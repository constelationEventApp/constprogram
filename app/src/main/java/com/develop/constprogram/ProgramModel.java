package com.develop.constprogram;

import android.provider.DocumentsContract;
import android.util.Log;
import android.widget.ImageButton;

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
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class ProgramModel {
    private String programDate;
    private  String programName;
    private  String programAddress;
    private String userIdentity;
    private String programIdentity;
    private String programImage;
    private String programOrganizer;
    private String programTypeOfEvent;
    private String programStartDate;
    private String programEndDate;
    private String programStartTime;
    private String programEndTime;
    private String programSummary;

    String TAG="ProgramModel";

    List<UserProgramModel> idListProgram;
    List<ProgramModel> listProgram;
    private StorageReference mStorageRef;
    CollectionReference mFireStore;
    FirebaseUser user= FirebaseAuth.getInstance().getCurrentUser();
    Boolean isToFavorite=false;
    Boolean isDeleteToFavorite=false;



    //Begin Constructor

    public ProgramModel(String programDate, String programName, String programAddress, String programIdentity, String image, String programOrganizer, String programTypeOfEvent, String programStartDate, String programEndDate, String programStartTime, String programEndTime, String programSummary, String userIdentity) {
        this.programDate = programDate;
        this.programName = programName;
        this.programAddress = programAddress;
        this.userIdentity = userIdentity;
        this.programIdentity=programIdentity;
        this.programImage=image;
        this.programOrganizer=programOrganizer;
        this.programTypeOfEvent=programTypeOfEvent;
        this.programStartDate=programStartDate;
        this.programEndDate=programEndDate;
        this.programStartTime=programStartTime;
        this.programEndTime=programEndTime;
        this.programSummary=programSummary;

        mFireStore= FirebaseFirestore.getInstance().collection("program");

    }
    //Empty Constructor is Important
    public ProgramModel(){
        //** It is Important **/
        mFireStore= FirebaseFirestore.getInstance().collection("program");

    }
    //End of Constructors

    public boolean deleteToFavorite(String programIdentity){
        if(programIdentity!=null){
            mFireStore=FirebaseFirestore.getInstance().collection("users")
                    .document(user.getUid()).collection("favorite");
            mFireStore.document(programIdentity)
                    .delete()
                    .addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            Log.d(TAG, "DocumentSnapshot successfully deleted!");
                            isDeleteToFavorite=true;
                        }
                    })
                    .addOnFailureListener(new OnFailureListener() {
                        @Override
                        public void onFailure(@NonNull Exception e) {
                            Log.w(TAG, "Error deleting document", e);
                        }
                    });
        }
        return isDeleteToFavorite;
    }

    public boolean addToFavorite(String programIdentity){
        if(programIdentity!=null){
            mFireStore.document(programIdentity)
                    .get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    ProgramModel programModel= documentSnapshot.toObject(ProgramModel.class);
                    insertProgramToFavorite(programModel,user.getUid());
                    Log.d(TAG, "Inside add to favorite");


                }
            });

        }
        return isToFavorite;
    }

    public void insertProgramToFavorite(final ProgramModel programModel, final String userIdentity){
        // mFireStore.document(programModel.programIdentity).set(programModel)
        mFireStore= FirebaseFirestore.getInstance().collection("users");
        CollectionReference collection=mFireStore.document(userIdentity).collection("favorite");

        collection.document(programModel.programIdentity).get()
                .addOnCompleteListener(new OnCompleteListener<DocumentSnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<DocumentSnapshot> task) {
                        if (task.isSuccessful()) {

                            DocumentSnapshot document = task.getResult();
                            if (document.exists()) {

                                Log.d(TAG, "Program Already inserted To favorite");
                            } else {
                                Log.d(TAG, "No such document");
                                //No such document insert
                                mFireStore= FirebaseFirestore.getInstance().collection("users");
                                mFireStore.document(userIdentity)
                                        .collection("favorite")
                                        .document(programModel.programIdentity)
                                        .set(programModel)
                                        .addOnSuccessListener(new OnSuccessListener<Void>() {
                                            @Override
                                            public void onSuccess(Void aVoid) {
                                                Log.d(TAG, "Program successfully written into Favorite!");
                                                isToFavorite=true;

                                            }
                                        })
                                        .addOnFailureListener(new OnFailureListener() {
                                            @Override
                                            public void onFailure(@NonNull Exception e) {
                                                Log.w(TAG, "Error adding document to Favorite", e);
                                            }
                                        });
                                //end insert

                            }
                        } else {
                            Log.d(TAG, "get failed with ", task.getException());

                        }
                    }
                });


              /*  .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                                Log.d(TAG, document.getId() + " => " + document.getData());
                                if(document.getId()==programModel.getProgramIdentity()){

                                    Log.d(TAG, "Already add to favorite!");

                                }else{
                                    Log.d(TAG, "No such document");
                                    //No such document insert
                                    mFireStore= FirebaseFirestore.getInstance().collection("users");
                                    mFireStore.document(userIdentity).collection("favorite").document(programModel.programIdentity).set(programModel)
                                            .addOnSuccessListener(new OnSuccessListener<Void>() {
                                                @Override
                                                public void onSuccess(Void aVoid) {
                                                    Log.d(TAG, "DocumentSnapshot successfully written!");
                                                }
                                            })
                                            .addOnFailureListener(new OnFailureListener() {
                                                @Override
                                                public void onFailure(@NonNull Exception e) {
                                                    Log.w(TAG, "Error adding document to Favorite", e);
                                                }
                                            });;
                                    //end insert
                                }
                            }
                        } else {
                            Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
*/

    }
    //Other Methode
    public void insertProgram(final ProgramModel programModel, final String userId){
       // mFireStore.document(programModel.programIdentity).set(programModel)



         mFireStore.add(programModel)
                 .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                     @Override
                     public void onSuccess(DocumentReference documentReference) {
                         Log.d(TAG, "Insert program is successful");
                         //Adding Document Reference
                         updateProgramIdentity(documentReference.getId());

                         //Log.d(TAG, "DocumentSnapshot written with ID: " + documentReference.getId());
                         UserInfoModel userInfoModel= new UserInfoModel();
                         userInfoModel.insertUserProgram(documentReference.getId(),userId);
                         //Insert orgnizer name
                         OrganizerModel organizerModel= new OrganizerModel();

                         organizerModel.insertOrganizer();

                     }
                 })
                 .addOnFailureListener(new OnFailureListener() {
                     @Override
                     public void onFailure(@NonNull Exception e) {
                         //Log.w(TAG, "Error adding document", e);
                     }
                 });

    }



    public void updateProgramIdentity(String programIdentity){
        mFireStore= FirebaseFirestore.getInstance().collection("program");
        DocumentReference doc=mFireStore.document(programIdentity);
        doc.update("programIdentity", programIdentity)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Log.d(TAG, "Update programIdentity is successful");
                    }
                });
    }

    public void getUserProgram(List<UserProgramModel> myProgramId){
        listProgram=new ArrayList<>();
        for (UserProgramModel id : myProgramId) {
            getUniqueProgram(id);

        }



    }
    public void getUniqueProgram(UserProgramModel programModelId){

        mFireStore.document(programModelId.getProgramIdentifiant()).get()
                .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                    @Override
                    public void onSuccess(DocumentSnapshot documentSnapshot) {
                        ProgramModel p = documentSnapshot.toObject(ProgramModel.class);
                        listProgram.add(p);
                    }
                });


    }

    public void modifyProgram(String programIdentity){

    }


    //Getters and Setters

    public String getProgramDate() {
        return programDate;
    }

    public String getProgramImage() {
        return programImage;
    }

    public void setProgramImage(String programImage) {
        this.programImage = programImage;
    }

    public void setProgramDate(String programDate) {
        this.programDate = programDate;
    }

    public String getProgramName() {
        return programName;
    }

    public void setProgramName(String programName) {
        this.programName = programName;
    }

    public String getProgramAddress() {
        return programAddress;
    }

    public void setProgramAddress(String programAddress) {
        this.programAddress = programAddress;
    }

    public String getProgramIdentity() {
        return programIdentity;
    }



    public String getUserIdentity() {
        return userIdentity;
    }
    public void setProgramIdentity(String programIdentity) {
        this.programIdentity = programIdentity;
    }
    public void setUserIdentity(String programIdentity) {
        this.userIdentity = programIdentity;
    }

    public String getProgramOrganizer() {
        return programOrganizer;
    }

    public void setProgramOrganizer(String programOrganizer) {
        this.programOrganizer = programOrganizer;
    }

    public String getProgramTypeOfEvent() {
        return programTypeOfEvent;
    }

    public void setProgramTypeOfEvent(String programTypeOfEvent) {
        this.programTypeOfEvent = programTypeOfEvent;
    }

    public String getProgramStartDate() {
        return programStartDate;
    }

    public void setProgramStartDate(String programStartDate) {
        this.programStartDate = programStartDate;
    }

    public String getProgramEndDate() {
        return programEndDate;
    }

    public void setProgramEndDate(String programEndDate) {
        this.programEndDate = programEndDate;
    }

    public String getProgramStartTime() {
        return programStartTime;
    }

    public void setProgramStartTime(String programStartTime) {
        this.programStartTime = programStartTime;
    }

    public String getProgramEndTime() {
        return programEndTime;
    }

    public void setProgramEndTime(String programEndTime) {
        this.programEndTime = programEndTime;
    }

    public String getProgramSummary() {
        return programSummary;
    }

    public void setProgramSummary(String programSummary) {
        this.programSummary = programSummary;
    }



}
