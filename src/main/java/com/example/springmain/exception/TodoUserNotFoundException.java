package com.example.springmain.exception;

public class TodoUserNotFoundException extends Exception {
    public TodoUserNotFoundException(String message) {
        super(message);
    }
}
