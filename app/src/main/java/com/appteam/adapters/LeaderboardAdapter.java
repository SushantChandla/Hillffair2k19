package com.appteam.adapters;


import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appteam.hillfair2k19.R;
import com.appteam.model.Leaderboard;
import com.squareup.picasso.Picasso;

import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;

/**
.
 */

public class LeaderboardAdapter extends RecyclerView.Adapter<LeaderboardAdapter.MyViewHolder> {
    List<Leaderboard> leaderboardList;
    Activity activity;

    public LeaderboardAdapter(List<Leaderboard> leaderboardList, Activity activity) {
        this.activity = activity;
        this.leaderboardList = leaderboardList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_leaderboard, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Leaderboard club = leaderboardList.get(position);
        holder.title.setText(club.getName());
        Log.e("aa", club.getImage());
        if (!club.getImage().equals("null"))
            Picasso.with(activity).load(club.getImage()).resize(80, 80).centerCrop().into(holder.image);
        else
            Picasso.with(activity).load("https://www.fluigent.com/wp-content/uploads/2018/07/default-avatar-BW.png").resize(80, 80).centerCrop().into(holder.image);
        holder.arrow.setText(club.getInfo());
        holder.rank.setText(String.valueOf(position + 1));
    }

    @Override
    public int getItemCount() {
        return leaderboardList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        CircleImageView image;
        TextView title;
        TextView arrow, rank;

        public MyViewHolder(View itemView) {
            super(itemView);
            arrow = itemView.findViewById(R.id.arrow);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
            rank = itemView.findViewById(R.id.rank);
        }
    }
}
