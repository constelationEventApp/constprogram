package com.develop.constprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.List;

public class ListProgram extends AppCompatActivity {


    ListView list;
    SearchView search;
    CustomProgramListModelControl adapter;
    public  ListProgram CustomListView = null;
    public  ArrayList<CustomProgramListModel> CustomListViewValuesArr;
    public ArrayList<UploadImage> upload;

    private DatabaseReference mDatabaseRef;
    private List<UploadImage> mUpload;
    String test;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_program);


        CustomListView = this;
        CustomListViewValuesArr= new ArrayList<CustomProgramListModel>();
        upload = new ArrayList<UploadImage>();

        search=(SearchView) findViewById(R.id.search);


        /******** Take some data in Arraylist ( CustomListViewValuesArr ) ***********/
        setListData();
        list= ( ListView )findViewById( R.id.list_program );  // List defined in XML ( See Below )
        list.setDivider(null);
        list.setDividerHeight(5);

        mUpload=new ArrayList<>();
        mDatabaseRef= FirebaseDatabase.getInstance().getReference("uploadImages");
        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for(DataSnapshot postSnapShot: dataSnapshot.getChildren()){
                    UploadImage uploadImage = postSnapShot.getValue(UploadImage.class);
                    mUpload.add(uploadImage);
                    test=mUpload.get(0).getmImageUrl();
                    Toast.makeText(getApplicationContext(), test, Toast.LENGTH_LONG).show();

                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(ListProgram.this, databaseError.getMessage(), Toast.LENGTH_LONG).show();

            }
        });

        /**************** Create Custom Adapter *********/
        adapter = new CustomProgramListModelControl(ListProgram.this, (ArrayList<CustomProgramListModel>) CustomListViewValuesArr) ;
        adapter.notifyDataSetChanged();
        list.setAdapter(adapter);

        //Setting Up Listener
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {

            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // TODO Auto-generated method stub


                TextView identifiant = (TextView) view.findViewById(R.id.identifiantProgramList);
                Toast.makeText(getApplicationContext(), test, Toast.LENGTH_SHORT).show();
              //  intent = new Intent(MainActivity.this, PlayAudioFile.class);
              //  startActivity(intent);

            }
        });

        search.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {

                // Log.e("Main"," data search: "+newText);
                adapter.getFilter().filter(newText);
                return false;
            }
        });
    }

    public void setListData(){

        CustomProgramListModel model= new CustomProgramListModel();
        model.setProgramAddress("Cool River");
        model.setProgramDate("Lundi 25");
        model.setProgramName("Dance Relax");
        model.setProgramIdentity("327329");
        CustomListViewValuesArr.add( model );

        CustomProgramListModel model1= new CustomProgramListModel();
        model1.setProgramAddress("Leogane, Place anacaona");
        model1.setProgramDate("Mardi 26");
        model1.setProgramName("Bel Negres");
        model1.setProgramIdentity("83293283");
        //CustomListViewValuesArr.add( model1 );

        CustomProgramListModel model2= new CustomProgramListModel();
        model2.setProgramAddress("Grand Groave");
        model2.setProgramDate("Mercredi 27");
        model2.setProgramName("Cool beach");
        model2.setProgramIdentity("8949032");
        //CustomListViewValuesArr.add( model2 );
    }
}