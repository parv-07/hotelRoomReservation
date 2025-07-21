package com.example.hotelRoomReservationSystem.CustomeExceptionHandler;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(InvalidBookingException.class)
    public ResponseEntity<?> handleInvalidBookingException(InvalidBookingException ex){
        System.out.println("Inside the custome exception handler");
        return new ResponseEntity<>(ex.getMessage() + "the response is written", HttpStatus.BAD_REQUEST);
    }
}
