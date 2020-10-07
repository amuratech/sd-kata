
package com.kylas.parkingapplication.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.kylas.parkingapplication.entities.ParkingSlot;
import com.kylas.parkingapplication.entities.ParkingTicket;
import com.kylas.parkingapplication.entities.Vehicle;
import com.kylas.parkingapplication.repository.ParkingTicketRepository;
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
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
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

    @Autowired
    private ParkingTicketRepository parkingTicketRepository;

    @MockBean
    private ParkingService parkingService;

    @MockBean
    private ParkingTicket parkingTicket;

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
        Vehicle mockVehicle1 = new Vehicle("111");
        ParkingSlot slot = new ParkingSlot(1, mockVehicle1);
        parkingTicket=new ParkingTicket(slot,new Date());

        Mockito.when(parkingService.park(ArgumentMatchers.any())).thenReturn(parkingTicket);
        String json = mapper.writeValueAsString(slot);
        mockMvc
                .perform(
                        post("/vehicles").queryParam("vehicleNo", mockVehicle1.getVehicleNo()).contentType(MediaType.APPLICATION_JSON).characterEncoding("utf-8")
                                .content(json).accept(MediaType.APPLICATION_JSON)).andExpect(status().isOk())
                .andExpect(jsonPath("$.slotNo", Matchers.equalTo(1)))
                .andExpect(jsonPath("$.vehicleNo", Matchers.equalTo("111")));
    }


    @Test
    public void testGetVehicles() throws Exception {

        Vehicle mockVehicle1 = new Vehicle("111");
        Vehicle mockVehicle2 = new Vehicle("112");
        ParkingSlot slot = new ParkingSlot(1, mockVehicle1);
        ParkingSlot slot1 = new ParkingSlot(2, mockVehicle2);
        ParkingTicket parkingTicket=new ParkingTicket(slot,new Date());
        ParkingTicket parkingTicket1=new ParkingTicket(slot1,new Date());

        List<ParkingSlot> vehicleList = new ArrayList<>();
        vehicleList.add(slot);
        vehicleList.add(slot1);

        List<ParkingTicket> parkingTicketList=new ArrayList<>();
        parkingTicketList.add(parkingTicket);
        parkingTicketList.add(parkingTicket1);

        Mockito.when(parkingService.getVehicles()).thenReturn(parkingTicketList);

        mockMvc.perform(get("/vehicles"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", Matchers.hasSize(2)))
                .andExpect(jsonPath("$[1].slotNo", Matchers.equalTo(2)))
                .andExpect(jsonPath("$[1].vehicleNo", Matchers.equalTo("112")));

    }
//
    @Test
    public void deleteVehicles() throws Exception {
        Vehicle mockVehicle1 = new Vehicle("111");
        Vehicle mockVehicle2 = new Vehicle("112");
        ParkingSlot slot = new ParkingSlot(1, mockVehicle1);
        ParkingSlot slot1 = new ParkingSlot(2, mockVehicle2);
        ParkingTicket parkingTicket=new ParkingTicket(slot,new Date());
        ParkingTicket parkingTicket1=new ParkingTicket(slot1,new Date());

        List<ParkingSlot> vehicleList = new ArrayList<>();
        vehicleList.add(slot);
        vehicleList.add(slot1);

        List<ParkingTicket> parkingTicketList=new ArrayList<>();
        parkingTicketList.add(parkingTicket);
        parkingTicketList.add(parkingTicket1);

        Mockito.when(parkingService.unPark(ArgumentMatchers.anyString())).thenReturn("Vehicle unparked");
        MvcResult requestResult = mockMvc
                .perform(delete("/vehicles")
                        .param("vehicleNo", "111"))
                .andExpect(status().isOk()).andReturn();
        String result = requestResult.getResponse().getContentAsString();
        assertThat(result.contains("Vehicle Exit Done")).isEqualTo(true);

    }
}