package com.appteam.fragments;


import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.androidnetworking.interfaces.JSONObjectRequestListener;
import com.appteam.adapters.ClubAdapter;
import com.appteam.hillfair2k19.R;
import com.appteam.model.Club;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


/**
 * Coded by ThisIsNSH on Someday.
 */

public class ClubsFragment extends Fragment {

    ProgressBar loadwall;
    private ClubAdapter clubAdapter;
    private RecyclerView recyclerView;
    private Activity activity;
    private List<Club> clubList = new ArrayList<>();

    public ClubsFragment() {

    }

    public ClubsFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_club, container, false);
        recyclerView = view.findViewById(R.id.secondRec);
        loadwall = view.findViewById(R.id.loadwall);
        clubAdapter = new ClubAdapter(clubList, activity);
        getData();
        @SuppressLint("WrongConstant") LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(clubAdapter);
        Log.e("ClubsFragment", "onCreateView: ");
        return view;
    }

    public void getData() {
        clubList.clear();
        loadwall.setVisibility(View.VISIBLE);
        AndroidNetworking.get(activity.getString(R.string.baseUrl) + "/Club_info")
                .build()
                .getAsJSONObject(new JSONObjectRequestListener() {
                    @Override
                    public void onResponse(JSONObject resp) {
                        try {
                            JSONArray response = resp.getJSONArray("clubs");
                            loadwall.setVisibility(View.GONE);
                            int users = response.length();
                            for (int i = 0; i < users; i++) {
                                JSONObject json = response.getJSONObject(i);
                                String clubname = json.getString("club_name");
                                String info = json.getString("description");
                                String id = json.getString("image_url");
                                Toast.makeText(activity, clubname, Toast.LENGTH_SHORT).show();
                                clubList.add(new Club(clubname, id, info));
                            }
                            clubAdapter.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                    @Override
                    public void onError(ANError error) {
                    }
                });

        clubAdapter.notifyDataSetChanged();
    }
}