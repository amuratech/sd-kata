package com.kylas.parkingapplication.entities;

import org.springframework.http.HttpStatus;

public class Vehicle {
    private int parkingSlot;
    private String vehicleNo;



    public int getParkingSlot() {
        return parkingSlot;
    }

    public void setParkingSlot(int parkingSlot) {
        this.parkingSlot = parkingSlot;
    }

    public String getVehicleNo() {
        return vehicleNo;
    }

    public void setVehicleNo(String vehicleNo) {
        this.vehicleNo = vehicleNo;
    }

    public Vehicle() {
        super();
    }

    public Vehicle(int parkingSlot, String vehicleNo) {
        this.parkingSlot = parkingSlot;
        this.vehicleNo = vehicleNo;
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "parkingSlot=" + parkingSlot +
                ", vehicleNo='" + vehicleNo + '\'' +
                '}';
    }
}
