package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.entities.ParkingLot;
import com.kylas.parkingapplication.entities.ParkingTicket;
import com.kylas.parkingapplication.entities.Vehicle;
import com.kylas.parkingapplication.repository.ParkingTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;


@Service
public class ParkingService {

    @Autowired
    private ParkingTicketRepository parkingTicketRepository;

    private final ParkingLot parkingLot = new ParkingLot(10);


    public List<ParkingTicket> getVehicles() {

     // return parkingTicketRepository.findAll();
      return parkingLot.listSlots();
    }

    public ParkingTicket park(String vehicleNo) {
        Vehicle vehicle = new Vehicle(vehicleNo);

        ParkingTicket ticket = this.parkingLot.park(vehicle);
        parkingTicketRepository.save(ticket);
        return ticket;
    }

    public String unPark(String vehicleNo) {
        Vehicle vehicle = new Vehicle(vehicleNo);
        ParkingTicket parkingTicket = this.parkingLot.unPark(vehicle);
        parkingTicketRepository.save(parkingTicket);
        return "Vehicle Exit done";
    }
}
