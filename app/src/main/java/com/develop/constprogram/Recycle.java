package com.develop.constprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.firestore.FirestoreRecyclerAdapter;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

public class Recycle extends AppCompatActivity {
    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference programRef=db.collection("program");
    private ProgramAdapter adapter;
    private String activityName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recycle);
        activityName="Recycle";

        setUpRecyclerView();
    }

    private void setUpRecyclerView() {
        Query query= programRef;
        FirestoreRecyclerOptions<ProgramModel> options= new FirestoreRecyclerOptions.Builder<ProgramModel>()
                .setQuery(query,ProgramModel.class)
                .build();
        adapter=new ProgramAdapter(options,activityName);

        RecyclerView recyclerView = findViewById(R.id.programListProgram);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProgramAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                TextView identifiant = (TextView) v.findViewById(R.id.identifiantProgramList);


                Toast.makeText(Recycle.this, identifiant.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position, View v) {
            }
        });




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