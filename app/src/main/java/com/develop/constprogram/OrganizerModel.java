package com.develop.constprogram;

import android.nfc.Tag;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
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
    CollectionReference mFireStore;

    String TAG ="OrganizerModel";



    public OrganizerModel(String organizerName, String organizerIdentity) {
        this.organizerName = organizerName;
        this.organizerIdentity=organizerIdentity;
        mFireStore= FirebaseFirestore.getInstance().collection("organizer");
    }

    public OrganizerModel (){
        mFireStore= FirebaseFirestore.getInstance().collection("organizer");

    }

    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public void insertOrganizer(final OrganizerModel organizerModel, final String idProgram){
        //Test if organizer is already insert
        DocumentReference docRef = mFireStore.document(organizerModel.getOrganizerName());
        docRef.get().addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
            @Override
            public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                if (task.isSuccessful()) {
                    DocumentSnapshot document = task.getResult();
                    if (document.exists()) {
                        Log.d(TAG, "Organiser Already inserted");
                    } else {
                        Log.d(TAG, "No such document");
                    //No such document insert
                        mFireStore.document(organizerModel.getOrganizerName()).set(organizerModel)
                                .addOnSuccessListener(new OnSuccessListener<Void>() {
                                    @Override
                                    public void onSuccess(Void aVoid) {
                                        Log.d(TAG, "DocumentSnapshot successfully written!");
                                        Log.d(TAG, "Insert organizer is successful");
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        //Log.w(TAG, "Error adding document", e);
                                    }
                                });
                        //end insert

                    }
                } else {
                    Log.d(TAG, "get failed with ", task.getException());
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
