package com.kylas.parkingapplication.controller;

import com.kylas.parkingapplication.entities.ParkingSlot;
import com.kylas.parkingapplication.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
public class MyController {
    @Autowired
    ParkingService parkingService;


    @GetMapping("/home")
    public String home() {
        return "Welcome to my Parking Service";
    }

    @GetMapping("/vehicles")
    public List<ParkingSlot> getVehicles() {
        return this.parkingService.getVehicles();
    }

    @PostMapping("/vehicles")
    public ParkingSlot addVehicle(@RequestParam(value = "vehicleNo") String vehicleNo) {
        return this.parkingService.park(vehicleNo);
    }

    @DeleteMapping("/vehicles")
    public String deleteVehicle(@RequestParam(value = "vehicleNo") String vehicleNo) {
        return this.parkingService.unPark(vehicleNo);

    }
}
