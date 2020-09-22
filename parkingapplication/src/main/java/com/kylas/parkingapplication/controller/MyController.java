package com.kylas.parkingapplication.controller;

import com.kylas.parkingapplication.entities.Vehicle;
import com.kylas.parkingapplication.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.TreeMap;

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
    public Vehicle addVehicle(@RequestParam(value="vehicleNo") String vehicleNo){
           return this.parkingService.addVehicle(vehicleNo);
    }

    @DeleteMapping("/vehicles")
    public String deleteVehicle(@RequestParam(value="vehicleNo") String vehicleNo){


            if(this.parkingService.deleteVehicle(vehicleNo))
                return "Deleted";
            else
                return "not deleted";

    }
}
