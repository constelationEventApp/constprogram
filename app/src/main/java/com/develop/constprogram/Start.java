package com.develop.constprogram;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.database.FirebaseListAdapter;
import com.firebase.ui.database.FirebaseListOptions;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.squareup.picasso.Picasso;

public class Start extends AppCompatActivity {

    ListView lv;
    FirebaseListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        lv=(ListView) findViewById(R.id.startListView);

        Query query= FirebaseDatabase.getInstance().getReference().child("program");

        FirebaseListOptions<CustomProgramListModel> options = new FirebaseListOptions.Builder<CustomProgramListModel>()
                .setLayout(R.layout.custom_list_program)
                .setLifecycleOwner(Start.this)
                .setQuery(query, CustomProgramListModel.class)
                .build();

        adapter = new FirebaseListAdapter(options) {
            @Override
            protected void populateView(View v, Object model, int position) {
                TextView programDate= v.findViewById(R.id.txtDateProgramList);
                TextView programName=v.findViewById(R.id.txtNameProgramList);
                TextView programAddress=v.findViewById(R.id.txtAdressProgramList);
                TextView programIdentity= v.findViewById(R.id.identifiantProgramList);
                ImageView mainImage= v.findViewById(R.id.imProgramList);
                ImageButton share=v.findViewById(R.id.imbShareProgramList);
                ImageButton favorite=v.findViewById(R.id.imbFavoriteProgramList);

                CustomProgramListModel pro= (CustomProgramListModel) model;

                programDate.setText(pro.getProgramDate().toString());
                programName.setText(pro.getProgramName().toString());
                programAddress.setText(pro.getProgramAddress().toString());
                programIdentity.setText(pro.getProgramIdentity().toString());
               // mainImage.setImageResource(R.drawable.ic_launcher_background);
                share.setImageResource(R.drawable.share);
                favorite.setImageResource(R.drawable.favorite);
                Picasso.get().load(pro.getProgramImage().toString()).placeholder(R.drawable.ic_launcher_background).fit().centerCrop().into(mainImage);


                share.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Share Button", Toast.LENGTH_LONG).show();
                        Intent sharingIntent= new Intent(android.content.Intent.ACTION_SEND);
                        sharingIntent.setType("text/plain");
                        String shareBody="Thank you for sharing our app follow this link to download from playstore";
                        sharingIntent.putExtra(android.content.Intent.EXTRA_SUBJECT, "Subject here");
                        sharingIntent.putExtra(android.content.Intent.EXTRA_TEXT, shareBody);
                        startActivity(Intent.createChooser(sharingIntent,"Share via"));

                    }
                });
                favorite.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(getApplicationContext(), "Favorite Button", Toast.LENGTH_LONG).show();
                    }
                });


            }
        };

        lv.setAdapter(adapter);
    }

    @Override
    protected void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        adapter.stopListening();
    }
}