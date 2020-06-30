package com.develop.constprogram.ui.settings;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.develop.constprogram.R;

public class SettingFragment extends Fragment {
  private  onButtonSettingFragmentSelected listener;
  Button privacyBtn;
  Button useTermBtn;
    Button languageBtn;
    Button userDataPolicyBtn;
    Button copyrightBtn;
    Button logOutBtn;

    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof onButtonSettingFragmentSelected){
            listener = (onButtonSettingFragmentSelected) context;

        } else{
            throw new ClassCastException(context.toString()+ "must implement listener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_setting, container, false);
        privacyBtn = (Button) view.findViewById(R.id.id_setting_confidentiality);
        privacyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.privacyButton();
            }
        });

        languageBtn = (Button) view.findViewById(R.id.id_setting_language);
        languageBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.languageButton();
            }
        });

        useTermBtn = (Button) view.findViewById(R.id.id_setting_term_use);
        useTermBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.termUseButton();
            }
        });

        logOutBtn = (Button) view.findViewById(R.id.id_log_out_setting);
        logOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.logOutButton();
            }
        });

        userDataPolicyBtn = (Button) view.findViewById(R.id.id_setting_user_data_politic);
        userDataPolicyBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.userDataPolicyButton();
            }
        });

        copyrightBtn = (Button) view.findViewById(R.id.id_setting_copyright);
        copyrightBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.copyrightButton();
            }
        });



        return view;
    }
    public interface onButtonSettingFragmentSelected {
        public void privacyButton();
        public void languageButton();
        public void termUseButton();
        public void userDataPolicyButton();
        public void copyrightButton();
        public  void logOutButton();


    }
}