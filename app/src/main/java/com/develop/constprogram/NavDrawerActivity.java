package com.develop.constprogram;

import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.develop.constprogram.ui.AboutUsFragment;
import com.develop.constprogram.ui.AccountFragment;
import com.develop.constprogram.ui.FavoriteFragment;
import com.develop.constprogram.ui.HelpCommentFragment;
import com.develop.constprogram.ui.HistoryFragment;
import com.develop.constprogram.ui.MethodPaymentFragment;
import com.develop.constprogram.ui.WhoToFollowFragment;
import com.develop.constprogram.ui.settings.SettingFragment;
import com.develop.constprogram.ui.subscription.SubscriptionFragment;
import com.google.android.material.navigation.NavigationView;

public class NavDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener, MainFragment.onFragmentBtnSelected {
    DrawerLayout mDrawerLayout;
    ActionBarDrawerToggle mActionBarDrawerToggle;
    Toolbar mToolbar;
    NavigationView mNavigationView;
    FragmentManager mFragmentManager;
    FragmentTransaction mFragmentTransaction;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nav_drawer);

        mDrawerLayout = findViewById(R.id.drawer);
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);
        mNavigationView = findViewById(R.id.navigationView);

        mNavigationView.setNavigationItemSelectedListener(this);

        mActionBarDrawerToggle = new ActionBarDrawerToggle(this, mDrawerLayout, mToolbar, R.string.open, R.string.close);
        mDrawerLayout.addDrawerListener(mActionBarDrawerToggle);
        mActionBarDrawerToggle.setDrawerIndicatorEnabled(true);
        mActionBarDrawerToggle.syncState();

        // load default fragment

        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.add(R.id.container_fragment, new MainFragment());
        mFragmentTransaction.commit();

    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
        mDrawerLayout.closeDrawer(GravityCompat.START);
        if(menuItem.getItemId() == R.id.nav_home){
            // load home fragment

            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.container_fragment, new MainFragment());
            mFragmentTransaction.commit();
        }

        if(menuItem.getItemId() == R.id.nav_favorite){
            // load dashboard fragment
            mFragmentManager = getSupportFragmentManager();
            mFragmentTransaction = mFragmentManager.beginTransaction();
            mFragmentTransaction.replace(R.id.container_fragment, new FavoriteFragment());
            mFragmentTransaction.commit();
        }
        switch (menuItem.getItemId()){
            case R.id.nav_compte : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new AccountFragment());
                mFragmentTransaction.commit();

            }
            case R.id.nav_follow : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new WhoToFollowFragment());
                mFragmentTransaction.commit();
            }
            case R.id.nav_about_us : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new AboutUsFragment());
                mFragmentTransaction.commit();
            }
            case R.id.nav_help_comment : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new HelpCommentFragment());
                mFragmentTransaction.commit();
            }
            case R.id.nav_history : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new HistoryFragment());
                mFragmentTransaction.commit();
            }
            case R.id.nav_quit : {

            }
            case R.id.nav_modepaiment : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new MethodPaymentFragment());
                mFragmentTransaction.commit();
            }
            case R.id.nav_subscription : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new SubscriptionFragment());
                mFragmentTransaction.commit();
            }
            case R.id.nav_setting : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new SettingFragment());
                mFragmentTransaction.commit();
            }
        }

        return true;
    }

    @Override
    public void onButtonSelected() {
        Toast.makeText(this,"Good", Toast.LENGTH_LONG).show();
    }
}