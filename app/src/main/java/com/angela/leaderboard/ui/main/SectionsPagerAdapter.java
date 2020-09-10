package com.angela.leaderboard.ui.main;

import android.content.Context;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.StringRes;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.angela.leaderboard.R;

/**
 * A [FragmentPagerAdapter] that returns a fragment corresponding to
 * one of the sections/tabs/pages.
 */
public class SectionsPagerAdapter extends FragmentPagerAdapter {

    @StringRes
    private static final int[] TAB_TITLES = new int[]{R.string.tab_text_learning,R.string.tab_text_iqskill};
    private final Context mContext;

    public SectionsPagerAdapter( Context context,FragmentManager fm ) {
        super (fm);


        mContext = context;
    }

    public SectionsPagerAdapter( @NonNull FragmentManager fm,int behavior,Context mContext ) {
        super (fm,behavior);
        this.mContext = mContext;
    }

    public SectionsPagerAdapter( @NonNull FragmentManager fm,int behavior ) {
        super (fm,behavior);
        mContext = null;
    }

    @NonNull
    @Override
    public Fragment getItem( int position ) {
        // getItem is called to instantiate the fragment for the given page.
        // Return a PlaceholderFragment (defined as a static inner class below).

        switch (position){
            case 0:
                 return new LearningTopListFragment ();


            case 1:
                return new SkillsIQFragment ();

        }

            return null;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle( int position ) {
        //return mContext.getResources ().getString (TAB_TITLES[position]);

        switch (position) {
            case 0:
                return "Learning Leaders";

            case 1:
                return "Skill IQ Leaders";
        }
                return null;

    }

    @Override
    public int getCount() {
        // Show 2 total pages.
        return 2;
    }
}