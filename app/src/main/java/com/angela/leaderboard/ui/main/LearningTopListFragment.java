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
import com.angela.leaderboard.adapters.LearningTopListAdapter;
import com.angela.leaderboard.models.LearningTopListModel;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.snackbar.Snackbar;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class LearningTopListFragment extends Fragment {
    private static final String learningLeaderUrl = "https://gadsapi.herokuapp.com/api/hours";
    private List<LearningTopListModel> learningTopListModelList;
    private RecyclerView recyclerView;


    @Nullable
    @Override
    public View onCreateView( @NonNull LayoutInflater inflater,@Nullable ViewGroup container,
            @Nullable Bundle savedInstanceState ) {

        View rootView = inflater.inflate (R.layout.fragment_learning_toplist, container, false);

        recyclerView = rootView.findViewById (R.id.learning_toplist_recycler);
        learningTopListModelList = new ArrayList<> ();

        loadLearningLeaders();

        return rootView;
    }

    private void loadLearningLeaders() {

        JsonArrayRequest request = new JsonArrayRequest (learningLeaderUrl,new Response.Listener<JSONArray> () {
            @Override
            public void onResponse( JSONArray response ) {
                JSONObject jsonObject;
                for (int i = 0; i < response.length (); i++) {

                    try {
                        jsonObject = response.getJSONObject (i);
                        LearningTopListModel model = new LearningTopListModel ();
                        model.setName (jsonObject.getString ("name"));
                        model.setCountry (jsonObject.getString ("country"));
                        model.setHours (jsonObject.getInt ("hours"));
                        model.setBadgeUrl (jsonObject.getString ("badgeUrl"));
                        learningTopListModelList.add (model);


                    } catch (JSONException e) {
                        e.printStackTrace ();
                    }
                    setupRecyclerView (learningTopListModelList);
                }
            }

        },new Response.ErrorListener () {
            @Override
            public void onErrorResponse( VolleyError error ) {
                Snackbar.make (Objects.requireNonNull (getView ()), "An Error occurred \n" +error,
                        Snackbar.LENGTH_SHORT).show ();
            }
        });
        RequestQueue requestQueue = Volley.newRequestQueue (Objects.requireNonNull (getContext ()));
        requestQueue.add (request);
    }
        private void setupRecyclerView( List<LearningTopListModel> learningTopListModelList ) {
            Collections.sort (learningTopListModelList,new Comparator<LearningTopListModel> () {
                @Override
                public int compare( LearningTopListModel learningTopListModel,LearningTopListModel t1 ) {
                    return Integer.compare (t1.getHours (),learningTopListModel.getHours ());
                }
            });

            LearningTopListAdapter learningTopListAdapter = new LearningTopListAdapter (getContext (), learningTopListModelList);
            recyclerView.setLayoutManager (new LinearLayoutManager (getContext ()));
            recyclerView.setAdapter (learningTopListAdapter);
            }
        }

