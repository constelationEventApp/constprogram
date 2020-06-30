package com.develop.constprogram.ui;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.develop.constprogram.R;

public class MethodPaymentFragment extends Fragment {
    private onButtonPaymentFragmentSelected listenerPayment;
    Button addCardPaymentBtn;
    Button otherSettingPaymentBtn;
    @Override
    public void onAttach(@NonNull Context context) {
        super.onAttach(context);
        if(context instanceof MethodPaymentFragment.onButtonPaymentFragmentSelected){
            listenerPayment = (MethodPaymentFragment.onButtonPaymentFragmentSelected) context;

        } else{
            throw new ClassCastException(context.toString()+ "must implement listener");
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment

        View view=inflater.inflate(R.layout.fragment_method_payment, container, false);
        addCardPaymentBtn = (Button) view.findViewById(R.id.id_add_card_payment);
        addCardPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerPayment.addCardPaymentButton();
            }
        });

        otherSettingPaymentBtn = (Button) view.findViewById(R.id.id_other_setting_payment);
        otherSettingPaymentBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listenerPayment.otherSettingPaymentButton();
            }
        });
        return view;
    }
    public interface onButtonPaymentFragmentSelected{
        public void addCardPaymentButton();
        public void otherSettingPaymentButton();
    }
}