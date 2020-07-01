package com.develop.constprogram;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
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
import com.develop.constprogram.ui.AddCreditCardPaymentFragment;
import com.develop.constprogram.ui.FavoriteFragment;
import com.develop.constprogram.ui.HelpCommentFragment;
import com.develop.constprogram.ui.HistoryFragment;
import com.develop.constprogram.ui.MethodPaymentFragment;
import com.develop.constprogram.ui.OtherSettingPaymentFragment;
import com.develop.constprogram.ui.WhoToFollowFragment;
import com.develop.constprogram.ui.settings.CopyrightSettingFragment;
import com.develop.constprogram.ui.settings.LanguageSettingFragment;
import com.develop.constprogram.ui.settings.PrivacySettingFragment;
import com.develop.constprogram.ui.settings.SettingFragment;
import com.develop.constprogram.ui.settings.TermeUseSettingFragment;
import com.develop.constprogram.ui.settings.UserDataPolicySettingFragment;
import com.develop.constprogram.ui.subscription.SubscriptionFragment;
import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.navigation.NavigationView;

public class NavDrawerActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener,
        MainFragment.onFragmentBtnSelected, SettingFragment.onButtonSettingFragmentSelected,
        MethodPaymentFragment.onButtonPaymentFragmentSelected, HelpCommentFragment.onButtonHelpCommentFragmentSelected,
LanguageSettingFragment.onButtonLanguageSettingFragmentSelected {
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
    public void onBackPressed() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        String fragmentClass = mFragmentManager.getFragments().toString();
        String fragmentName="";
        int length = fragmentClass.length();
        int i=1;
        char element = 'a';
        while (i < length && element != '{'){
            element = fragmentClass.charAt(i);
            if(element != '{'){
                fragmentName+=element;

            }
            i++;
        }


        switch (fragmentName){
            case "HelpCommentFragment" :{
                mFragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                break;
            }
            case "AccountFragment":{
                mFragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                break;
            }
            case "MethodPaymentFragment":{
                mFragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                break;
            }
            case "SettingFragment":{
                mFragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                break;
            }
            case "FavoriteFragment":{
                mFragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                break;
            }
            case "WhoToFollowFragment":{
                mFragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                break;
            }
            case "SubscriptionFragment":{
                mFragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                break;
            }
            case "HistoryFragment":{
                mFragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                break;
            }
            case "AboutUsFragment":{
                mFragmentTransaction.replace(R.id.container_fragment, new MainFragment());
                break;
            }

            case "CopyrightSettingFragment" :{
                mFragmentTransaction.replace(R.id.container_fragment, new SettingFragment());
                break;
            }
            case "LanguageSettingFragment" :{
                mFragmentTransaction.replace(R.id.container_fragment, new SettingFragment());
                break;
            }
            case "PrivacySettingFragment" :{
                mFragmentTransaction.replace(R.id.container_fragment, new SettingFragment());
                break;
            }
            case "TermeUseSettingFragment" :{
                mFragmentTransaction.replace(R.id.container_fragment, new SettingFragment());
                break;
            }
            case "UserDataPolicySettingFragment" :{
                mFragmentTransaction.replace(R.id.container_fragment, new SettingFragment());
                break;
            }
            case "AddCreditCardPaymentFragment":{
                mFragmentTransaction.replace(R.id.container_fragment, new MethodPaymentFragment());
                break;
            }
            case "OtherSettingPaymentFragment":{
                mFragmentTransaction.replace(R.id.container_fragment, new MethodPaymentFragment());
                break;
            }
            default:{
               message();
               break;
            }

        }
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
                break;

            }
            case R.id.nav_follow : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new WhoToFollowFragment());
                mFragmentTransaction.commit();
                break;
            }
            case R.id.nav_about_us : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new AboutUsFragment());
                mFragmentTransaction.commit();
                break;
            }
            case R.id.nav_help_comment : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new HelpCommentFragment());
                mFragmentTransaction.commit();
                break;
            }
            case R.id.nav_history : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new HistoryFragment());
                mFragmentTransaction.commit();
                break;
            }
            case R.id.nav_quit : {
                message();
                break;
            }
            case R.id.nav_modepaiment : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new MethodPaymentFragment());
                mFragmentTransaction.commit();
                break;
            }
            case R.id.nav_subscription : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new SubscriptionFragment());
                mFragmentTransaction.commit();
                break;
            }
            case R.id.nav_setting : {
                mFragmentManager = getSupportFragmentManager();
                mFragmentTransaction = mFragmentManager.beginTransaction();
                mFragmentTransaction.replace(R.id.container_fragment, new SettingFragment());
                mFragmentTransaction.commit();
                break;
            }
        }

        return true;
    }
    public  void message(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Exit");
        // set message
        builder.setMessage("Do you really want to exit WichEvent ?");
        // set icon
        builder.setIcon(R.drawable.quit);
        // set cancelable
        builder.setCancelable(true);
        // set Yes and No button
        builder.setPositiveButton("Oui", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                finish();
                System.exit(0);

            }
        });
        builder.setNegativeButton("Non", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // create dialog
        AlertDialog alertDialog = builder.create();
        // show dialog
        alertDialog.show();

    }


    @Override
    public void onButtonSelected() {
        Toast.makeText(this,"Good", Toast.LENGTH_LONG).show();
    }

    @Override
    public void privacyButton() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container_fragment, new PrivacySettingFragment());
        mFragmentTransaction.commit();
    }

    @Override
    public void languageButton() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container_fragment, new LanguageSettingFragment());
        mFragmentTransaction.commit();
    }

    @Override
    public void termUseButton() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container_fragment, new TermeUseSettingFragment());
        mFragmentTransaction.commit();
    }

    @Override
    public void userDataPolicyButton() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container_fragment, new UserDataPolicySettingFragment());
        mFragmentTransaction.commit();
    }
    public void change(){
        Toast.makeText(this, "Amos", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void copyrightButton() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container_fragment, new CopyrightSettingFragment());
        mFragmentTransaction.commit();
    }

    @Override
    public void logOutButton() {

    }

    @Override
    public void addCardPaymentButton() {
       /* mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container_fragment, new AddCreditCardPaymentFragment());
        mFragmentTransaction.commit();*/
       signOut();


    }
    public void signOut(){
        AuthUI.getInstance()
                .signOut(this)
                .addOnCompleteListener(new OnCompleteListener<Void>() {

                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        startActivity(new Intent(getApplicationContext(),Authentication.class));
                        finish();
                    }
                });
    }
    @Override
    public void otherSettingPaymentButton() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container_fragment, new OtherSettingPaymentFragment());
        mFragmentTransaction.commit();
    }

    @Override
    public void allArticleTv() {
        change();
    }

    @Override
    public void sendCommentButton() {
        mFragmentManager = getSupportFragmentManager();
        mFragmentTransaction = mFragmentManager.beginTransaction();
        mFragmentTransaction.replace(R.id.container_fragment, new SendCommentFragment());
        mFragmentTransaction.commit();

    }

    @Override
    public void englishButton() {
        Toast.makeText(this, "English", Toast.LENGTH_LONG).show();
    }

    @Override
    public void frenchButton() {
        Toast.makeText(this, "French", Toast.LENGTH_LONG).show();

    }

    @Override
    public void spanishButton() {
        Toast.makeText(this, "Spanish", Toast.LENGTH_LONG).show();

    }

    @Override
    public void creoleButton() {
        Toast.makeText(this, "Creole", Toast.LENGTH_LONG).show();

    }
}