package com.design.parkinglot.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class Billing {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private int minHours;
    private int maxHours;
    private VehicleType vehicleType;
    private Long dollarAmount;

    public Billing() {
    }

    public Long getDollarAmount() {
        return dollarAmount;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Billing billing = (Billing) o;
        return minHours == billing.minHours &&
                maxHours == billing.maxHours &&
                Objects.equals(id, billing.id) &&
                vehicleType == billing.vehicleType &&
                Objects.equals(dollarAmount, billing.dollarAmount);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, minHours, maxHours, vehicleType, dollarAmount);
    }

    @Override
    public String toString() {
        return "Billing{" +
                "id=" + id +
                ", minHours=" + minHours +
                ", maxHours=" + maxHours +
                ", vehicleType=" + vehicleType +
                ", dollarAmount=" + dollarAmount +
                '}';
    }
}
