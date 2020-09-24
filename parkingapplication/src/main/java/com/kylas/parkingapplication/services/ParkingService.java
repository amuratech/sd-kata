package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.ParkingapplicationApplication;
import com.kylas.parkingapplication.entities.Vehicle;
import com.kylas.parkingapplication.exceptions.ParkingLotException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;


import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.*;


@Service
public class ParkingService {


    @Value("${slot}")
    private  int parkingSlots;

    private  static int[] currentAvailableSlot = new int[10];
    private static int deletedFlag=0;

    private List<Vehicle> vehicles=new ArrayList<>();

    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Vehicle addVehicle(String vehicleNo) {
        if(deletedFlag>0){
                if(vehicles.size()<parkingSlots){
                    for(int i = 0; i < parkingSlots; i++) {
                        if (currentAvailableSlot[i] == 1) {
                            vehicles.add(new Vehicle(vehicleNo, i + 1));
                            currentAvailableSlot[i]=0;
                            deletedFlag=deletedFlag-1;
                            break;
                        }
                    }
                }
            return vehicles.get(vehicles.size()-1);
        }
        else if(vehicles.size()<parkingSlots) {
            vehicles.add(new Vehicle(vehicleNo,vehicles.size()+1));
            return vehicles.get(vehicles.size()-1);
        }
        else{
            throw new ParkingLotException("Parking lot is full.",
                    ParkingLotException.ExceptionType.NO_PARKING_AVAILABLE);
        }
    }

    public String deleteVehicle(String vehicleNo) {

        Optional<Vehicle> foundVehicle = vehicles.stream().
                filter(vehicle -> vehicle.getVehicleNo().equals(vehicleNo)).findFirst();
        if(foundVehicle.isPresent()) {
            int currentSlot = foundVehicle.get().getParkingSlot();
            Vehicle v=foundVehicle.get();
            vehicles.remove(foundVehicle.get());
            currentAvailableSlot[currentSlot-1] = 1;
            deletedFlag=deletedFlag+1;
            return "Vehicle exit";
        }
        return "Vehicle not exit";
    }
}

