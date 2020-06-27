package com.develop.constprogram;

import android.content.Context;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import static com.firebase.ui.auth.AuthUI.getApplicationContext;

public class UserInfoModel {
    private String uFirstname, uLastname, uEmail, uTitle, uCompany, uGender, uImage, uIdentity;
    private StorageReference mStorageRef;
    CollectionReference mFireStore;
    private Context context;
    private ProgramModel programModel;




    //Constructors

    public UserInfoModel(String uFirstname, String uLastname, String uEmail, String uTitle, String uCompany, String uGender, String uImage, String uIdentity) {
        this.uFirstname = uFirstname;
        this.uLastname = uLastname;
        this.uEmail = uEmail;
        this.uTitle = uTitle;
        this.uCompany = uCompany;
        this.uGender = uGender;
        this.uImage = uImage;
        this.uIdentity=uIdentity;
       // mStorageRef= FirebaseStorage.getInstance().getReference("uploads");
        mFireStore= FirebaseFirestore.getInstance().collection("users");
    }

    public UserInfoModel(){
        //It is important dont delete
        mFireStore= FirebaseFirestore.getInstance().collection("users");
    }

    //Inserting user to Firesore

    public void insertUser(UserInfoModel user){

        mFireStore.document(user.getuIdentity()).set(user)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                    }
                });
    }


    //Method
    public void insertUserProgram(String programId,String userId){
        DocumentReference userDocument=mFireStore.document(userId);
        UserProgramModel userProgramModel=new UserProgramModel();
        userProgramModel.setProgramIdentifiant(programId);
        CollectionReference myProgram=userDocument.collection("myprogram");
        myProgram.add(userProgramModel);




    }

    //Getters and Setters

    public String getuFirstname() {
        return uFirstname;
    }

    public void setuFirstname(String uFirstname) {
        this.uFirstname = uFirstname;
    }

    public String getuLastname() {
        return uLastname;
    }

    public void setuLastname(String uLastname) {
        this.uLastname = uLastname;
    }

    public String getuEmail() {
        return uEmail;
    }

    public void setuEmail(String uEmail) {
        this.uEmail = uEmail;
    }

    public String getuTitle() {
        return uTitle;
    }

    public void setuTitle(String uTitle) {
        this.uTitle = uTitle;
    }

    public String getuCompany() {
        return uCompany;
    }

    public void setuCompany(String uCompany) {
        this.uCompany = uCompany;
    }

    public String getuGender() {
        return uGender;
    }

    public void setuGender(String uGender) {
        this.uGender = uGender;
    }

    public String getuImage() {
        return uImage;
    }

    public void setuImage(String uImage) {
        this.uImage = uImage;
    }

    public String getuIdentity() {
        return uIdentity;
    }

    public void setuIdentity(String uIdentity) {
        this.uIdentity = uIdentity;
    }
}
