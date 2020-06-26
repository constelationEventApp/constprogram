package com.develop.constprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;

import java.util.List;

public class EventInformationSecondActivity extends AppCompatActivity {
    Bundle extras;
    String fullname, email, imageUrl, userId, phoneNumber, userProvider;
    FirebaseUser user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_information_second);
         extras = getIntent().getExtras();

        user =FirebaseAuth.getInstance().getCurrentUser();
        List<? extends UserInfo> infos = user.getProviderData();
        for (UserInfo ui : infos) {
            switch (ui.getProviderId()){
                case GoogleAuthProvider.PROVIDER_ID:
                    Toast.makeText(EventInformationSecondActivity.this, "userProvider: "+ user.getDisplayName(), Toast.LENGTH_LONG).show();
                    break;
                case FacebookAuthProvider.PROVIDER_ID:
                    userProvider="Facebook";
                    Toast.makeText(EventInformationSecondActivity.this, "FaceProvider: "+ user.getDisplayName()+ "linkPhoto"+ user.getPhotoUrl(), Toast.LENGTH_LONG).show();

                    break;
                case EmailAuthProvider.PROVIDER_ID:
                    userProvider="Email";
                    break;
                default:
                    userProvider="Telephone";
                    Toast.makeText(EventInformationSecondActivity.this, "userInfo: "+ user.getPhoneNumber(), Toast.LENGTH_LONG).show();

            }
        }

        if (extras != null) {
            /*switch (extras.getString("userProvider")){
                case "Google":
                    break;
                case "Facebook":
                    break;
                case "Email":
                    break;
                default:
            }*/
            fullname=extras.getString("fullname");
            email= extras.getString("email");
            phoneNumber=extras.getString("phoneNumber");
            imageUrl= extras.getString("imageUrl");
            userId= extras.getString("userId");
            userProvider= extras.getString("userProvider :");


            //The key argument here must match that used in the other activity
        }
    }
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(EventInformationSecondActivity.this, EventInformationFirstActivity.class);
        startActivity(intent);
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
}