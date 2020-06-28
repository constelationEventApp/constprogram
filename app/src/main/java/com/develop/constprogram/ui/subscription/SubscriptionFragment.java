package com.develop.constprogram.ui.subscription;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.develop.constprogram.R;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class SubscriptionFragment extends Fragment {
    TabLayout mTabLayoutSubs;
    ViewPager mViewPagerSubs;
    PageAdapterSubscription mPageAdapterSubscription;
    FragmentManager mFragmentManager;
    TabItem mFollower;
    TabItem mFollowing;
/*    public static SubscriptionFragment newInstance(String param1, String param2) {
        SubscriptionFragment fragment = new SubscriptionFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

 /*   @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_subscription, container,false);

        mViewPagerSubs = view.findViewById(R.id.id_viewpager_subscription);
        mTabLayoutSubs = view.findViewById(R.id.id_tab_layout_subscription);
        mFollower = view.findViewById(R.id.id_follower_tab_subscription);
        mFollowing = view.findViewById(R.id.id_following_tab_subscription);

        mPageAdapterSubscription = new PageAdapterSubscription(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mTabLayoutSubs.getTabCount());

        mViewPagerSubs.setAdapter(mPageAdapterSubscription);
        mTabLayoutSubs.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPagerSubs.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPagerSubs.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayoutSubs));

        return view;
    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
    }
}