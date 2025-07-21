package com.example.hotelRoomReservationSystem.CustomeExceptionHandler;

public class InvalidBookingException extends RuntimeException {

    public InvalidBookingException(String message){
        super(message+" it comes first on exception handler");
        System.out.println("inside the exception handlers");
    }
}
