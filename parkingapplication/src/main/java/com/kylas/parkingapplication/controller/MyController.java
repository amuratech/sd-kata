package com.kylas.parkingapplication.controller;

import com.kylas.parkingapplication.entities.ParkingSlot;
import com.kylas.parkingapplication.services.ParkingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


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
    public ResponseEntity<Object> deleteVehicle(@RequestParam(value = "vehicleNo") String vehicleNo) {
        try {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", "Vehicle Exit Done");
            this.parkingService.unPark(vehicleNo);
            return new ResponseEntity<>(body, HttpStatus.OK);
        } catch (Exception e) {
            Map<String, Object> body = new LinkedHashMap<>();
            body.put("timestamp", LocalDateTime.now());
            body.put("message", "Vehicle Exit not done");
            return new ResponseEntity<>(body, HttpStatus.NOT_FOUND);
        }


    }
}
