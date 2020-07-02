package com.develop.constprogram.ui.subscription;



import android.util.Log;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FollowingModel {
    private String userIdentity;
    private String organizerIdentity;
    private FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    private CollectionReference mFireStore;
    private String TAG="Follow Model";

    public FollowingModel(String userIdentity, String organizerIdentity) {
        this.userIdentity = userIdentity;

        mFireStore= FirebaseFirestore.getInstance().collection("organizer");

    }
    public FollowingModel(){
        mFireStore= FirebaseFirestore.getInstance().collection("organizer");


    }

    public void youFollowMe(String organizerName){
        com.develop.constprogram.WhoFollowMeModel followModel= new com.develop.constprogram.WhoFollowMeModel();
        followModel.setUserIdentity(user.getUid());
        mFireStore.document(organizerName)
                .collection("whofallowme")
                .document(user.getUid()).set(followModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Yes you fallow me");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                    }
                });

    }

    public void youUnFollowMe(String organizerName){
        mFireStore.document(organizerName)
                .collection("whofallowme")
                .document(user.getUid()).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Yes you unfollow me");
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });
    }


    /*public void unFollowOrganizer(String organizerName){
        mFireStore.document(organizerName)
                .collection(w)

    }*/
    public void getWhoFollowMe(){
        mFireStore.document(user.getDisplayName())
                .collection("whofallowme")
                .get()
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




    public String getUserIdentity() {
        return userIdentity;
    }

    public void setUserIdentity(String userIdentity) {
        this.userIdentity = userIdentity;
    }
}

