package com.develop.constprogram;

public class UserProgramModel {
    private String programIdentifiant;

    public UserProgramModel(String programIdentifiant) {
        this.programIdentifiant = programIdentifiant;
    }

    public  UserProgramModel(){

    }

    public String getProgramIdentifiant() {
        return programIdentifiant;
    }

    public void setProgramIdentifiant(String programIdentifiant) {
        this.programIdentifiant = programIdentifiant;
    }

}
