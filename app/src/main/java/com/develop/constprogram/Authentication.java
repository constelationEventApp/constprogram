package com.develop.constprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ErrorCodes;
import com.firebase.ui.auth.IdpResponse;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseUserMetadata;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Authentication extends AppCompatActivity {
    private FirebaseAuth mFirebaseAuth;
    private FirebaseAuth.AuthStateListener mAuthListener;
    private static final int RC_SIGN_IN=1;
    FirebaseUser user;
    String userProvider;

    String fullname, email, imageUrl, userId, phoneNumber;

    List<String> whitelistedCountries;

    List<AuthUI.IdpConfig> providers ;

    //setReadPermissions("email", "public_profile")
    //new AuthUI.IdpConfig.TwitterBuilder().build());

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_authentication);

        whitelistedCountries= new ArrayList<String>();
        whitelistedCountries.add("+509");


         providers = Arrays.asList(
                new AuthUI.IdpConfig.EmailBuilder().build(),
                new AuthUI.IdpConfig.PhoneBuilder().setWhitelistedCountries(whitelistedCountries).build(),
                new AuthUI.IdpConfig.GoogleBuilder().build(),
                new AuthUI.IdpConfig.FacebookBuilder().build());

        mFirebaseAuth=FirebaseAuth.getInstance();
        mAuthListener=new FirebaseAuth.AuthStateListener(){
            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth){
                user =FirebaseAuth.getInstance().getCurrentUser();

                if(user!=null){

                        startActivity(new Intent(getApplicationContext(),NavDrawerActivity.class));
                        finish();

                    // userProvider=firebaseAuth.getCurrentUser().getProviderData().get(0).toString();


                    //  Toast.makeText(Authentication.this, "User signed In", Toast.LENGTH_LONG).show();
                }else{
                    startActivityForResult(


                            AuthUI.getInstance()
                                    .createSignInIntentBuilder()
                                    .setAvailableProviders(providers)
                                    .setLogo(R.drawable.event_picture)
                                    .setTheme(R.style.GreenTheme)
                                    .build(),
                            RC_SIGN_IN);


                }
            }
        };
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // RC_SIGN_IN is the request code you passed into startActivityForResult(...) when starting the sign in flow.
        if (requestCode == RC_SIGN_IN) {
            IdpResponse response = IdpResponse.fromResultIntent(data);

            // Successfully signed in
            if (resultCode == RESULT_OK) {
                /*user =FirebaseAuth.getInstance().getCurrentUser();

                List<? extends UserInfo> infos = user.getProviderData();
                for (UserInfo ui : infos) {
                    switch (ui.getProviderId()){
                        case GoogleAuthProvider.PROVIDER_ID:
                            userProvider="Google";
                            break;
                        case FacebookAuthProvider.PROVIDER_ID:
                            userProvider="Facebook";
                            break;
                        case EmailAuthProvider.PROVIDER_ID:
                            userProvider="Email";
                            break;
                        default:
                            userProvider="Telephone";
                    }
                }*/

                    Intent intent =new Intent(getApplicationContext(),CreateAccountActivity.class);
                    startActivity(intent);
                    finish();

            } else {
                // Sign in failed
                if (response == null) {
                    // User pressed back button
                    //showSnackbar(R.string.sign_in_cancelled);
                    return;
                }

                if (response.getError().getErrorCode() == ErrorCodes.NO_NETWORK) {
                    // showSnackbar(R.string.no_internet_connection);
                    return;
                }

                //showSnackbar(R.string.unknown_error);
                // Log.e(TAG, "Sign-in error: ", response.getError());
            }
        }
    }


    @Override
    protected void onResume() {
        super.onResume();
        mFirebaseAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    protected void onPause() {
        super.onPause();
        mFirebaseAuth.removeAuthStateListener(mAuthListener);

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