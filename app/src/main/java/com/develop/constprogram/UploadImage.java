package com.develop.constprogram;

public class UploadImage {
    private String mName;
    private String mImageUrl;

    public UploadImage(String mName, String mImageUrl) {
        if(mName.trim().equals("")){
            mName="no name";
        }
        this.mName = mName;
        this.mImageUrl = mImageUrl;
    }

    public UploadImage(){
        //Empty constructor is needed

    }

    public String getmName() {
        return mName;
    }

    public void setmName(String mName) {
        this.mName = mName;
    }

    public String getmImageUrl() {
        return mImageUrl;
    }

    public void setmImageUrl(String mImageUrl) {
        this.mImageUrl = mImageUrl;
    }
}
