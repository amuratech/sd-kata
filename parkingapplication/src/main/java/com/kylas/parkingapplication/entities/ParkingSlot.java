package com.kylas.parkingapplication.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ParkingSlot {
    private int slotNo;
    private Vehicle vehicle;


    public void park(Vehicle vehicle) {
        this.vehicle = vehicle;
    }


    public void unPark(Vehicle vehicle) {
        this.vehicle = null;
    }
}
