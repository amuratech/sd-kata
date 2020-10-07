package com.kylas.parkingapplication.entities;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.DynamicUpdate;

import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@DynamicUpdate
@Entity
@Table(name = "ticket")
public class ParkingTicket {



    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int ticketId;

    private int slotNo;

    private String vehicleNo;

    private Date inTime;

    private Date outTime;

    public ParkingTicket(ParkingSlot parkingSlot, Date inTime) {
        this.slotNo = parkingSlot.getSlotNo();
        this.vehicleNo = parkingSlot.getVehicle().getVehicleNo();
        this.inTime = inTime;

    }


    public void updateTicketStatus(Date date) {
        this.outTime = date;
    }
}