package com.develop.constprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;

public class EventInformationSecondActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_information_second);
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EventInformationSecondActivity.this, EventInformationFirstActivity.class);
        startActivity(intent);
        finish();
    }
}