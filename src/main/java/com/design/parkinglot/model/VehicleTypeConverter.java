package com.design.parkinglot.model;

import javax.persistence.AttributeConverter;

public class VehicleTypeConverter implements AttributeConverter<VehicleType, String> {

    @Override
    public String convertToDatabaseColumn(VehicleType vehicleType) {
        return vehicleType.name();
    }

    @Override
    public VehicleType convertToEntityAttribute(String s) {
        try {
            return VehicleType.valueOf(s);
        } catch(Exception e) {
            return null;
        }
    }
}
