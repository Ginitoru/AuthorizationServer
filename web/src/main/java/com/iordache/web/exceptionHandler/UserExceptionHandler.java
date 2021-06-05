package com.iordache.web.exceptionHandler;

import com.iordache.exceptions.errors.UserAlreadyExists;
import com.iordache.exceptions.errors.UserNotFoundException;
import com.iordache.exceptions.message.UserErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class UserExceptionHandler {


    @ExceptionHandler  //method 2
    public ResponseEntity<UserErrorMessage> handleUserAlreadyExistsException(UserAlreadyExists exc){

        var error = userErrorMessage(HttpStatus.FOUND.value(), exc.getMessage(), exc);
        return new ResponseEntity<>(error, HttpStatus.FOUND);
    }

    @ExceptionHandler  //method 2
    public ResponseEntity<UserErrorMessage> handleUserNotFoundException(UserNotFoundException exc){

        var error = userErrorMessage(HttpStatus.NOT_FOUND.value(), exc.getMessage(), exc);
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

                       //method 1
    private UserErrorMessage userErrorMessage(int status, String message, RuntimeException exc){
        exc.printStackTrace();
        return new UserErrorMessage(status, message, System.currentTimeMillis());

    }



}
