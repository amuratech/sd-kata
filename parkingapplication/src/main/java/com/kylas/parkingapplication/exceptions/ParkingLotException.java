package com.kylas.parkingapplication.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.lang.annotation.Annotation;

@ResponseStatus(value = HttpStatus.BAD_REQUEST,reason = "Parking Lot is full")
public class ParkingLotException extends RuntimeException {

    public enum ExceptionType {
        NO_PARKING_AVAILABLE
    }

    public ExceptionType type;

    public ParkingLotException(String message, ExceptionType type) {
        super(message);
        this.type = type;
    }
}
