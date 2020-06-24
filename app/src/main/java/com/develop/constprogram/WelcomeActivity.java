package com.develop.constprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class WelcomeActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_welcome);
    }

    //onClick Login Button
    public  void onClickLoginButton (View view){
        Intent home = new Intent(WelcomeActivity.this, LoginActivity.class);
        startActivity(home);
        finish();
    }
    //onClick create account
    public  void onClickCreateAccount (View view){
        Intent home = new Intent(WelcomeActivity.this, EventInformationFirstActivity.class);
        startActivity(home);
        finish();
    }

}