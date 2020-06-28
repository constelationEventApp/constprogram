package com.develop.constprogram.ui.subscription;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

class PageAdapterSubscription extends FragmentPagerAdapter {
    private int tabsNumber;
    public PageAdapterSubscription(@NonNull FragmentManager fm, int behavior, int tabs) {
        super(fm, behavior);
        this.tabsNumber = tabs;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0: {
                return new FollowerFragment();
            }
            case 1: {
                return new FollowingFragment();
            }
            default: {
                return null;
            }
        }
    }

    @Override
    public int getCount() {
        return tabsNumber;
    }
}
