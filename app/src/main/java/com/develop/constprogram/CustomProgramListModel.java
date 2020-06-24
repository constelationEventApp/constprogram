package com.develop.constprogram;

public class CustomProgramListModel {
    private String programDate;
    private  String programName;
    private  String programAddress;
    private String programIdentity;
    private String programImage;

    public CustomProgramListModel(String programDate, String programName, String programAddress, String programIdentity, String image) {
        this.programDate = programDate;
        this.programName = programName;
        this.programAddress = programAddress;
        this.programIdentity = programIdentity;
        programImage=image;
    }

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

    public void setProgramIdentity(String programIdentity) {
        this.programIdentity = programIdentity;
    }

    public CustomProgramListModel(){

    }


}
