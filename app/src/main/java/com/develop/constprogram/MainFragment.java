package com.develop.constprogram;

import android.content.Context;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.develop.constprogram.ui.home.MyprogramFragment;
import com.google.android.material.tabs.TabItem;
import com.google.android.material.tabs.TabLayout;


public class MainFragment extends Fragment  {
    private onFragmentBtnSelected listener;
    ViewPager mViewPager;

    TabLayout mTabLayout;
    TabItem mDashboard;
    TabItem mProgramList;
    TabItem mMyProgram;
    PagerAdapter mPagerAdapter, mAdapter;
    FragmentManager mFragmentManager;



    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if( context instanceof onFragmentBtnSelected){
            listener = (onFragmentBtnSelected) context;

        }else {
            throw new ClassCastException(context.toString()+ "must implement listener");
        }
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
    // * @param param1 Parameter 1.
     //* @param param2 Parameter 2.
     * @return A new instance of fragment MainFragment.
     */
    // TODO: Rename and change types and number of parameters
   /* public static MainFragment newInstance(String param1, String param2) {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }*/

   /*
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }
*/
XmlClickable mMyProgramFragment;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_main, container,false);

       /* Button load = view.findViewById(R.id.id_load);
        load.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.onButtonSelected();
            }
        });*/
       mViewPager = view.findViewById(R.id.id_viewpager_tab);
       mTabLayout = view.findViewById(R.id.id_tab_layout);
       mDashboard = view.findViewById(R.id.id_dashboard_tab);
        mProgramList = view.findViewById(R.id.id_listProgram_tab);
        mMyProgram = view.findViewById(R.id.id_myprogram_tab);

        mPagerAdapter = new PagerAdapter(getChildFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mTabLayout.getTabCount());

        mViewPager.setAdapter(mPagerAdapter);

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                mViewPager.setCurrentItem(tab.getPosition());
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {

            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));
        return  view;
    }



    public  interface onFragmentBtnSelected{
        public void onButtonSelected();
    }
}