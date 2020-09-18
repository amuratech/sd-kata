package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.entities.Vehicle;
import com.kylas.parkingapplication.exceptions.ParkingLotException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingService {

    private List<Vehicle> vehicles=new ArrayList<>();
    static int parking_slot_no=0;




    public List<Vehicle> getVehicles() {
        return vehicles;
    }

    public List addVehicle(String vehicle_no) throws ParkingLotException {
        parking_slot_no++;
        if(parking_slot_no<=10) {
            vehicles.add(new Vehicle(parking_slot_no,vehicle_no));

            return vehicles;
        }
      else
        {
            throw new ParkingLotException("Parking lot is full.",
                    ParkingLotException.ExceptionType.NO_PARKING_AVAILABLE);        }
    }
}
