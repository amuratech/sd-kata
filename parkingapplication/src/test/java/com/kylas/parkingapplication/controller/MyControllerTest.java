
package com.kylas.parkingapplication.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kylas.parkingapplication.entities.Vehicle;
import com.kylas.parkingapplication.services.ParkingService;
import org.hamcrest.Matchers;
import org.junit.Before;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;


import org.mockito.ArgumentMatchers;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;


import java.util.ArrayList;
import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@AutoConfigureMockMvc
@SpringBootTest
@ExtendWith(SpringExtension.class)
public class MyControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private MyController myController;

    @MockBean
    private ParkingService parkingService;

    private static ObjectMapper mapper = new ObjectMapper();


    @Before
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(myController)
                .build();
    }

    @Test
    public void testHome() throws Exception {

        mockMvc.perform(get("/home"))
                .andExpect(status().isOk())
                .andExpect(content().string("Welcome to my Parking Service"));
    }


    @Test
    public void testAddVehicle() throws Exception {
        Vehicle vehicle = new Vehicle();
        vehicle.setParkingSlot(1);
        vehicle.setVehicleNo("MH 12 AJ 1234");
        Mockito.when(parkingService.addVehicle(ArgumentMatchers.any())).thenReturn(vehicle);
        String json = mapper.writeValueAsString(vehicle);
        mockMvc
                .perform(
                        post("/vehicles").queryParam("vehicleNo", vehicle.getVehicleNo()).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.parkingSlot", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.vehicleNo", Matchers.equalTo("MH 12 AJ 1234")));
    }


    @Test
    public void testGetVehicles() throws Exception {

        Vehicle mockVehicle1 = new Vehicle();
        mockVehicle1.setParkingSlot(1);
        mockVehicle1.setVehicleNo("MH 24 AJ 1234");

        Vehicle mockVehicle2 = new Vehicle();
        mockVehicle2.setParkingSlot(2);
        mockVehicle2.setVehicleNo("MH 24 AJ 1235");


        List<Vehicle> vehicleList = new ArrayList<>();
        vehicleList.add(mockVehicle1);
        vehicleList.add(mockVehicle2);

        Mockito.when(parkingService.getVehicles()).thenReturn(vehicleList);

        //then
        mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[1].vehicleNo", Matchers.equalTo("MH 24 AJ 1235")));


    }

//    @Test
//    public void testExitVehicles() throws Exception {
//
//        Vehicle mockVehicle1 = new Vehicle();
//        mockVehicle1.setParkingSlot(1);
//        mockVehicle1.setVehicleNo("MH 24 AJ 1234");
//
//        Vehicle mockVehicle2 = new Vehicle();
//        mockVehicle2.setParkingSlot(2);
//        mockVehicle2.setVehicleNo("MH 24 AJ 1235");
//
//
//        List<Vehicle> vehicleList = new ArrayList<>();
//        vehicleList.add(mockVehicle1);
//        vehicleList.add(mockVehicle2);
//
//
//        //then
//        mockMvc.perform(delete("/vehicles"))
//                .andExpect(status().isOk())
//                 .accept(MediaType.APPLICATION_JSON))
//                .andExpect()
//
//
//    }

}