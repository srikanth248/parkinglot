package com.design.parkinglot.controller;

import com.design.parkinglot.exception.InvalidTicketStatusException;
import com.design.parkinglot.model.*;
import com.design.parkinglot.repo.BillingRepository;
import com.design.parkinglot.repo.TicketRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.Instant;

@RestController
public class ExitController {

    private TicketRepository ticketRepository;
    private BillingRepository billingRepository;

    public ExitController(TicketRepository ticketRepository, BillingRepository billingRepository) {
        this.ticketRepository = ticketRepository;
        this.billingRepository = billingRepository;
    }

    @PostMapping
    public ExitResponse exit(Ticket ticket) throws InvalidTicketStatusException {
        Long paymentAmount = 0L;
        if(ticket == null) {
            Billing billing = billingRepository.findByMinHoursGreaterThanAndMaxHoursLessThanEqualAndVehicleType(24, VehicleType.FOUR_WHEELER);
            paymentAmount = billing.getDollarAmount();
            return new ExitResponse("Please pay "+paymentAmount, false, new Ticket(Instant.now(), TicketStatus.LOST, VehicleType.FOUR_WHEELER, true));
        }
        if(!ticket.isTicketActive()) {
            return new ExitResponse("Please insert a valid ticket.", false, ticket);
        }
        if(ticket.getStatus() == TicketStatus.PAID) {
            Ticket updatedTicket = ticketRepository.updateTicketStatusToInactive(ticket);
            return new ExitResponse("Ticket Already Paid. Thank you. Please exit.", true, updatedTicket);
        }
        if(ticket.getStatus() == TicketStatus.UNPAID) {
            Instant exitTime = Instant.now();
            int timeLessThanTheHour = (int) ((exitTime.getEpochSecond() - ticket.getIssueTime().getEpochSecond()) / 3600);
            int hoursToBill = timeLessThanTheHour + 1;
            return new ExitResponse("Please pay "+paymentAmount, false, ticket);
        }
        throw new InvalidTicketStatusException();
    }

    @PostMapping
    public ExitResponse pay(Ticket ticket, Long paymentAmount, boolean atGate) throws InvalidTicketStatusException {
        if(ticket == null || ticket.getStatus() == TicketStatus.LOST) {
            if(ticket == null) {
                ticket = new Ticket(Instant.now(), TicketStatus.LOST, VehicleType.FOUR_WHEELER, true);
            }
            processPayment(paymentAmount, atGate, ticket);
        } else if(ticket.getStatus() == TicketStatus.PAID) {
            return new ExitResponse("This ticket is already paid.", false, ticket);
        } else if(ticket.getStatus() == TicketStatus.UNPAID) {
            processPayment(paymentAmount, atGate, ticket);
        }
        throw new InvalidTicketStatusException();
    }

    private ExitResponse processPayment(long paymentAmount, boolean atGate, Ticket ticket) {
        long ticketCost = 0L;
        Billing billing = billingRepository.findByMinHoursGreaterThanAndMaxHoursLessThanEqualAndVehicleType(24, ticket.getVehicleType());
        ticketCost = billing.getDollarAmount();
        if(paymentAmount >= ticketCost) {
            Ticket paidTicket = ticketRepository.updateTicketStatusToPaid(ticket);
            if (atGate) {
                Ticket updatedTicket = ticketRepository.updateTicketStatusToInactive(paidTicket);
                return new ExitResponse("Ticket Already Paid. Thank you. Please exit.", true, updatedTicket);
            } else {
                return new ExitResponse("Thank you for the payment. Please exit with in 20 minutes.", false, paidTicket);
            }
        } else {
            return new ExitResponse("Please pay the exact amount.", false, ticket);
        }
    }
}
