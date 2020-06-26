package com.develop.constprogram;

import androidx.appcompat.app.AppCompatActivity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class FragmentUsageActivity extends AppCompatActivity implements SegondFragment.secondFragmentInterface {

    Button firstFragment, secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fragment_usage);
// get the reference of Button's
        firstFragment = (Button) findViewById(R.id.firstFragment);
        secondFragment = (Button) findViewById(R.id.secondFragment);

// perform setOnClickListener event on First Button
        firstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// load First Fragment
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frameLayout, new FirstFragment()).commit();

            }
        });
// perform setOnClickListener event on Second Button
        secondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// load Second Fragment
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.frameLayout, new SegondFragment()).commit();
            }
        });

    }

    @Override
    public void onClickSecondButton() {
        Toast.makeText(getBaseContext(),"You are in the second fragment", Toast.LENGTH_LONG).show();
    }
}