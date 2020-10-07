package com.kylas.parkingapplication.repository;

import com.kylas.parkingapplication.entities.ParkingTicket;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ParkingTicketRepository extends JpaRepository<ParkingTicket,Integer> {
}
