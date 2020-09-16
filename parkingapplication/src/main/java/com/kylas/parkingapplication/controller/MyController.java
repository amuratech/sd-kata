package com.kylas.parkingapplication.controller;

import com.kylas.parkingapplication.entities.Vehicle;
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

    @GetMapping("/vehicles")
    public List<Vehicle> getVehicles()
    {
    return this.parkingService.getVehicles();
    }

    @PostMapping("/vehicles")
    public List addVehicle(@RequestParam(value = "vehicle_no") String vehicle_no) throws ParkingLotException {
             return this.parkingService.addVehicle(vehicle_no);
    }
}
