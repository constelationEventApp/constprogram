package com.develop.constprogram;

import android.util.Log;

import androidx.annotation.NonNull;

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

public class WhoIFollowModel {
    private String organizerIdentity;
    private CollectionReference mFireStore;
    private FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    private String TAG="WhoIFollowModel";



    public WhoIFollowModel(String organizerIdentity) {
        this.organizerIdentity = organizerIdentity;
        mFireStore= FirebaseFirestore.getInstance().collection("users");

    }
    public  WhoIFollowModel(){
        mFireStore= FirebaseFirestore.getInstance().collection("users");


    }

    public void iFollowYou(final String organizerName){
        WhoIFollowModel whoIFollowModel = new WhoIFollowModel();
        whoIFollowModel.setOrganizerIdentity(organizerName);
        mFireStore.document(user.getUid())
                .collection("whoifollow");
        mFireStore.document(organizerName)
                .set(whoIFollowModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written into whoIFollow!");
                        WhoFollowMeModel whoFollowMeModel= new WhoFollowMeModel();
                        whoFollowMeModel.youFollowMe(organizerName);

                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                    }
                });
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
