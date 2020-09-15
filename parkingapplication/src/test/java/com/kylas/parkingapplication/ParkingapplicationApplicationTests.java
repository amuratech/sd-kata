package com.kylas.parkingapplication;

import com.kylas.parkingapplication.controller.MyController;
import com.kylas.parkingapplication.exceptions.ParkingLotException;
import com.kylas.parkingapplication.services.ParkingService;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class ParkingapplicationApplicationTests {
	MyController myController=null;
	ParkingService parkingService=null;
	ParkingService service= Mockito.mock(ParkingService.class);

	public ParkingapplicationApplicationTests()
	{
		myController=new MyController();
		parkingService=new ParkingService();
	}


	@Test
	public void myControllerTest() {
		Assertions.assertThat(myController.home()).isEqualTo("Welcome to my Parking Service");
	}
	@Test
	public void parkingServiceDefaultTest()
	{
		Assertions.assertThat(parkingService.getCars().size()).isEqualTo(2);

	}
	@Test
	public void parkingServicegetCarTest()
	{
		Assertions.assertThat(parkingService.getCars().isEmpty()).isEqualTo(false);
	}

	@Test
	public void parkingServiceAddCarTest() throws ParkingLotException {
		 parkingService.addCar("MH 12 io 9087");
		parkingService.addCar("MH 12 io 9088");
		parkingService.addCar("MH 12 io 9089");
		parkingService.addCar("MH 12 io 9090");
		Assertions.assertThat(parkingService.getCars().size()).isEqualTo(6);
	}


}
