package com.develop.constprogram.ui;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.develop.constprogram.OrganizerAdapter;
import com.develop.constprogram.OrganizerModel;
import com.develop.constprogram.ProgramAdapter;
import com.develop.constprogram.ProgramModel;
import com.develop.constprogram.R;
import com.firebase.ui.firestore.FirestoreRecyclerOptions;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;

import static com.facebook.FacebookSdk.getApplicationContext;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WhoToFollowFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WhoToFollowFragment extends Fragment {

    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public WhoToFollowFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment WhoToFollowFragment.
     */
    // TODO: Rename and change types and number of parameters
    public static WhoToFollowFragment newInstance(String param1, String param2) {
        WhoToFollowFragment fragment = new WhoToFollowFragment();
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

    private FirebaseFirestore db = FirebaseFirestore.getInstance();
    private CollectionReference programRef=db.collection("organizer");
    private OrganizerAdapter adapter;
    private String fragmentName;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_who_to_follow, container, false);
        fragmentName="whoToFollow";
        setUpRecyclerView(view,fragmentName);
        return view;
    }

    private void setUpRecyclerView(View view, String fragment) {
        Query query= programRef;
        FirestoreRecyclerOptions<OrganizerModel> options= new FirestoreRecyclerOptions.Builder<OrganizerModel>()
                .setQuery(query,OrganizerModel.class)
                .build();
        adapter=new OrganizerAdapter(options,fragment);

        RecyclerView recyclerView = view.findViewById(R.id.id_organizer_list);
        recyclerView.setHasFixedSize(true);

        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        recyclerView.setAdapter(adapter);

        adapter.setOnItemClickListener(new OrganizerAdapter.ClickListener() {
            @Override
            public void onItemClick(int position, View v) {

                TextView identifiant = (TextView) v.findViewById(R.id.identifiantOrganizerList);

                Toast.makeText(getActivity(), identifiant.getText(), Toast.LENGTH_LONG).show();

                //  Toast.makeText(Recycle.this, identifiant.getText(), Toast.LENGTH_SHORT).show();
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