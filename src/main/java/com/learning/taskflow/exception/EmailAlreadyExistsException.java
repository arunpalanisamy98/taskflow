package com.learning.taskflow.exception;

public class  EmailAlreadyExistsException extends RuntimeException {

    public EmailAlreadyExistsException(String message){
        super(message); 
    }
    
}
