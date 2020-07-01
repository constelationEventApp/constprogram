package com.develop.constprogram;

import androidx.annotation.NonNull;
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
import android.widget.EditText;
import android.widget.TextView;
import android.widget.TimePicker;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

import java.text.DateFormat;
import java.util.Calendar;

public class EventInformationFirstActivity extends AppCompatActivity implements TimePickerDialog.OnTimeSetListener
    {
        private static final String TAG ="EventInformationFirstPageActivity";
        private TextView mDisplayStartDate, mDisplayStartTime;
        private TextView mDisplayEndDate, mDisplayEndTime;
        private DatePickerDialog.OnDateSetListener mDateSetListener, mDateSetListener2;
        private boolean temoinDate;
        private boolean temoinTime;

        private EditText mEventTitle;
        private EditText mtTypeOfEvent;
        private EditText mOrganizer;
        String address="Haiti";

        FirebaseUser user;



        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_event_information_first);
            //Instanciate user
            user = FirebaseAuth.getInstance().getCurrentUser();


            mDisplayStartDate = (TextView) findViewById(R.id.start_date_id_info);
            mDisplayEndDate = (TextView) findViewById(R.id.end_date_id_info);
            mDisplayStartTime = (TextView) findViewById(R.id.start_time_id_info);
            mDisplayEndTime = (TextView) findViewById(R.id.end_time_id_info);
            mEventTitle = (EditText) findViewById(R.id.event_title_id_info);
            mtTypeOfEvent = (EditText) findViewById(R.id.event_type_id_info);
            mOrganizer = (EditText) findViewById(R.id.organizer_id_info);

            //Difault display organizer name
            mOrganizer.setText(user.getDisplayName());


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
                    Calendar c= Calendar.getInstance();
                    c.set(Calendar.MONTH, month);
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

              //      String date = month +"/" + dayOfMonth + "/" + year;
                    String date= DateFormat.getDateInstance().format(c.getTime());
                    mDisplayStartDate.setText(date);

                }
            };
            mDateSetListener2 = new DatePickerDialog.OnDateSetListener() {
                @Override
                public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                    month++;
                  //  String date = month +"/" + dayOfMonth + "/" + year;
                    Calendar c= Calendar.getInstance();
                    c.set(Calendar.MONTH, month);
                    c.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                    //      String date = month +"/" + dayOfMonth + "/" + year;
                    String date= DateFormat.getDateInstance().format(c.getTime());
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


        public void signOut(View view){
            AuthUI.getInstance()
                    .signOut(this)
                    .addOnCompleteListener(new OnCompleteListener<Void>() {

                        @Override
                        public void onComplete(@NonNull Task<Void> task) {
                            startActivity(new Intent(getApplicationContext(),Authentication.class));
                            finish();
                        }
                    });
        }

        public void onClickNext(View view){
            mDisplayStartDate = (TextView) findViewById(R.id.start_date_id_info);
            mDisplayEndDate = (TextView) findViewById(R.id.end_date_id_info);
            mDisplayStartTime = (TextView) findViewById(R.id.start_time_id_info);
            mDisplayEndTime = (TextView) findViewById(R.id.end_time_id_info);
            mEventTitle = (EditText) findViewById(R.id.event_title_id_info);
            mtTypeOfEvent = (EditText) findViewById(R.id.event_type_id_info);
            mOrganizer = (EditText) findViewById(R.id.organizer_id_info);

            Intent intent = new Intent(EventInformationFirstActivity.this, EventInformationSecondActivity.class);
            intent.putExtra("title", mEventTitle.getText().toString());
            intent.putExtra("type", mtTypeOfEvent.getText().toString());
            intent.putExtra("organizer", mOrganizer.getText().toString());
            intent.putExtra("address", address);
            intent.putExtra("startDate", mDisplayStartDate.getText().toString());
            intent.putExtra("endDate", mDisplayEndDate.getText().toString());
            intent.putExtra("startTime", mDisplayStartTime.getText().toString());
            intent.putExtra("endTime", mDisplayEndTime.getText().toString());

            startActivity(intent);

        }

    }


