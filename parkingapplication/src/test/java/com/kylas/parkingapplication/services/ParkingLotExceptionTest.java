package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.entities.ParkingLot;
import com.kylas.parkingapplication.entities.Vehicle;
import com.kylas.parkingapplication.exceptions.ParkingLotException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

public class ParkingLotExceptionTest {

    @Test
    public void shouldReturnExpectedExceptionType() {
        ParkingLot parkingLot = new ParkingLot(3);
        Vehicle vehicle1 = new Vehicle("MH 14 LK 1234");
        Vehicle vehicle2 = new Vehicle("MH 14 LK 1235");
        Vehicle vehicle3 = new Vehicle("MH 14 LK 1236");
        Vehicle vehicle4 = new Vehicle("MH 14 LK 1237");

        parkingLot.park(vehicle1);
        parkingLot.park(vehicle2);
        parkingLot.park(vehicle3);

        Assertions.assertThatThrownBy(() -> {
            parkingLot.park(vehicle4);
        }).isInstanceOf(ParkingLotException.class)
                .hasMessage("Parking Lot is Full");
    }
}