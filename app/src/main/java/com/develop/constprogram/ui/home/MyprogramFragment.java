package com.develop.constprogram.ui.home;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.develop.constprogram.Authentication;
import com.develop.constprogram.EventInformationFirstActivity;
import com.develop.constprogram.EventInformationFirstFragment;
import com.develop.constprogram.NavDrawerActivity;
import com.develop.constprogram.ProgramAdapter;
import com.develop.constprogram.ProgramModel;
import com.develop.constprogram.R;
import com.develop.constprogram.XmlClickable;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

/*/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyprogramFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyprogramFragment extends Fragment implements XmlClickable {



    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference programRef=db.collection("program");
    private ProgramAdapter adapter;
    private String fragementName;
    FirebaseUser user;
    FloatingActionButton add_more_program;
    XmlClickable myProgramClick;
    FloatingActionButton fButton;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_myprogram, container, false);
        fragementName="MyProgram";
        fButton=view.findViewById(R.id.add_more_program);
        setUpRecyclerView(view,fragementName);

//        view.findViewById(R.id.add_more_program)
//                .setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        startActivity(new Intent(getActivity(),EventInformationFirstFragment.class));
//                    }
//                });
        return view;
    }



    private void setUpRecyclerView(View view, String fragment) {
        //Query query= programRef;
        user = FirebaseAuth.getInstance().getCurrentUser();

        Query query = programRef.whereEqualTo("userIdentity", user.getUid());

        FirestoreRecyclerOptions<ProgramModel> options= new FirestoreRecyclerOptions.Builder<ProgramModel>()
                .setQuery(query,ProgramModel.class)
                .build();
        adapter=new ProgramAdapter(options,fragment);

        RecyclerView recyclerView = view.findViewById(R.id.myProgram);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new ProgramAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                //TextView identifiant = (TextView) v.findViewById(R.id.identifiantProgramList);


                //Toast.makeText(getContext(), identifiant.getText(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void onItemLongClick(int position, View v) {
            }
        });
    }



    @Override
    public void onStart() {
        super.onStart();
        adapter.startListening();
    }

    @Override
    public void onStop() {
        super.onStop();
        adapter.stopListening();
    }



    @Override
    public void addMoreProgram() {


                startActivity(new Intent(getContext(),EventInformationFirstFragment.class));

        }



    public interface XmlClickableTransfert{
        public void addMoreProgram();
    }
}