package com.kylas.parkingapplication.exceptions;

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
