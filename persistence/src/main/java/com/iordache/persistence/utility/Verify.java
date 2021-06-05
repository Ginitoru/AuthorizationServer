package com.iordache.persistence.utility;

public class Verify {

    private static final String EMAIL_REGEX = "^[a-zA-Z0-9+_.-]+@[a-zA-Z0-9.-]+$";
    private static final String PHONE_NUMBER_REGEX = "[0-9]+";




    public static boolean isEmail(String email){
        return email.matches(EMAIL_REGEX);
    }


    public static boolean isPhoneNumber(String phoneNumber){
        return phoneNumber.matches(PHONE_NUMBER_REGEX);
    }
}


