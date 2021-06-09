package com.jacobs.myapplication35;

import android.content.Context;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentManager;



import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public  class  ViewPagerAdapter  extends  FragmentPagerAdapter {

    int mNumOfTabs;
    public ViewPagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }


    @Override
    public Fragment getItem(int position) {
        /*
         * IMPORTANT: This is the point. We create a RootFragment acting as
         * a container for other fragments
         */
        if (position == 0)
            return new Frag2();
        else
            return new RootFrag();
    }


    @Override
    public int getCount() {
        return mNumOfTabs;
    }
}