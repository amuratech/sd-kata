package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.ParkingapplicationApplication;
import com.kylas.parkingapplication.entities.Vehicle;
import com.kylas.parkingapplication.exceptions.ParkingLotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;


@Service
public class ParkingService {


    @Value("${slot}")
    private int parkingSlots;



    private List<Vehicle> vehicles=new ArrayList<>();


    public List<Vehicle> getVehicles() {

        return vehicles;
    }


    public Vehicle addVehicle(String vehicleNo) {

        if((vehicles.size()<parkingSlots) ) {
            vehicles.add(new Vehicle(vehicleNo,vehicles.size()+1));

            return vehicles.get(vehicles.size()-1);

        }
        else{
            throw new ParkingLotException("Parking lot is full.",
                    ParkingLotException.ExceptionType.NO_PARKING_AVAILABLE);

        }

    }

    public boolean deleteVehicle(String vehicleNo) {

        if(vehicles.removeIf(v->v.getVehicleNo().equals(vehicleNo))){
            return true;
        }
        else
            return false;
    }


}