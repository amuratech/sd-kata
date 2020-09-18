package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.entities.Vehicle;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;


class ParkingServiceTest {



    @Test
    public void givenCarNo_ShouldReturn() {
        ParkingService parkingService = new ParkingService();
        ReflectionTestUtils.setField(
                parkingService,
                "parkingSlots",
                10);
        Vehicle vehicle = parkingService.addVehicle("MH 14 LK 1234");
        Assertions.assertThat(vehicle.getVehicleNo()).isEqualTo("MH 14 LK 1234");

    }

    @Test
    public void shouldReturnAllCars()  {
        ParkingService parkingService = new ParkingService();
        ReflectionTestUtils.setField(
                parkingService,
                "parkingSlots",
                10);
         parkingService.addVehicle("MH 14 LK 1234");
         parkingService.addVehicle("MH 14 LK 1235");
        List<Vehicle> vehicles = parkingService.getVehicles();
        Assertions.assertThat(vehicles.size()).isEqualTo(2);

    }

}