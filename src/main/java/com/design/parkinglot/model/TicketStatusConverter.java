package com.design.parkinglot.model;

import org.springframework.util.StringUtils;

import javax.persistence.AttributeConverter;

public class TicketStatusConverter implements AttributeConverter<TicketStatus, String> {
    @Override
    public String convertToDatabaseColumn(TicketStatus ticketStatus) {
        return ticketStatus.name();
    }

    @Override
    public TicketStatus convertToEntityAttribute(String s) {
        if(StringUtils.isEmpty(s)) {
            return null;
        } else {
            return TicketStatus.valueOf(s);
        }
    }
}
