package com.appteam.hillfair2k19;


import
        android.app.Activity;
import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.android.volley.VolleyError;
import com.appteam.adapters.TeamAdapter;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import com.appteam.hillfair2k19.model.Team;


/**
 * A simple {@link Fragment} subclass.
 */
public class coreteam extends Fragment {

    ProgressBar loadwall;
    private RecyclerView recyclerView;
    private TeamAdapter teamAdapter;
    private List<com.appteam.hillfair2k19.model.Team> teamList = new ArrayList<>();
    private Activity activity;
    IResult mResultCallbackAndroidNeworking;
    public coreteam() {
    }

    public coreteam(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_coreteam, container, false);
        com.androidnetworking.AndroidNetworking.initialize(getActivity().getApplicationContext());

        recyclerView = view.findViewById(R.id.thirdRec);
        loadwall = view.findViewById(R.id.loadwall);
        teamAdapter = new com.appteam.adapters.TeamAdapter(teamList, activity);

        recyclerView.setLayoutManager(new GridLayoutManager(activity, 2));
        getData();
        recyclerView.setAdapter(teamAdapter);
//        Log.e("CodeFragment", "onCreateView: ");
        return view;
    }

    public void getData() {
        teamList.clear();
//        teamList.add(new Team("Captaion Marvel", "https://www.hdwallpapersfreedownload.com/uploads/large/super-heroes/captain-marvel-avengers-brie-larson-super-hero-hd-wallpaper.jpg", "Chief"));
//        teamList.add(new Team("Thanos", "https://pre00.deviantart.net/db91/th/pre/i/2017/197/8/0/thanos_wallpaper_16_by_rippenstain-dbghpzw.jpg", "Villan"));
//        teamList.add(new Team("Iron Mam", "https://wallpapersite.com/images/pages/ico_n/15263.jpg", "Hero"));
//        teamAdapter.notifyDataSetChanged();

        //Initialising
        initAndroidNetworkingCallback();
        com.androidnetworking.AndroidNetworking.initialize(getActivity());
        com.appteam.hillfair2k19.AndroidNetworking androidNetworking = new com.appteam.hillfair2k19.AndroidNetworking(mResultCallbackAndroidNeworking,getActivity());

        //                 GET REQUEST SINGLE JSON OBJECT
        androidNetworking.getJsonObjectAndroidNetworking("GetCoreteam",activity.getString(R.string.baseUrl) + "/core_team");


//        GET REQUEST FOR JSON ARRAY
//        androidNetworking.getJsonArrayAndroidNetworking("GETJSONARRAYFROMLIIFESAVER","https://lifesaverapp.herokuapp.com/controlpolice");



    }
    void initAndroidNetworkingCallback(){
        mResultCallbackAndroidNeworking = new IResult() {
            @Override
            public void notifySuccess(String requestType, JSONObject response , JSONArray jsonArray) {
                if (response != null)
                {
                    //JsonObject
                    try {
                        jsonArray = response.getJSONArray("members");
                        int users = jsonArray.length();
                        for (int i = 0; i < users; i++) {
                            JSONObject json = jsonArray.getJSONObject(i);
                            String name = json.getString("name");
                            String profile = json.getString("image_url");
                            String position = json.getString("position");
                            teamList.add(new com.appteam.hillfair2k19.model.Team(name, profile, position));
                        }
                        teamAdapter.notifyDataSetChanged();

                    } catch (JSONException e) {
                        e.printStackTrace();
                    }
//                    Toast.makeText(getActivity(), String.valueOf(jsonArray), Toast.LENGTH_SHORT).show();
                }
                else {
                    //JsonArray
                }
            }

            @Override
            public void notifyError(String requestType, VolleyError error) {

            }
        };
    }


}
