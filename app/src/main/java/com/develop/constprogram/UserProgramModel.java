package com.develop.constprogram;

import androidx.annotation.NonNull;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

public class UserProgramModel {
    private String programIdentifiant;
    private StorageReference mStorageRef;
    private CollectionReference mFireStore;
    private List<UserProgramModel> listProgram;
    private ProgramModel programModel;

    public UserProgramModel(String programIdentifiant) {
        this.programIdentifiant = programIdentifiant;

        mFireStore= FirebaseFirestore.getInstance().collection("users");

    }

    public  UserProgramModel(){
        mFireStore= FirebaseFirestore.getInstance().collection("users");

    }

    public void getUserProgram(String userId){



        listProgram= new ArrayList<>();
        programModel=new ProgramModel();
        DocumentReference userDocument=mFireStore.document(userId);
        CollectionReference myProgram=userDocument.collection("myprogram");

        myProgram.get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {
                        if (task.isSuccessful()) {
                            for (QueryDocumentSnapshot document : task.getResult()) {
                              UserProgramModel userProgramModel = document.toObject(UserProgramModel.class);
                              listProgram.add(userProgramModel);
                              programModel.getUserProgram(listProgram);
                            }
                        } else {
                            //Log.d(TAG, "Error getting documents: ", task.getException());
                        }
                    }
                });
        //UserProgramModel userProgramModel=new UserProgramModel();
       // userProgramModel.setProgramIdentifiant(programId);

    }

    public String getProgramIdentifiant() {
        return programIdentifiant;
    }

    public void setProgramIdentifiant(String programIdentifiant) {
        this.programIdentifiant = programIdentifiant;
    }

}
