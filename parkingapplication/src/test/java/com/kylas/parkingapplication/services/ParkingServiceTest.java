package com.kylas.parkingapplication.services;

import com.kylas.parkingapplication.entities.Car;
import com.kylas.parkingapplication.exceptions.ParkingLotException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;


class ParkingServiceTest {


    @Test
    public void givenCarNo_ShouldReturn() throws ParkingLotException {
        ParkingService parkingService = new ParkingService();
        List list = parkingService.addCar("MH 14 LK 1234");
        Assertions.assertThat(list.size()).isEqualTo(1);

    }

    @Test
    public void shouldReturnAllCars() throws ParkingLotException {
        ParkingService parkingService = new ParkingService();
         parkingService.addCar("MH 14 LK 1234");
         parkingService.addCar("MH 14 LK 1235");
        List<Car> cars = parkingService.getCars();
        Assertions.assertThat(cars.size()).isEqualTo(2);

    }

}