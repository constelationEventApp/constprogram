package com.develop.constprogram;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.develop.constprogram.ui.home.DashboardFragment;
import com.develop.constprogram.ui.home.MyprogramFragment;
import com.develop.constprogram.ui.home.ProgramListFragment;

class PagerAdapter extends FragmentPagerAdapter {
    private int tabsNumber;

    public PagerAdapter(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position){
            case 0:{
                return  new DashboardFragment();
            }
            case 1:{
                return new ProgramListFragment();
            }
            case 2:{
                return new MyprogramFragment();
            }
            default:{
                return null;
            }
        }

    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
