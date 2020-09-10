package com.angela.leaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.angela.leaderboard.R;
import com.angela.leaderboard.models.LearningTopListModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.BaseRequestOptions;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class LearningTopListAdapter extends RecyclerView.Adapter<LearningTopListAdapter.LearningTopListViewHolder>{

        private Context context;
        List<LearningTopListModel> learningLeadersList;
        RequestOptions requestOptions;


    public LearningTopListAdapter( Context context, List<LearningTopListModel> learningLeadersModel ) {
        this.context = context;
        this.learningLeadersList = learningLeadersModel;

        requestOptions = new RequestOptions ()
                .fitCenter ()
                .placeholder (R.drawable.learner_badge)
                .error (R.drawable.learner_badge);
    }


    @NonNull
    @Override
    public LearningTopListViewHolder onCreateViewHolder( @NonNull ViewGroup parent,int viewType ) {
        LayoutInflater layoutInflater= LayoutInflater.from (context);
        View view = layoutInflater.inflate (R.layout.learning_toplist,parent,false);
        return new LearningTopListViewHolder (view);
    }

    @Override
    public void onBindViewHolder( @NonNull LearningTopListViewHolder holder,int position ) {

        String name = learningLeadersList.get (position).getName ();
        String badge = learningLeadersList.get (position).getBadgeUrl ();
        String country = learningLeadersList.get (position).getCountry ();
        int hours = learningLeadersList.get (position).getHours ();
        String description = hours + "learning hours," + country;

        Glide.with (context)
                .load (badge)
                .apply (requestOptions)
                .into (holder.learningLeadersBadge);
        holder.learningLeadersName.setText(name);
        holder.learningLeadersDescription.setText(description);

    }

    @Override
    public int getItemCount() {
        return learningLeadersList.size ();
    }

    public class LearningTopListViewHolder extends RecyclerView.ViewHolder{
        AppCompatImageView learningLeadersBadge;
        MaterialTextView learningLeadersName;
        MaterialTextView learningLeadersDescription;

        public LearningTopListViewHolder( @NonNull View itemView ) {
            super (itemView);

            learningLeadersBadge = itemView.findViewById (R.id.learner_badge);
            learningLeadersName = itemView.findViewById (R.id.learner_name);
            learningLeadersDescription = itemView.findViewById (R.id.learner_description);
        }
    }
}

