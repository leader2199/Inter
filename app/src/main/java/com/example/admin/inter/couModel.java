package com.example.admin.inter;

public class couModel {
    private int imgID;
    private String name;

    public couModel(int imgID, String name) {
        this.imgID = imgID;
        this.name = name;
    }

    public int getImgID() {
        return imgID;
    }

    public void setImgID(int imgID) {
        this.imgID = imgID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
