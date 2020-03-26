package com.design.parkinglot.repo;

import com.design.parkinglot.model.Ticket;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

public interface TicketRepository extends CrudRepository<Ticket, Long> {

    @Query("UPDATE TICKET SET STATUS=FALSE")
    Ticket updateTicketStatusToInactive(Ticket ticket);
}
