package com.appteam.adapters;

import android.app.Activity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.appteam.hillfair2k19.R;
import com.appteam.model.Club;
import com.squareup.picasso.Picasso;

import java.util.List;
import de.hdodenhof.circleimageview.CircleImageView;

/**
 * Coded by ThisIsNSH on Someday.
 */

public class ClubAdapter extends RecyclerView.Adapter<ClubAdapter.MyViewHolder> {
    List<Club> clubList;
    Activity activity;

    public ClubAdapter(List<Club> clubList, Activity activity) {
        this.activity = activity;
        this.clubList = clubList;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(parent.getContext(), R.layout.adapter_sponsor, null);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, int position) {
        final Club club = clubList.get(position);
        holder.title.setText(club.getName());
        Picasso.with(activity).load(club.getImage()).resize(80, 80).centerCrop().into(holder.image);
//        holder.arrow.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
////                InfoDialog infoDialog = new InfoDialog(activity, club.getInfo());
////                infoDialog.show();
//            }
//        });
    }

    @Override
    public int getItemCount() {
        return clubList.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        ImageView image;
        TextView title;
//        TextView arrow;

        public MyViewHolder(View itemView) {
            super(itemView);
//            arrow = itemView.findViewById(R.id.arrow);
            title = itemView.findViewById(R.id.title);
            image = itemView.findViewById(R.id.image);
        }
    }
}