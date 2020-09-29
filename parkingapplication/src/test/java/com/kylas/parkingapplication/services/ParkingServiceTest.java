package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.entities.ParkingLot;
import com.kylas.parkingapplication.entities.Vehicle;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class ParkingServiceTest {

    @Test
    public void getVehicles() {
        ParkingLot parkingLot = new ParkingLot(3);
        assertThat(parkingLot.size()).isEqualTo(0);

        Vehicle vehicle1 = new Vehicle("MH 14 LK 1234");
        parkingLot.park(vehicle1);

        Vehicle vehicle2 = new Vehicle("MH 14 LK 1235");
        parkingLot.park(vehicle2);

        Vehicle vehicle3 = new Vehicle("MH 14 LK 1236");
        parkingLot.park(vehicle3);

        assertThat(parkingLot.listVehicles().get(0).getSlotNo()).isEqualTo(1);
        assertThat(parkingLot.listVehicles().get(0).getVehicle().getVehicleNo()).isEqualTo("MH 14 LK 1234");

        assertThat(parkingLot.listVehicles().get(1).getSlotNo()).isEqualTo(2);
        assertThat(parkingLot.listVehicles().get(1).getVehicle().getVehicleNo()).isEqualTo("MH 14 LK 1235");

        assertThat(parkingLot.listVehicles().get(2).getSlotNo()).isEqualTo(3);
        assertThat(parkingLot.listVehicles().get(2).getVehicle().getVehicleNo()).isEqualTo("MH 14 LK 1236");

    }

    @Test
    public void park() {
        ParkingLot parkingLot = new ParkingLot(3);
        assertThat(parkingLot.size()).isEqualTo(0);

        Vehicle vehicle1 = new Vehicle("MH 14 LK 1234");
        parkingLot.park(vehicle1);
        assertThat(parkingLot.size()).isEqualTo(1L);

        Vehicle vehicle2 = new Vehicle("MH 14 LK 1235");
        parkingLot.park(vehicle2);
        assertThat(parkingLot.size()).isEqualTo(2L);

        Vehicle vehicle3 = new Vehicle("MH 14 LK 1236");
        parkingLot.park(vehicle3);
        assertThat(parkingLot.size()).isEqualTo(3);

    }

    @Test
    public void unPark() {
        ParkingLot parkingLot = new ParkingLot(3);
        assertThat(parkingLot.size()).isEqualTo(0);

        Vehicle vehicle1 = new Vehicle("MH 14 LK 1234");
        parkingLot.park(vehicle1);
        assertThat(parkingLot.size()).isEqualTo(1L);

        Vehicle vehicle2 = new Vehicle("MH 14 LK 1235");
        parkingLot.park(vehicle2);
        assertThat(parkingLot.size()).isEqualTo(2L);

        Vehicle vehicle3 = new Vehicle("MH 14 LK 1236");
        parkingLot.park(vehicle3);
        assertThat(parkingLot.size()).isEqualTo(3L);


        assertThat(parkingLot.unPark(vehicle1)).isEqualTo(true);
        assertThat(parkingLot.size()).isEqualTo(2L);
    }
}