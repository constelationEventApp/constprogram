package com.develop.constprogram.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.develop.constprogram.ProgramAdapter;
import com.develop.constprogram.ProgramModel;
import com.develop.constprogram.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/*/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MyprogramFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MyprogramFragment extends Fragment {

    /*/ TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public MyprogramFragment() {
        // Required empty public constructor
    }*/

    /*/**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment MyprogramFragment.
     */
    // TODO: Rename and change types and number of parameters
  /*  public static MyprogramFragment newInstance(String param1, String param2) {
        MyprogramFragment fragment = new MyprogramFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }*/

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference programRef=db.collection("program");
    private ProgramAdapter adapter;
    private String fragementName;
    FirebaseUser user;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_myprogram, container, false);

        fragementName="MyProgram";
        setUpRecyclerView(view,fragementName);

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

                TextView identifiant = (TextView) v.findViewById(R.id.identifiantProgramList);


                Toast.makeText(getContext(), identifiant.getText(), Toast.LENGTH_SHORT).show();
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
}