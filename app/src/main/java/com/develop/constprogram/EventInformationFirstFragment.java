package com.develop.constprogram;

import android.app.DatePickerDialog;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;

import androidx.fragment.app.DialogFragment;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TextView;

import java.util.Calendar;


public class EventInformationFirstFragment extends Fragment {

    private TextView mDisplayStartDate, mDisplayStartTime;
    private TextView mDisplayEndDate, mDisplayEndTime;
    private DatePickerDialog.OnDateSetListener mDateSetListener, mDateSetListener2;
    private boolean temoinDate;
    private boolean temoinTime;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {


        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_event_information_first, container, false);


        initializeValue(view);

        return view;
    }

    private void initializeValue(View view) {
        mDisplayStartDate = (TextView) view.findViewById(R.id.start_date_id_info);
        mDisplayEndDate = (TextView) view.findViewById(R.id.end_date_id_info);
        mDisplayStartTime = (TextView) view.findViewById(R.id.start_time_id_info);
        mDisplayEndTime = (TextView) view.findViewById(R.id.end_time_id_info);

        mDisplayStartTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment timepicker = new TimePickerFragment();
                timepicker.show(getActivity().getSupportFragmentManager(),"time picker");
                temoinTime = true;
                Calendar cal =Calendar.getInstance();
                int year = cal.get(Calendar.YEAR);
                int month = cal.get(Calendar.MONTH);
                int day = cal.get(Calendar.DAY_OF_MONTH);


                DatePickerDialog dialog = new DatePickerDialog(
                        getActivity(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,
                        year, month, day );
                dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
                dialog.show();
//        Toast.makeText(this, aff, Toast.LENGTH_LONG).show();
            }
        });
        mDisplayEndTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                DialogFragment timepicker = new TimePickerFragment();
                timepicker.show(getActivity().getSupportFragmentManager(),"time picker");
                temoinTime = false;

            }
        });

        mDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String date = month +"/" + dayOfMonth + "/" + year;
                mDisplayStartDate.setText(date);

            }
        };
        mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                month++;
                String date = month +"/" + dayOfMonth + "/" + year;
                mDisplayEndDate.setText(date);
            }
        };

    }

    public void onClickDate(View view){
        Calendar cal =Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);


        DatePickerDialog dialog = new DatePickerDialog(
                getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,
                year, month, day );
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
//        Toast.makeText(this, aff, Toast.LENGTH_LONG).show();

    }

    public void onClickDate2 (View view){
        Calendar cal =Calendar.getInstance();
        int year = cal.get(Calendar.YEAR);
        int month = cal.get(Calendar.MONTH);
        int day = cal.get(Calendar.DAY_OF_MONTH);

        DatePickerDialog dialog = new DatePickerDialog(
                getContext(), android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener2,
                year, month, day );
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
        dialog.show();
//        Toast.makeText(this, aff, Toast.LENGTH_LONG).show();

    }
}