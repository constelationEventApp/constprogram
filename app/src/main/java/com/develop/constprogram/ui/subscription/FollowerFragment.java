package com.develop.constprogram.ui.subscription;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.develop.constprogram.ui.subscription.FollowerAdapter;
import com.develop.constprogram.ui.subscription.FollowerModel;
import com.develop.constprogram.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link FollowerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class FollowerFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public FollowerFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment FollowerFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static FollowerFragment newInstance(String param1, String param2) {
        FollowerFragment fragment = new FollowerFragment();
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
    }

    private CollectionReference mFireStore=FirebaseFirestore.getInstance()
            .collection("users");

    private FollowerAdapter adapter;
    private String fragmentName;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_follower, container, false);
        fragmentName="Follower";
        setUpRecyclerView(view,fragmentName);
        return view;
    }
    private void setUpRecyclerView(View view, String fragment) {
         FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

        Query query=  mFireStore.document(user.getUid())
                .collection("whoifollow");
        FirestoreRecyclerOptions<FollowerModel> options= new FirestoreRecyclerOptions.Builder<FollowerModel>()
                .setQuery(query,FollowerModel.class)
                .build();
        adapter=new FollowerAdapter(options,fragment);

        RecyclerView recyclerView = view.findViewById(R.id.follower);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new FollowerAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                TextView organizerId=v.findViewById(R.id.organizerId);
                Toast.makeText(getActivity(),organizerId.getText().toString(), Toast.LENGTH_LONG).show();


                  //Toast.makeText(Recycle.this, identifiant.getText(), Toast.LENGTH_SHORT).show();
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