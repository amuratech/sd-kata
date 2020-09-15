package com.kylas.parkingapplication.controller;

import com.kylas.parkingapplication.entities.Car;
import com.kylas.parkingapplication.exceptions.ParkingLotException;
import com.kylas.parkingapplication.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController
{
    @Autowired
    private ParkingService parkingService;

    @GetMapping("/home")
    public String home(){
        return "Welcome to my Parking Service";
    }

    //Get all cars
    @GetMapping("/allCars")
    public List<Car> getCars()
    {
    return this.parkingService.getCars();
    }

    @PostMapping("/allCars")
    public List addCar(@RequestParam(value = "carNo") String carNo) throws ParkingLotException {
            //System.out.println(carNo);
             return this.parkingService.addCar(carNo);
    }
}
