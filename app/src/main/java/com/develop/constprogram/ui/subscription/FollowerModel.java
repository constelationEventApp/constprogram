package com.develop.constprogram.ui.subscription;


import android.util.Log;

import androidx.annotation.NonNull;

import com.develop.constprogram.WhoFollowMeModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FollowerModel {
    private String organizerIdentity;
    private CollectionReference mFireStore;
    private FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    private String TAG="WhoIFollowModel";



    public FollowerModel(String organizerIdentity) {
        this.organizerIdentity = organizerIdentity;
        mFireStore= FirebaseFirestore.getInstance().collection("users");

    }
    public  FollowerModel(){
        mFireStore= FirebaseFirestore.getInstance().collection("users");


    }

    public void iFollowYou(final String organizerName){
        com.develop.constprogram.WhoIFollowModel whoIFollowModel = new com.develop.constprogram.WhoIFollowModel();
        whoIFollowModel.setOrganizerIdentity(organizerName);
        mFireStore.document(user.getUid())
                .collection("whoifollow")
                .add(whoIFollowModel);
    }


    public void iUnFollowYou(final String organizerName){
        mFireStore.document(user.getUid())
                .collection("whoifollow");
        mFireStore.document(organizerName).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "I unFollowYou");
                        WhoFollowMeModel whoFollowMeModel= new WhoFollowMeModel();
                        whoFollowMeModel.youUnFollowMe(organizerName);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });

    }




    public void getWhoIFollow(){
        mFireStore.document(user.getUid())
                .collection("whoifollow") .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
    }



    public String getOrganizerIdentity() {
        return organizerIdentity;
    }

    public void setOrganizerIdentity(String organizerIdentity) {
        this.organizerIdentity = organizerIdentity;
    }

}

