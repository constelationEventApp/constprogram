package com.develop.constprogram;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class EventInformationFirstActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener
    {
        private static final String TAG ="EventInformationFirstPageActivity";
        private TextView mDisplayStartDate, mDisplayStartTime;
        private TextView mDisplayEndDate, mDisplayEndTime;
        private DatePickerDialog.OnDateSetListener mDateSetListener, mDateSetListener2;
        private boolean temoinDate;
        private boolean temoinTime;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_event_information_first);

            mDisplayStartDate = (TextView) findViewById(R.id.start_date_id_info);
            mDisplayEndDate = (TextView) findViewById(R.id.end_date_id_info);
            mDisplayStartTime = (TextView) findViewById(R.id.start_time_id_info);
            mDisplayEndTime = (TextView) findViewById(R.id.end_time_id_info);

            mDisplayStartTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DialogFragment timepicker = new TimePickerFragment();
                    timepicker.show(getSupportFragmentManager(),"time picker");
                    temoinTime = true;
                }
            });
            mDisplayEndTime.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    DialogFragment timepicker = new TimePickerFragment();
                    timepicker.show(getSupportFragmentManager(),"time picker");
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
                    EventInformationFirstActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener,
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
                    EventInformationFirstActivity.this, android.R.style.Theme_Holo_Light_Dialog_MinWidth,mDateSetListener2,
                    year, month, day );
            dialog.getWindow().setBackgroundDrawable(new ColorDrawable(Color.TRANSPARENT));
            dialog.show();
//        Toast.makeText(this, aff, Toast.LENGTH_LONG).show();

        }

        @Override
        public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
            TextView start = (TextView) findViewById(R.id.start_time_id_info);
            TextView end = (TextView) findViewById(R.id.end_time_id_info);
            String time = hourOfDay + " : " + minute;
            if(temoinTime){
                start.setText(time);
            }
            else {
                end.setText(time);
            }
        }

        //onClick Next Button
        public  void onClickNextButton (View view){
            Intent home = new Intent(EventInformationFirstActivity.this, EventInformationSecondActivity.class);
            startActivity(home);
            finish();
        }
    }


