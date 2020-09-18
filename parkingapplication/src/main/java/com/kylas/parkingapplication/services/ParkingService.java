package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.entities.Vehicle;
import com.kylas.parkingapplication.exceptions.ParkingLotException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingService {

    private List<Vehicle> vehicles=new ArrayList<>();
    static int parkingSlotNo=0;




    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public Vehicle addVehicle(String vehicleNo) {
        parkingSlotNo++;

            if(parkingSlotNo<=3) {
                vehicles.add(new Vehicle(parkingSlotNo,vehicleNo));

            }
            else{
                throw new ParkingLotException("Parking lot is full.",
                        ParkingLotException.ExceptionType.NO_PARKING_AVAILABLE);  

        }

        return vehicles.get(vehicles.size()-1);
    }
}
