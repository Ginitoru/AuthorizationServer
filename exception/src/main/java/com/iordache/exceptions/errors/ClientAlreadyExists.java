package com.iordache.exceptions.errors;

public class ClientAlreadyExists extends RuntimeException{
    public ClientAlreadyExists() {
    }

    public ClientAlreadyExists(String message) {
        super(message);
    }
}
