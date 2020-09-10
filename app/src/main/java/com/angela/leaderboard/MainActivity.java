package com.angela.leaderboard;




import android.content.Intent;
import android.os.Bundle;


import com.google.android.material.tabs.TabLayout;

import androidx.viewpager.widget.ViewPager;
import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.Button;

import com.angela.leaderboard.ui.main.SectionsPagerAdapter;

public class MainActivity extends AppCompatActivity {

    private Button buttonsubmitlb;

    @Override
    protected void onCreate( Bundle savedInstanceState ) {
        super.onCreate (savedInstanceState);
        setContentView (R.layout.activity_main);
        SectionsPagerAdapter sectionsPagerAdapter = new SectionsPagerAdapter (this,getSupportFragmentManager ());
        ViewPager viewPager = findViewById (R.id.view_pager);
        viewPager.setAdapter (sectionsPagerAdapter);
        TabLayout tabs = findViewById (R.id.tabs);
        tabs.setupWithViewPager (viewPager);

        buttonsubmitlb = findViewById (R.id.btn_submit);
        buttonsubmitlb.setOnClickListener (new View.OnClickListener () {
            @Override
            public void onClick( View view ) {
                projectSubmitlb();
            }


        });


             }
    private void projectSubmitlb() {

        Intent projectsubmitlb = new Intent (this, SubmitLBActivity.class);
        startActivity (projectsubmitlb);
    }

        }