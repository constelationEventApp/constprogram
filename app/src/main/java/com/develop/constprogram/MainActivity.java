package com.develop.constprogram;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
Button btn, btnListDashboard, pickImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.toListProgram);
        pickImage=findViewById(R.id.picKImage);
        btnListDashboard=findViewById(R.id.btnListDashboard);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent=new Intent(MainActivity.this,FragmentUsageActivity.class);
                Intent intent=new Intent(MainActivity.this,CreateProgramActivity.class);
                startActivity(intent);
            }
        });

        btnListDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this, NavDrawerActivity.class);
                startActivity(intent);
            }
        });


        pickImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,ImageToUploadAndDisplay.class);
                startActivity(intent);
            }
        });

    }
}
