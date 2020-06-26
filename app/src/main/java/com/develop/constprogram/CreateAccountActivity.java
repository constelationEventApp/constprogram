package com.develop.constprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.squareup.picasso.Picasso;

import java.util.List;

public class CreateAccountActivity extends AppCompatActivity {

    FirebaseUser user;
    TextView tvFirstname, tvLastname, tvEmail, tvTitle, tvCompany;
    ImageView imageProfile;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_account);

        tvFirstname = (TextView) findViewById(R.id.txt_fname_id_create_account);
        tvLastname = (TextView) findViewById(R.id.txt_lname_id_create_account);
        tvEmail = (TextView) findViewById(R.id.txt_email_id_create_account);
        tvTitle = (TextView) findViewById(R.id.txt_title_id_create_account);
        tvCompany = (TextView) findViewById(R.id.txt_company_id_create_account);
        imageProfile=(ImageView) findViewById(R.id.imageProfile);



        user = FirebaseAuth.getInstance().getCurrentUser();
        List<? extends UserInfo> infos = user.getProviderData();
        for (UserInfo ui : infos) {
            switch (ui.getProviderId()){
                case GoogleAuthProvider.PROVIDER_ID: case FacebookAuthProvider.PROVIDER_ID:
                    tvFirstname.setText(user.getDisplayName().split(" ")[0]);
                    tvLastname.setText(user.getDisplayName().split(" ")[1]);
                    tvEmail.setText(user.getEmail());
                    Picasso.get().load(user.getPhotoUrl())
                            .placeholder(R.drawable.image_holder)
                            .fit().centerCrop().into(imageProfile);
                    Toast.makeText(CreateAccountActivity.this, "userProvider: "+ user.getDisplayName(), Toast.LENGTH_LONG).show();
                    break;

                case EmailAuthProvider.PROVIDER_ID:
                    tvFirstname.setText(user.getDisplayName().split(" ")[0]);
                    tvLastname.setText(user.getDisplayName().split(" ")[1]);
                    tvEmail.setText(user.getEmail());

                    break;
                default:
                    Toast.makeText(CreateAccountActivity.this, "userInfo: "+ user.getPhoneNumber(), Toast.LENGTH_LONG).show();

            }
        }
    }
    @Override
    public void onBackPressed() {
        cancelmessage();
    }
    public void cancelmessage(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Cancel");
        // set message
        builder.setMessage("Do you really want to cancel the registration ?");
        // set icon
        builder.setIcon(R.drawable.close);
        // set cancelable
        builder.setCancelable(true);
        // set Yes and No button
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                Intent intent = new Intent(CreateAccountActivity.this, SignUpActivity.class);
                startActivity(intent);
                finish();
            }
        });
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        // create dialog
        AlertDialog alertDialog = builder.create();
        // show dialog
        alertDialog.show();
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

    public void finishCreateAccountProcess(View view){
        FirebaseUserMetadata metadata = user.getMetadata();
        if (metadata.getCreationTimestamp() == metadata.getLastSignInTimestamp()) {
            // The user is new,
            startActivity(new Intent(getApplicationContext(),Dashboard.class));
        } else {
            startActivity(new Intent(getApplicationContext(),Recycle.class));
            finish();
        }
    }
}