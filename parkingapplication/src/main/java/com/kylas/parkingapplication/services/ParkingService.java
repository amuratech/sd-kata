package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.entities.Car;
import com.kylas.parkingapplication.exceptions.ParkingLotException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ParkingService {

    private List<Car> list=new ArrayList<>();
    static int counter=0;




    public List<Car> getCars() {
        return list;
    }

    public List addCar(String carNo) throws ParkingLotException {
        counter++;
        if(counter<9) {
            list.add(new Car(counter,carNo));

            return list;
        }
      else
        {
            throw new ParkingLotException("Parking lot is full.",
                    ParkingLotException.ExceptionType.NO_PARKING_AVAILABLE);        }
    }
}
