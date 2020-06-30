package com.develop.constprogram.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.develop.constprogram.R;

import java.util.ArrayList;

import static com.develop.constprogram.R.layout.fragment_help_comment;

public class HelpCommentFragment extends Fragment {
    private onButtonHelpCommentFragmentSelected listener;
    Button sendCommentBtn;
    TextView allArticleTv;
    ListView articleList;
    ArrayList <String> mArrayList;
    ArrayAdapter mAdapter;
    ArrayList<String> nomListe;


    ArrayAdapter mAdapter2;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onButtonHelpCommentFragmentSelected){
            listener = (onButtonHelpCommentFragmentSelected) context;

        } else{
            throw new ClassCastException(context.toString()+ "must implement listener");
        }

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view= inflater.inflate(fragment_help_comment, container, false) ;
       final String  article [] = {"Bonjour", "Bonsoir"};
        articleList= (ListView) view.findViewById(R.id.id_listview_help_comment);

        sendCommentBtn = (Button) view.findViewById(R.id.id_send_comment);
        sendCommentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.sendCommentButton();
            }
        });

        allArticleTv = (TextView) view.findViewById(R.id.id_toutes_articles);
        allArticleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.allArticleTv();
            }
        });




        return view;
    }
    public interface onButtonHelpCommentFragmentSelected {
        public void allArticleTv();
        public void sendCommentButton();



    }
}