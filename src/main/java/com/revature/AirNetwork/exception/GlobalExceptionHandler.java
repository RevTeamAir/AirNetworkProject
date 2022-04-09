package com.revature.AirNetwork.exception;

import com.revature.AirNetwork.models.JsonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.util.Date;

@ControllerAdvice
public class GlobalExceptionHandler {

    //handle specific exceptions
    /*@ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<?> handleResourceNotFoundException
        (ResourceNotFoundException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<errorDetails, HttpStatus.NOT_FOUND>();
    }*/
    //handle gloval exceptions
    /*@ExceptionHandler(Exception.class)
    public ResponseEntity<JsonResponse> handleGlobalException
    (JsonResponse jsonResponse, WebRequest request){
        JsonResponse jasonResponse = new JsonResponse(new Date(), jsonResponse.getMessage(), request.getDescription(false));

        return new ResponseEntity(jasonResponse );
    }*/
}
