package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.exceptions.ParkingLotException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

@SpringBootTest(classes = ParkingService.class)
public class ParkingLotExceptionTest {

    @Value("${slot}")
    private int slots;


    @Test
    public void shouldReturnExpectedExceptionType(){
        ParkingService parkingService1 = new ParkingService();
        ReflectionTestUtils.setField(
                parkingService1,
                "parkingSlots",
                slots);

        parkingService1.addVehicle("MH 14 LK 1234");
        parkingService1.addVehicle("MH 14 LK 1235");
        parkingService1.addVehicle("MH 14 LK 1234");
        parkingService1.addVehicle("MH 14 LK 1235");
        parkingService1.addVehicle("MH 14 LK 1234");
        parkingService1.addVehicle("MH 14 LK 1235");
        parkingService1.addVehicle("MH 14 LK 1234");
        parkingService1.addVehicle("MH 14 LK 1235");
        parkingService1.addVehicle("MH 14 LK 1234");
        parkingService1.addVehicle("MH 14 LK 1235");


        Assertions.assertThatThrownBy(()->{
            parkingService1.addVehicle("MH 12 AJ 1234");
        }).isInstanceOf(ParkingLotException.class);
    }
}
