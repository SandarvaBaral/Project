package com.sandarva.medtrack;

public class UsersDoctor {
    private String firstName, phNumber;

    private UsersDoctor(){}

    private UsersDoctor(String firstName, String phNumber){
        this.firstName = firstName;
        this.phNumber = phNumber;
    }


    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }


    public String getPhNumber() {
        return phNumber;
    }

    public void setPhNumber(String phNumber) {
        this.phNumber = phNumber;
    }
}
