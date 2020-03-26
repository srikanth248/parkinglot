package com.design.parkinglot.model;

import javax.persistence.*;
import java.time.Instant;
import java.util.Objects;

@Entity
public class Ticket {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name = "issue_time", columnDefinition = "timestamp")
    private Instant issueTime;
    @Column(name = "status", columnDefinition = "varchar2(10)")
    @Convert(converter = TicketStatusConverter.class)
    private TicketStatus status;
    @Column(name = "vehicle_type", columnDefinition = "varchar2(10)")
    @Convert(converter = VehicleTypeConverter.class)
    private VehicleType vehicleType;
    private boolean isTicketActive;

    public Ticket(Instant issueTime, TicketStatus status, VehicleType vehicleType, boolean isTicketActive) {
        this.issueTime = issueTime;
        this.status = status;
        this.vehicleType = vehicleType;
        this.isTicketActive = isTicketActive;
    }

    public Ticket() {
    }

    public Instant getIssueTime() {
        return issueTime;
    }

    public TicketStatus getStatus() {
        return status;
    }

    public VehicleType getVehicleType() {
        return vehicleType;
    }

    public boolean isTicketActive() {
        return isTicketActive;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Ticket ticket = (Ticket) o;
        return isTicketActive == ticket.isTicketActive &&
                Objects.equals(id, ticket.id) &&
                Objects.equals(issueTime, ticket.issueTime) &&
                status == ticket.status &&
                vehicleType == ticket.vehicleType;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, issueTime, status, vehicleType, isTicketActive);
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", issueTime=" + issueTime +
                ", status=" + status +
                ", vehicleType=" + vehicleType +
                ", isTicketActive=" + isTicketActive +
                '}';
    }
}
