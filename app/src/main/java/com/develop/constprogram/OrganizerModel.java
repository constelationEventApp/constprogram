package com.develop.constprogram;

import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

public class OrganizerModel {
    private String organizerName;
    private String organizerIdentity;
    private int organizerCounterFollower=0;
    CollectionReference mFireStore;
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();

    String TAG ="OrganizerModel";



    public OrganizerModel(String organizerName, String organizerIdentity, int organizerCounterFollower) {
        this.organizerName = organizerName;
        this.organizerIdentity=organizerIdentity;
        this.organizerCounterFollower=organizerCounterFollower;
        mFireStore= FirebaseFirestore.getInstance().collection("organizer");
    }

    public OrganizerModel (){
        mFireStore= FirebaseFirestore.getInstance().collection("organizer");

    }


    public void insertOrganizer(){
        //Test if organizer is already insert
       /* DocumentReference docRef = mFireStore.document(organizerModel.getOrganizerName());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "Organiser Already inserted");
                    } else {
                        Log.d(TAG, "No such document");*/
                    //No such document insert
                OrganizerModel organizer = new OrganizerModel();
                organizer.setOrganizerIdentity(user.getUid());
                organizer.setOrganizerName(user.getDisplayName());
                        mFireStore.document(user.getUid())
                                .set(organizer);

                                /*add(organizer)
                                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                                    @Override
                                    public void onSuccess(DocumentReference documentReference) {
                                        updateOrganizerIdentity(documentReference.getId());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //Log.w(TAG, "Error adding document", e);
                                    }
                                });
*/

                        //end insert
/*
                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
                }
            }
        });*/
    }
    public void updateOrganizerIdentity(String idDocument){
        mFireStore= FirebaseFirestore.getInstance().collection("organizer");
        mFireStore.document(idDocument)
                .update("organizerIdentity", idDocument)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Update whoIFollowdentity is successful");
                    }
                });

    }
    public String getOrganizerIdentity() {
        return organizerIdentity;
    }

    public void setOrganizerIdentity(String organizerIdentity) {
        this.organizerIdentity = organizerIdentity;
    }

    public int getOrganizerCounterFollower() {
        return organizerCounterFollower;
    }

    public void setOrganizerCounterFollower(int organizerCounterFollower) {
        this.organizerCounterFollower = organizerCounterFollower;
    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }
}
