package com.angela.leaderboard.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.AppCompatImageView;
import androidx.recyclerview.widget.RecyclerView;

import com.angela.leaderboard.R;
import com.angela.leaderboard.models.SkillsIQModel;
import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.textview.MaterialTextView;

import java.util.List;

public class SkillsIqAdapter extends RecyclerView.Adapter<SkillsIqAdapter.SkillsIQViewHolder>{

    private Context context;
    List<SkillsIQModel>skillsIQModel;
    RequestOptions requestOptions;

    public SkillsIqAdapter( Context context, List<SkillsIQModel> skillsIQModel) {
        this.context = context;
        this.skillsIQModel=skillsIQModel;

        requestOptions = new RequestOptions ()
                .fitCenter ()
                .placeholder (R.drawable.skilliq_badge)
                .error (R.drawable.skilliq_badge);
    }

    @NonNull
    @Override
    public SkillsIQViewHolder onCreateViewHolder( @NonNull ViewGroup parent,int viewType ) {
        LayoutInflater layoutInflater= LayoutInflater.from (context);
        View view = layoutInflater.inflate (R.layout.skillsiq_learners,parent, false);
        return new SkillsIQViewHolder (view);
    }

    @Override
    public void onBindViewHolder( @NonNull SkillsIQViewHolder holder,int position ) {
        String name = skillsIQModel.get (position).getName ();
        String badge = skillsIQModel.get (position).getBadgeUrl ();
        String country = skillsIQModel.get (position).getCountry ();
        int score = skillsIQModel.get (position).getScore ();
        String description = score + "skill IQ Score," + country+".";


        Glide.with (context)
                .load (badge)
                .apply (requestOptions)
                .into (holder.skilliqBadge);
        holder.skilliqName.setText(name);
        holder.skilliqDescription.setText(description);

    }

    @Override
    public int getItemCount() {
        return skillsIQModel.size ();
    }

    public class SkillsIQViewHolder extends RecyclerView.ViewHolder{
        AppCompatImageView skilliqBadge;
        MaterialTextView skilliqName;
        MaterialTextView skilliqDescription;



        public SkillsIQViewHolder( @NonNull View itemView ) {
            super (itemView);
            skilliqBadge = itemView.findViewById (R.id.skillig_badge);
            skilliqName = itemView.findViewById (R.id.skilliq_name);
            skilliqDescription= itemView.findViewById (R.id.skilliq_description);
        }
    }

}



