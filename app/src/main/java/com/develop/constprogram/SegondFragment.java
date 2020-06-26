package com.develop.constprogram;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;


public class SegondFragment extends Fragment {



    View view;
    Button secondButton;
    private secondFragmentInterface secondInt;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        view= inflater.inflate(R.layout.fragment_segond, container, false);
        // get the reference of Button
        secondButton = (Button) view.findViewById(R.id.secondButton);

// perform setOnClickListener on second Button
        secondButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

// display a message by using a Toast
                //Toast.makeText(getContext(), "Second Activity", Toast.LENGTH_LONG).show();
                secondInt.onClickSecondButton();
            }
        });
        return view;
    }

    public interface secondFragmentInterface{

        public void onClickSecondButton();

    }
}