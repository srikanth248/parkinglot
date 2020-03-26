package com.design.parkinglot.controller;

import com.design.parkinglot.exception.ParkingLotFullException;
import com.design.parkinglot.exception.UnsupportedVehicleException;
import com.design.parkinglot.model.Ticket;
import com.design.parkinglot.model.TicketStatus;
import com.design.parkinglot.model.VehicleType;
import com.design.parkinglot.repo.TicketRepository;
import com.design.parkinglot.service.ParkingService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;


@RestController
public class EntryController {

    private TicketRepository ticketRepository;
    private ParkingService parkingService;

    public EntryController(TicketRepository ticketRepository, ParkingService parkingService) {
        this.ticketRepository = ticketRepository;
        this.parkingService = parkingService;
    }

    @GetMapping("/entry")
    public Ticket getTicket(String vehicleType) throws ParkingLotFullException, UnsupportedVehicleException {
        VehicleType vt;
        try {
            vt = VehicleType.valueOf(vehicleType);
        } catch(Exception ex) {
            throw new UnsupportedVehicleException();
        }
        Integer parkingVacancies = parkingService.getParkingVacancies(vt);
        if (parkingVacancies < 1) {
            throw new ParkingLotFullException();
        }
        Ticket ticket = new Ticket(Instant.now(), TicketStatus.UNPAID, vt, true);
        ticketRepository.save(ticket);
        //TODO: Start Async Park Process
        return ticket;
    }

}
