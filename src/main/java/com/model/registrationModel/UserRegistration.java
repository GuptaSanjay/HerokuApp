package com.model.registrationModel;

import lombok.*;

import java.io.Serializable;

@Builder
@Data
@ToString
@Getter
@EqualsAndHashCode
public class UserRegistration implements Serializable{

    private static String username;
    private static String email;
    private static String firstName;
    private static String lastName;
    private static String password;
    private static String mobileNumber;
    private static String dateOfBirth;
    private static String monthOfBirth;
    private static String yearOfBirth;
    private static String address;
    private static String country;
    private static String state;
    private static String city;
    private static String zipcode;

}
