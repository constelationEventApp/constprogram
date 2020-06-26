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
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.OnProgressListener;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.StorageTask;
import com.google.firebase.storage.UploadTask;
import com.squareup.picasso.Picasso;

public class ImageToUploadAndDisplay extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST=1;
    private Button mButtonChooser, mButtonUpload, mButtonShowUpload;
    private ImageButton mImageField;
    private TextView mImageLink;
   private ProgressBar mProgressBar;

   private Uri mImageUri;

   private StorageReference mStorageRef;
   //private DatabaseReference mDatabaseRef;
   CollectionReference mFireStore;
   private StorageTask mUploadTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_to_upload_and_display);

        mButtonChooser=findViewById(R.id.chooseImage);
        mButtonUpload=findViewById(R.id.uploadImage);
        mButtonShowUpload=findViewById(R.id.showImage);
        mImageField=findViewById(R.id.placeImage);
        mImageLink=findViewById(R.id.imageLink);
        mProgressBar=findViewById(R.id.uploadImageProgressBar);

        mStorageRef= FirebaseStorage.getInstance().getReference("uploads");
        mFireStore=FirebaseFirestore.getInstance().collection("uploads");
        //mDatabaseRef= FirebaseDatabase.getInstance().getReference("uploads");



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
            uploadFile();
        }

    }

    private String getFileExtension(Uri uri){
        ContentResolver cR= getContentResolver();
        MimeTypeMap mime= MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    private void uploadFile() {
        if(mImageUri != null){
            StorageReference fileReference= mStorageRef.child(System.currentTimeMillis()+"."+getFileExtension(mImageUri));
            mUploadTask=fileReference.putFile(mImageUri)
                    .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                            Handler handler= new Handler();
                            handler.postDelayed(new Runnable() {
                                @Override
                                public void run() {
                                    mProgressBar.setProgress(0);

                                }
                            }, 5000);

                            Toast.makeText(ImageToUploadAndDisplay.this, "upload success", Toast.LENGTH_LONG).show();

                            UploadImage uploadImage= new UploadImage(mImageLink.getText().toString().trim(), taskSnapshot.getUploadSessionUri().toString());
                            mFireStore.add(uploadImage);

                          //  String uploadId= mDatabaseRef.push().getKey();
                            //mDatabaseRef.child(uploadId).setValue(uploadImage);

                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {

                            Toast.makeText(ImageToUploadAndDisplay.this, e.getMessage(), Toast.LENGTH_SHORT).show();

                        }
                    })
                    .addOnProgressListener(new OnProgressListener<UploadTask.TaskSnapshot>() {
                        @Override
                        public void onProgress(@NonNull UploadTask.TaskSnapshot taskSnapshot) {
                            double progress = (100.0 * taskSnapshot.getBytesTransferred()/taskSnapshot.getTotalByteCount());
                            mProgressBar.setProgress((int)progress);

                        }
                    });

        }else{
            Toast.makeText(this, "No file selected", Toast.LENGTH_SHORT).show();
        }
    }

    public void onButtonShowClick(View view){

    }
}