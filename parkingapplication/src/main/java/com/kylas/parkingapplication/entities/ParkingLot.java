package com.kylas.parkingapplication.entities;

import com.kylas.parkingapplication.exceptions.ParkingLotException;
import com.kylas.parkingapplication.repository.ParkingTicketRepository;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static com.kylas.parkingapplication.exceptions.ParkingLotException.ExceptionType.*;

public class ParkingLot {
    @Autowired
    private ParkingTicketRepository parkingTicketRepository;

    private final List<ParkingTicket> parkingTicketList = new ArrayList<>();
    private final int capacity;
    private final List<ParkingSlot> slots;




    public ParkingLot(int capacity) {
        this.capacity = capacity;
        slots = new ArrayList<>();


    }

    public boolean isEmpty() {
        return slots.isEmpty();
    }

    public boolean isSlotAvailable() {
        return (slots.size() < capacity);
    }

    public ParkingTicket park(Vehicle vehicle) {
        if (!isSlotAvailable())
            throw new ParkingLotException("Parking Lot is Full", NO_PARKING_AVAILABLE);

        int slotNo = getAvailableSlotNo();


        ParkingSlot parkingSlot = new ParkingSlot(slotNo, vehicle);


        ParkingTicket parkingTicket = new ParkingTicket(parkingSlot, new Date());


        parkingTicketList.add(parkingTicket);
        slots.add(parkingSlot);
        return parkingTicket;
    }

    private int getAvailableSlotNo() {
        for (int i = 1; i <= capacity; i++) {
            int finalI = i;
            if (slots.stream().noneMatch(parkingSlot -> parkingSlot.getSlotNo() == finalI)) {
                return i;
            }
        }
        return -1;
    }

    public long size() {
        return slots.size();
    }

    public List<ParkingTicket> listSlots() {
        return parkingTicketList;
    }

    public int getParkingSlot(Vehicle vehicle) {
        ParkingSlot foundSlot = slots.stream()
                .filter(parkingSlot -> parkingSlot.getVehicle().getVehicleNo().equals(vehicle.getVehicleNo()))
                .findAny()
                .orElseThrow(() -> new ParkingLotException("Vehicle not found.", NO_VEHICLE));
        return foundSlot.getSlotNo();
    }

    public ParkingTicket unPark(Vehicle vehicle) {
        if (isEmpty()) {
            throw new ParkingLotException("Slot is Empty", SLOT_IS_EMPTY);
        }
        ParkingSlot foundSlot = slots.stream()
                .filter(parkingSlot -> parkingSlot.getVehicle().getVehicleNo().equals(vehicle.getVehicleNo()))
                .findFirst()
                .orElseThrow(() -> new ParkingLotException("Vehicle not found.", NO_VEHICLE));

        ParkingTicket currentTicket = parkingTicketList.stream()
                .filter(parkingTicket -> parkingTicket.getVehicleNo().equals(foundSlot.getVehicle().getVehicleNo()))
                .reduce((first, second) -> second)
                .orElseThrow(() -> new ParkingLotException("Vehicle not found.", NO_VEHICLE));

        currentTicket.updateTicketStatus(new Date());

        slots.remove(foundSlot);

       return currentTicket;
    }
}