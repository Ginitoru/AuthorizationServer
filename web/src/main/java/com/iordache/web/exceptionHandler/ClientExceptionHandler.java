package com.iordache.web.exceptionHandler;

import com.iordache.entity.Client;
import com.iordache.exceptions.errors.ClientAlreadyExists;
import com.iordache.exceptions.message.ErrorMessage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ClientExceptionHandler {

    @ExceptionHandler
    public ResponseEntity<ErrorMessage> handleClientAlreadyExistsException(ClientAlreadyExists exc){

        var error = ErrorMessage.builder()
                                             .message(exc.getMessage())
                                             .status(HttpStatus.FOUND.value())
                                             .timeStamp(System.currentTimeMillis())
                                             .build();
        exc.printStackTrace();

        return new ResponseEntity<>(error, HttpStatus.FOUND);

    }

}
