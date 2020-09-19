package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.entities.Vehicle;
import com.kylas.parkingapplication.exceptions.ParkingLotException;
import org.springframework.beans.factory.annotation.Value;

import org.springframework.http.HttpCookie;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ParkingService {

    @Value("${slot}")
    private int parkingSlots;

    private List<Vehicle> vehicles=new ArrayList<>();
    static int parkingSlotNo=0;

    public List<Vehicle> getVehicles() {

        return vehicles;
    }


    public Vehicle addVehicle(String vehicleNo) {

        parkingSlotNo++;
        if(parkingSlotNo<=parkingSlots) {
                vehicles.add(new Vehicle(parkingSlotNo,vehicleNo));
            return vehicles.get(vehicles.size()-1);

            }
            else{
                throw new ParkingLotException("Parking lot is full.",
                        ParkingLotException.ExceptionType.NO_PARKING_AVAILABLE);

        }

    }

}
