package com.develop.constprogram.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.develop.constprogram.R;

public class LanguageSettingFragment extends Fragment {
    private onButtonLanguageSettingFragmentSelected listener;
    TextView englishTv, spanishTv, creoleTv, frenchTv;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view=inflater.inflate(R.layout.fragment_language_setting, container, false);
        englishTv = (TextView) view.findViewById(R.id.id_english_language_setting);
        englishTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.englishButton();
            }
        });

        frenchTv=(TextView) view.findViewById(R.id.id_french_language_setting);
        frenchTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.frenchButton();
            }
        });

        creoleTv=(TextView) view.findViewById(R.id.id_creole_language_setting);
        creoleTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.creoleButton();
            }
        });

        spanishTv=(TextView) view.findViewById(R.id.id_spanish_language_setting);
        spanishTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.spanishButton();
            }
        });
        return view;
    }
    public interface onButtonLanguageSettingFragmentSelected {
        public void englishButton();
        public void frenchButton();
        public void spanishButton();
        public void creoleButton();

    }
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onButtonLanguageSettingFragmentSelected){
            listener = (onButtonLanguageSettingFragmentSelected) context;

        } else{
            throw new ClassCastException(context.toString()+ "must implement listener");
        }

    }
}