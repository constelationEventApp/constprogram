package com.develop.constprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class MainActivity extends AppCompatActivity {
Button btn, btnListDashboard;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn=findViewById(R.id.toListProgram);
        btnListDashboard=findViewById(R.id.btnListDashboard);

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Recycle.class);
                startActivity(intent);
            }
        });

        btnListDashboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent=new Intent(MainActivity.this,Dashboard.class);
                startActivity(intent);
            }
        });
        /*list= (ListView) findViewById(R.id.list_program);
        adapter = new ArrayAdapter(this,
                android.R.layout.simple_list_item_1,values);
        // Assign adapter to ListView
        list.setAdapter(adapter);*/
    }
}
