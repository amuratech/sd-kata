package com.kylas.parkingapplication.entities;

import org.assertj.core.api.Assertions;
import org.junit.Test;


public class VehicleTest {

    @Test
    public void testToString(){
        Vehicle vehicle=new Vehicle();

        vehicle.setParkingSlot(1);
        vehicle.setVehicleNo("MH 24 AJ 1234");

        Assertions.assertThat(vehicle.toString()).isEqualTo("Vehicle{" +
                "parkingSlot=" + 1 +
                ", vehicleNo='" + "MH 24 AJ 1234" + '\'' +
                '}');

    }
}