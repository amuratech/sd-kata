package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.entities.Vehicle;
import com.kylas.parkingapplication.exceptions.ParkingLotException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;



@SpringBootTest(classes = ParkingService.class)
class ParkingServiceTest {

    @Value("${slot}")
    private int slots;

    @Test
    public void givenCarNo_ShouldVehicle() {
        ParkingService parkingService = new ParkingService();
        ReflectionTestUtils.setField(
                parkingService,
                "parkingSlots",
                slots);
        Vehicle vehicle = parkingService.addVehicle("MH 14 LK 1234");
        Assertions.assertThat(vehicle.getVehicleNo()).isEqualTo("MH 14 LK 1234");

    }

    @Test
    public void shouldReturnAllVehicle()  {
        ParkingService parkingService = new ParkingService();
        ReflectionTestUtils.setField(
                parkingService,
                "parkingSlots",
                slots);
         parkingService.addVehicle("MH 14 LK 1234");
         parkingService.addVehicle("MH 14 LK 1235");
        List<Vehicle> vehicles = parkingService.getVehicles();
        Assertions.assertThat(vehicles.size()).isEqualTo(2);

    }

    @Test
    public void shouldExitVehicle()  {
        ParkingService parkingService = new ParkingService();
        ReflectionTestUtils.setField(
                parkingService,
                "parkingSlots",
                slots);
        parkingService.addVehicle("MH 14 LK 1234");
        parkingService.addVehicle("MH 14 LK 1235");
        parkingService.addVehicle("MH 14 LK 1236");
        parkingService.addVehicle("MH 14 LK 1237");
        String status = parkingService.deleteVehicle("MH 14 LK 1235");
        Assertions.assertThat(status).isEqualTo("Vehicle exit");

    }



}