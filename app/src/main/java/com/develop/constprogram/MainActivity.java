package com.develop.constprogram;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {
Button btn, btnListDashboard, pickImage;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);




        Handler handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                startActivity(new Intent(getApplicationContext(), NavDrawerActivity.class));
                finish();
            }
        },2000);

      /*  btn=findViewById(R.id.toListProgram);
        pickImage=findViewById(R.id.picKImage);
        btnListDashboard=findViewById(R.id.btnListDashboard);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
              //  Intent intent=new Intent(MainActivity.this,FragmentUsageActivity.class);
                Intent intent=new Intent(MainActivity.this,Authentication.class);
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
*/
    }
}
