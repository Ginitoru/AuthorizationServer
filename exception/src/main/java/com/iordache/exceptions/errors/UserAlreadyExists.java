package com.iordache.exceptions.errors;

public class UserAlreadyExists extends RuntimeException{

    public UserAlreadyExists() {
    }

    public UserAlreadyExists(String message) {
        super(message);
    }
}
