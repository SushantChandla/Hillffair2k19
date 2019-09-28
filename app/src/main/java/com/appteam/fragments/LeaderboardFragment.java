package com.appteam.fragments;


import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.androidnetworking.AndroidNetworking;
import com.androidnetworking.error.ANError;
import com.androidnetworking.interfaces.JSONArrayRequestListener;
import com.appteam.adapters.LeaderboardAdapter;
import com.appteam.hillfair2k19.R;
import com.appteam.model.Leaderboard;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;


public class LeaderboardFragment extends Fragment implements View.OnClickListener {
    ProgressBar loadwall;
    private LeaderboardAdapter clubAdapter;
    private RecyclerView recyclerView;
    private List<Leaderboard> clubList = new ArrayList<>();
    private TextView point, referral, popular;
    private Activity activity;

    public LeaderboardFragment() {
    }

    public LeaderboardFragment(Activity activity) {
        this.activity = activity;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_leaderboard, container, false);

        point = view.findViewById(R.id.point);
        referral = view.findViewById(R.id.referral);
        loadwall = view.findViewById(R.id.loadwall);
        popular = view.findViewById(R.id.popular);
        popular.setOnClickListener(this);
        point.setOnClickListener(this);
        referral.setOnClickListener(this);
        recyclerView = view.findViewById(R.id.leaderboard);
        clubAdapter = new LeaderboardAdapter(clubList, activity);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(clubAdapter);
        recyclerView.setNestedScrollingEnabled(false);
        getData();
        Log.e("LeaderFragment", "onCreateView: ");
        return view;
    }
    public void getData() {

        clubList.clear();
        loadwall.setVisibility(View.VISIBLE);
        AndroidNetworking.get(getString(R.string.baseUrl) + "getleaderboard")
                .build()
                .getAsJSONArray(new JSONArrayRequestListener() {
                    @Override
                    public void onResponse(JSONArray response) {
                        try {
                            loadwall.setVisibility(View.GONE);
                            int users = response.length();
                            System.out.println(response);
                            for (int i = 0; i < users; i++) {
                                JSONObject json = response.getJSONObject(i);
                                String clubname = json.getString("name");
                                String score = json.getString("score");
                                String id = json.getString("image_url");
                                clubList.add(new Leaderboard(clubname, id, score));
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

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.popular:
                popular.setTextColor(getResources().getColor(R.color.black));
                point.setTextColor(getResources().getColor(R.color.hint));
                referral.setTextColor(getResources().getColor(R.color.hint));
                break;
            case R.id.point:
                point.setTextColor(getResources().getColor(R.color.black));
                popular.setTextColor(getResources().getColor(R.color.hint));
                referral.setTextColor(getResources().getColor(R.color.hint));
                break;
            case R.id.referral:
                referral.setTextColor(getResources().getColor(R.color.black));
                point.setTextColor(getResources().getColor(R.color.hint));
                popular.setTextColor(getResources().getColor(R.color.hint));
                break;
        }

    }
}
