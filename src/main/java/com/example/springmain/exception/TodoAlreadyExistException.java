package com.example.springmain.exception;

public class TodoAlreadyExistException extends Exception {
    public TodoAlreadyExistException(String message) {
        super(message);
    }
}
