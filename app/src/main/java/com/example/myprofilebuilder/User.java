package com.example.myprofilebuilder;

import java.io.Serializable;

public class User implements Serializable {
    String firstName;
    String lastName;
    String GenderFlag;

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", GenderFlag='" + GenderFlag + '\'' +
                '}';
    }

    public User(String firstName, String lastName, String genderFlag) {
        this.firstName = firstName;
        this.lastName = lastName;
        GenderFlag = genderFlag;
    }
}
