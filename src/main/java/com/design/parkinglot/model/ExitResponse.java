package com.design.parkinglot.model;

import java.util.Objects;

public class ExitResponse {

    private String response;
    private boolean shouldGateOpen;
    private Ticket ticket;

    public ExitResponse(String response, boolean shouldGateOpen, Ticket ticket) {
        this.response = response;
        this.shouldGateOpen = shouldGateOpen;
        this.ticket = ticket;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ExitResponse that = (ExitResponse) o;
        return shouldGateOpen == that.shouldGateOpen &&
                Objects.equals(response, that.response) &&
                Objects.equals(ticket, that.ticket);
    }

    @Override
    public int hashCode() {
        return Objects.hash(response, shouldGateOpen, ticket);
    }

    @Override
    public String toString() {
        return "ExitResponse{" +
                "response='" + response + '\'' +
                ", shouldGateOpen=" + shouldGateOpen +
                ", ticket=" + ticket +
                '}';
    }
}
