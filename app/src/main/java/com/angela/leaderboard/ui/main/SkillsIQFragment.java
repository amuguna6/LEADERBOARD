package com.angela.leaderboard.ui.main;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonArrayRequest;
import com.android.volley.toolbox.Volley;
import com.angela.leaderboard.R;
import com.angela.leaderboard.adapters.SkillsIqAdapter;
import com.angela.leaderboard.models.SkillsIQModel;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SkillsIQFragment extends Fragment {
    List<SkillsIQModel> skillsIQModelList;
    RecyclerView recyclerView;


    @Override
    public View onCreateView( @NonNull LayoutInflater inflater,@Nullable ViewGroup
            container,@Nullable Bundle savedInstanceState ) {
        View rootView = inflater.inflate (R.layout.fragment_skill_iq, container, false);

        recyclerView = rootView.findViewById (R.id.skilliq_recycler);
        skillsIQModelList = new ArrayList<> ();

        loadSkillsIQList();

        return rootView;
    }

    private void loadSkillsIQList() {

        String skillIQLeadersUrl = "https://gadsapi.herokuapp.com/api/skilliq";
        JsonArrayRequest request = new JsonArrayRequest (skillIQLeadersUrl,new Response.Listener<JSONArray> () {
            @Override
            public void onResponse( JSONArray response ) {
                JSONObject jsonObject;
                for (int i = 0; i < response.length (); i++) {

                    try {
                        jsonObject = response.getJSONObject (i);
                        SkillsIQModel model = new SkillsIQModel ();
                        model.setName (jsonObject.getString ("name"));
                        model.setCountry (jsonObject.getString ("country"));
                        model.setScore (jsonObject.getInt ("score"));
                        model.setBadgeUrl (jsonObject.getString ("badgeUrl"));
                        skillsIQModelList.add (model);

                        setupRecyclerView (skillsIQModelList);

                    } catch (JSONException e) {
                        e.printStackTrace ();
                    }

                }
            }

        },new Response.ErrorListener () {
            @Override
            public void onErrorResponse( VolleyError error ) {
                Snackbar.make (Objects.requireNonNull (getView ()),"An Error Occurred \n" + error,
                        Snackbar.LENGTH_SHORT).show ();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue (Objects.requireNonNull (getContext ()));
        requestQueue.add (request);

         }
        private void setupRecyclerView( List<SkillsIQModel> skillsIQModelList ) {
            Collections.sort (skillsIQModelList,new Comparator<SkillsIQModel> () {
                @Override
                public int compare( SkillsIQModel skillsIQModel,SkillsIQModel t1 ) {
                    SkillsIQModel rhs = new SkillsIQModel();
                    SkillsIQModel Ihs = new SkillsIQModel();
                    return Integer.compare (rhs.getScore(), Ihs.getScore());
                }
            });
            SkillsIqAdapter skillsIqAdapter = new SkillsIqAdapter (getContext (), skillsIQModelList);
            recyclerView.setLayoutManager (new LinearLayoutManager (getContext ()));
            recyclerView.setAdapter (skillsIqAdapter);

    }
}
