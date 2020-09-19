package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.entities.Vehicle;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.List;


@ExtendWith(SpringExtension.class)
@SpringBootTest(classes = ParkingService.class)
class ParkingServiceTest {

    @Value("${slot}")
    private int slots;

    @Test
    public void givenCarNo_ShouldReturn() {
        ParkingService parkingService = new ParkingService();
        ReflectionTestUtils.setField(
                parkingService,
                "parkingSlots",
                slots);
        Vehicle vehicle = parkingService.addVehicle("MH 14 LK 1234");
        Assertions.assertThat(vehicle.getVehicleNo()).isEqualTo("MH 14 LK 1234");

    }

    @Test
    public void shouldReturnAllCars()  {
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

//    @Test
//    public void shouldReturnExpectedExceptionType(){
//        ParkingService parkingService = new ParkingService();
//        ReflectionTestUtils.setField(
//                parkingService,
//                "parkingSlots",
//                10);
//       // Assertions.assertThrows()
//    }

}