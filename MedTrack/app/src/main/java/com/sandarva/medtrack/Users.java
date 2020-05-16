package com.sandarva.medtrack;

public class Users {

    private String fName;

    private Users(){}

    private Users(String fName){
        this.fName = fName;
    }


    public String getfName() {
        return fName;
    }

    public void setfName(String fName) {
        this.fName = fName;
    }
}
