package com.example.College.Event.Registration.System.exception;

public class DuplicateRegistrationException extends RuntimeException {

    public DuplicateRegistrationException(String message) {
        super(message);
    }
}