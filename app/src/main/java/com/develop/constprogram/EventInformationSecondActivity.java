package com.develop.constprogram;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.firebase.ui.auth.AuthUI;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.EmailAuthProvider;
import com.google.firebase.auth.FacebookAuthProvider;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
import com.google.firebase.auth.UserInfo;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.ThrowOnExtraProperties;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

import java.util.List;

public class EventInformationSecondActivity extends AppCompatActivity {

    private static final int PICK_IMAGE_REQUEST=1;
    Bundle extras;
    ProgramModel programModel;
    String tvFirstname, tvLastname, tvEmail,tvTitle, tvCompany, imageUrl, userId, phoneNumber, userProvider;
    FirebaseUser user;

   // ImageToUploadAndDisplay imageToUploadAndDisplay;

    private ImageButton mImageField;
    private TextView mImageLink;
    private ProgressBar mProgressBar;
    private EditText mProgramSumary;

    private Uri mImageUri;

    private StorageReference mStorageRef;
    //private DatabaseReference mDatabaseRef;
    CollectionReference mFireStore;
    private StorageTask mUploadTask;
    private String imageStoragePath;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_information_second);

        mImageField=findViewById(R.id.placeImage);
        mImageLink=findViewById(R.id.imageLink);
        mProgressBar=findViewById(R.id.uploadImageProgressBar);
        mProgramSumary=findViewById(R.id.event_summary_id_info);


        mStorageRef= FirebaseStorage.getInstance().getReference("uploads");
        mFireStore= FirebaseFirestore.getInstance().collection("uploads");
         extras = getIntent().getExtras();
         programModel= new ProgramModel();

       /* user =FirebaseAuth.getInstance().getCurrentUser();
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
        }*/


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

    public void onButtonChooseClick(View view){
        openFileChooser();

    }

    private void openFileChooser() {
        Intent intent= new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode==PICK_IMAGE_REQUEST && resultCode== RESULT_OK && data != null && data.getData()!=null){
            mImageUri = data.getData();
            Picasso.get().load(mImageUri).fit().centerCrop().into(mImageField);

        }
    }
    public void onButtonUploadClick(View view){
        if(mUploadTask!=null && mUploadTask.isInProgress()){
            Toast.makeText(this, "Upload is in progress", Toast.LENGTH_LONG).show();

        }else{
            uploadData();
        }

    }
    private String getFileExtension(Uri uri){
        ContentResolver cR= getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadData() {
        if(mImageUri != null){
            final StorageReference fileReference= mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
            mUploadTask=fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Task <Uri> downloadUri=taskSnapshot.getStorage().getDownloadUrl();
                          fileReference.getDownloadUrl()
                                  .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                      @Override
                                      public void onSuccess(Uri uri) {
                                          imageStoragePath=uri.toString();
                                          insertEventInfo();
                                      }
                                  });



                            Toast.makeText(EventInformationSecondActivity.this, "upload success", Toast.LENGTH_LONG).show();

                            UploadImage uploadImage= new UploadImage("Image", taskSnapshot.getUploadSessionUri().toString());
                            mFireStore.add(uploadImage);

                            //  String uploadId= mDatabaseRef.push().getKey();
                            //mDatabaseRef.child(uploadId).setValue(uploadImage);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(EventInformationSecondActivity.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                           // double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            //mProgressBar.setProgress((int)progress);

                        }
                    });

        }else{
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void insertEventInfo(){

        user =FirebaseAuth.getInstance().getCurrentUser();

        Toast.makeText(this, "Inside Event Info", Toast.LENGTH_SHORT).show();
        programModel.setProgramName(extras.get("title").toString());
        programModel.setProgramTypeOfEvent(extras.get("type").toString());
        programModel.setProgramOrganizer(extras.get("organizer").toString());
        programModel.setProgramAddress(extras.get("address").toString());
        programModel.setProgramStartDate(extras.get("startDate").toString());
        programModel.setProgramEndDate(extras.get("endDate").toString());
        programModel.setProgramStartTime(extras.get("startTime").toString());
        programModel.setProgramEndTime(extras.get("endTime").toString());
        programModel.setProgramSummary(mProgramSumary.getText().toString());
        programModel.setProgramImage(imageStoragePath);
        //programModel.setProgramIdentity(user.getUid());

        programModel.insertProgram(programModel, user.getUid());



    }


}