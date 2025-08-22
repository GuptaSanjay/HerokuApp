package com.data.Enums;

import lombok.ToString;

@ToString
public enum UserData {
    First_Name("Sanjay"),
    Last_Name("Gupta"),
    Password("Sanjay1234")
    ;

    private final String userData;

    UserData(String userData) {
        this.userData=userData;
    }

    public String getUserData() {
        return this.userData;
    }
}
