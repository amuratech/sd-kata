package com.kylas.parkingapplication.entities;

public class Vehicle {
    private int parking_slot;
    private String vehicle_no;

    public int getParking_slot() {
        return parking_slot;
    }

    public void setParking_slot(int parking_slot) {
        this.parking_slot = parking_slot;
    }

    public String getVehicle_no() {
        return vehicle_no;
    }

    public void setVehicle_no(String vehicle_no) {
        this.vehicle_no = vehicle_no;
    }

    public Vehicle(int parking_slot, String vehicle_no) {
        this.parking_slot = parking_slot;
        this.vehicle_no = vehicle_no;
    }

    public Vehicle() {
        super();
    }

    @Override
    public String toString() {
        return "Vehicle{" +
                "parking_slot=" + parking_slot +
                ", vehicle_no='" + vehicle_no + '\'' +
                '}';
    }
}
