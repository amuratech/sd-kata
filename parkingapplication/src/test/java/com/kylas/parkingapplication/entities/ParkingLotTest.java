package com.kylas.parkingapplication.entities;

import com.kylas.parkingapplication.exceptions.ParkingLotException;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class ParkingLotTest {
    @Test
    public void initialiseParkingLot_shouldBeEmpty() {
        ParkingLot parkingLot = new ParkingLot(5);
        assertThat(parkingLot.isEmpty()).isTrue();
    }

    @Test
    public void initialiseParkingLot_shouldHaveVacantSlot() {
        ParkingLot parkingLot = new ParkingLot(5);
        assertThat(parkingLot.isSlotAvailable()).isTrue();
    }

    @Test
    public void shouldParkNewVehicle() {
        ParkingLot parkingLot = new ParkingLot(5);
        Vehicle vehicle = new Vehicle("MH 12 AA 1234");
        ParkingTicket parkingTicket = parkingLot.park(vehicle);
        assertThat(parkingLot.isEmpty()).isFalse();
        assertThat(parkingLot.size()).isEqualTo(1);
        assertThat(parkingTicket.getSlotNo()).isEqualTo(1);

    }

    @Test
    public void shouldParkTwoVehicles() {
        ParkingLot parkingLot = new ParkingLot(5);
        Vehicle vehicle = new Vehicle("MH 12 AA 1234");
        ParkingTicket parkingTicket = parkingLot.park(vehicle);
        Vehicle vehicle2 = new Vehicle("MH 12 AA 1235");
        ParkingTicket parkingTicket1 = parkingLot.park(vehicle2);

        assertThat(parkingLot.isEmpty()).isFalse();
        assertThat(parkingLot.size()).isEqualTo(2);
        assertThat(parkingTicket.getSlotNo()).isEqualTo(1);
        assertThat(parkingTicket1.getSlotNo()).isEqualTo(2);

    }

    @Test
    public void shouldAssociate_vehicleWithParkingSlot() {
        ParkingLot parkingLot = new ParkingLot(5);
        Vehicle vehicle = new Vehicle("MH 12 AA 1234");
        parkingLot.park(vehicle);
        Vehicle vehicle2 = new Vehicle("MH 12 AA 1235");
        parkingLot.park(vehicle2);

        assertThat(parkingLot.isEmpty()).isFalse();
        assertThat(parkingLot.size()).isEqualTo(2);
        assertThat(parkingLot.getParkingSlot(vehicle)).isEqualTo(1);

    }

    @Test
    public void shouldReturnException_whenUnparkFromEmptySlot() {
        ParkingLot parkingLot = new ParkingLot(5);
        Vehicle vehicle = new Vehicle("MH 12 AA 1234");
        assertThatThrownBy(() -> {
            parkingLot.unPark(vehicle);
        }).isInstanceOf(ParkingLotException.class)
                .hasMessage("Slot is Empty");
    }

    @Test
    public void shouldReturnException_whenUnparkButNoSuchVehicleIsPresent() {
        ParkingLot parkingLot = new ParkingLot(5);
        Vehicle vehicle = new Vehicle("MH 12 AA 1234");
        Vehicle vehicle1 = new Vehicle("MH 12 AA 1235");
        parkingLot.park(vehicle);
        assertThatThrownBy(() -> {
            parkingLot.unPark(vehicle1);
        }).isInstanceOf(ParkingLotException.class)
                .hasMessage("Vehicle not found.");
    }

    @Test
    public void shouldUnpark_whenVehicleIsPresent() {
        ParkingLot parkingLot = new ParkingLot(10);
        Vehicle vehicle = new Vehicle("MH 12 AA 1234");
        Vehicle vehicle1 = new Vehicle("MH 12 AA 1235");
        Vehicle vehicle2 = new Vehicle("MH 12 AA 1236");
        Vehicle vehicle3 = new Vehicle("MH 12 AA 1237");
        Vehicle vehicle4 = new Vehicle("MH 12 AA 1238");
        Vehicle vehicle5 = new Vehicle("MH 12 AA 1239");
        parkingLot.park(vehicle);
        parkingLot.park(vehicle1);
        parkingLot.park(vehicle2);
        parkingLot.park(vehicle3);
        parkingLot.park(vehicle4);
        parkingLot.park(vehicle5);
        assertThat(parkingLot.size()).isEqualTo(6);
        ParkingTicket parkingTicket = parkingLot.unPark(vehicle2);
        assertThat(parkingLot.size()).isEqualTo(5);
        ParkingTicket parkingTicket1= parkingLot.unPark(vehicle4);
        assertThat(parkingLot.size()).isEqualTo(4);
    }


}
