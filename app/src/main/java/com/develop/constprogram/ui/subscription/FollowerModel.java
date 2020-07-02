package com.develop.constprogram.ui.subscription;


import android.util.Log;

import androidx.annotation.NonNull;

import com.develop.constprogram.OrganizerModel;
import com.develop.constprogram.UserInfoModel;
import com.develop.constprogram.WhoFollowMeModel;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

public class FollowerModel {
    private String organizerName;
    private String identityOrganizer;
    private CollectionReference mFireStore;
    private FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    private String TAG="FollowerModel";



    public FollowerModel(String organizerName, String identity) {
        this.organizerName = organizerName;
        this.identityOrganizer=identity;
        mFireStore= FirebaseFirestore.getInstance().collection("users");

    }
    public  FollowerModel(){
        mFireStore= FirebaseFirestore.getInstance().collection("users");


    }

    public void iFollowYou(final OrganizerModel organizer){
        FollowerModel followerModel = new FollowerModel();
        followerModel.setIdentityOrganizer(organizer.getOrganizerIdentity());
        followerModel.setOrganizerName(organizer.getOrganizerName());
        mFireStore.document(user.getUid())
                .collection("whoifollow")
                .document(organizer.getOrganizerIdentity())
                .set(followerModel)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "DocumentSnapshot successfully written!");
                        FollowingModel followingModel = new FollowingModel();
                        followingModel.youFollowMe(organizer.getOrganizerIdentity());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error writing document", e);
                    }
                });

             /*   .add(followerModel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        updateWhoIFollowIdentity(documentReference.getId());

                        FollowingModel followingModel = new FollowingModel();
                        followingModel.youFollowMe(organizerId,documentReference.getId());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        //Log.w(TAG, "Error adding document", e);
                    }
                });
*/

    }



    public void iUnFollowYou(final FollowerModel model){

        Log.d(TAG, "I unFollowYou");
        FollowingModel followingModel= new FollowingModel();
        followingModel.youUnFollowMe(model.getIdentityOrganizer());

      /*  mFireStore.document(user.getUid())
                .collection("whoifollow");
        mFireStore.document(model.getIdentityOrganizer()).delete()
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "I unFollowYou");
                        FollowingModel followingModel= new FollowingModel();
                        followingModel.youUnFollowMe(model.getIdentityOrganizer());
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Log.w(TAG, "Error deleting document", e);
                    }
                });*/

    }


    public void updateWhoIFollowIdentity(String identity){
        mFireStore.document(user.getUid())
                .collection("whoifollow")
                .document(identity)
                .update("idDocument", identity)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Update whoIFollowdentity is successful");
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



    public String getOrganizerName() {
        return organizerName;
    }

    public void setOrganizerName(String organizerName) {
        this.organizerName = organizerName;
    }

    public String getIdentityOrganizer() {
        return identityOrganizer;
    }

    public void setIdentityOrganizer(String identityOrganizer) {
        this.identityOrganizer = identityOrganizer;
    }
}

