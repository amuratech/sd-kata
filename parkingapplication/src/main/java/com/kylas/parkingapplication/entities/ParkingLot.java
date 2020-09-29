package com.kylas.parkingapplication.entities;

import com.kylas.parkingapplication.exceptions.ParkingLotException;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import static com.kylas.parkingapplication.exceptions.ParkingLotException.ExceptionType.*;

public class ParkingLot {


    private final int capacity;
    private final List<ParkingSlot> slots;

    public ParkingLot(int capacity) {
        this.capacity = capacity;
        slots = new ArrayList<>(capacity);
        for (int i = 0; i < capacity; i++) {
            slots.add(new ParkingSlot(i + 1, null)); // lots are added in slot
        }
    }

    public boolean isEmpty() {
        return slots.stream().allMatch(parkingSlot -> parkingSlot.getVehicle() == null);
    }

    public boolean isSlotAvailable() {
        for (int i = 0; i < capacity; i++) {
            ParkingSlot currentSlot = slots.get(i);
            if (currentSlot.getVehicle() == null)
                return true;
        }

        return false;
    }

    public ParkingSlot park(Vehicle vehicle) {
        if (!isSlotAvailable())
            throw new ParkingLotException("Parking Lot is Full", NO_PARKING_AVAILABLE);

        for (int i = 0; i < capacity; i++) {

            ParkingSlot currentSlot = slots.get(i);

            if (currentSlot.getVehicle() == null) {
                currentSlot.park(vehicle);
                return currentSlot;
            }
        }
        return null;
    }

    public long size() {
        return slots.stream().filter(parkingSlot -> parkingSlot.getVehicle() != null).count();
    }

    public List<ParkingSlot> listVehicles() {

        return slots.stream()
                .filter(parkingSlot -> parkingSlot.getVehicle() != null).collect(Collectors.toList());

    }

    public int getParkingSlot(Vehicle vehicle) {
        ParkingSlot foundSlot = slots.stream()
                .filter(parkingSlot -> parkingSlot.getVehicle().getVehicleNo().equals(vehicle.getVehicleNo()))
                .findAny()
                .orElseThrow(() -> new ParkingLotException("Vehicle not found.", NO_VEHICLE));
        return foundSlot.getSlotNo();
    }

    public boolean unPark(Vehicle vehicle) {
        if (isEmpty()) {
            throw new ParkingLotException("Slot is Empty", SLOT_IS_EMPTY);
        } else {
            for (int i = 0; i < capacity; i++) {

                ParkingSlot currentSlot = slots.get(i);

                if (currentSlot.getVehicle() != null && currentSlot.getVehicle().getVehicleNo().equals(vehicle.getVehicleNo())) {
                    currentSlot.unPark(currentSlot.getVehicle());
                    return true;
                }
            }
        }
        throw new ParkingLotException("Vehicle not found.", NO_VEHICLE);
    }

}
