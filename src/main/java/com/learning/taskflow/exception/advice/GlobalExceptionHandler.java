package com.learning.taskflow.exception.advice;

import java.time.LocalDateTime;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.learning.taskflow.exception.EmailAlreadyExistsException;
import com.learning.taskflow.exception.InvalidEmailException;
import com.learning.taskflow.exception.InvalidUserIdException;
import com.learning.taskflow.exception.PageNumberSizeException;
import com.learning.taskflow.exception.UserAlreadyExistsException;
import com.learning.taskflow.model.ErrorResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleUserAlreadyExists(UserAlreadyExistsException exception){
        return buildErrorResponse("user already exists", exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidUserIdException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUserId(InvalidUserIdException exception){
        return buildErrorResponse("user id does not exist", exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(InvalidEmailException.class)
    public ResponseEntity<ErrorResponse> handleInvalidEmail(InvalidEmailException exception){
        return buildErrorResponse("email does not exist", exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidUserId(EmailAlreadyExistsException exception){
        return buildErrorResponse("email already exists", exception.getMessage(), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(PageNumberSizeException.class)
    public ResponseEntity<ErrorResponse> handlePageError(PageNumberSizeException exception){
        return buildErrorResponse("pagination error", exception.getMessage(), HttpStatus.CONFLICT);
    }


    private ResponseEntity<ErrorResponse> buildErrorResponse(String error, String message, HttpStatus status) {
        ErrorResponse response = ErrorResponse.builder()
            .error(error)
            .message(message)
            .time(LocalDateTime.now())
            .build();

        return ResponseEntity.status(status).body(response);
    }
    
}
