package com.develop.constprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class CreateProgramActivity extends AppCompatActivity {

    private Button firstFragment, secondFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_program);

        firstFragment = (Button) findViewById(R.id.id_create_account_back);
        secondFragment = (Button) findViewById(R.id.id_create_account_next);
        getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.id_create_program_frame, new EventInformationFirstFragment()).commit();


// perform setOnClickListener event on First Button
        firstFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// load First Fragment
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.id_create_program_frame, new EventInformationFirstFragment()).commit();

            }
        });
// perform setOnClickListener event on Second Button
        secondFragment.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
// load Second Fragment
                getSupportFragmentManager().beginTransaction().addToBackStack(null).replace(R.id.id_create_program_frame, new EventInformationSecondFragment()).commit();
            }
        });
    }
}