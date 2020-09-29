package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.entities.ParkingLot;
import com.kylas.parkingapplication.entities.ParkingSlot;
import com.kylas.parkingapplication.entities.Vehicle;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParkingService {


    private final ParkingLot parkingLot = new ParkingLot(10);


    public List<ParkingSlot> getVehicles() {

        return this.parkingLot.listVehicles();
    }

    public ParkingSlot park(String vehicleNo) {
        Vehicle vehicle = new Vehicle(vehicleNo);
        return this.parkingLot.park(vehicle);
    }

    public String unPark(String vehicleNo) {
        Vehicle vehicle = new Vehicle(vehicleNo);
        if (this.parkingLot.unPark(vehicle)) {
            return "Vehicle unparked";
        }
        return "Vehicle not unparked";
    }
}

