package com.design.parkinglot.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Objects;

@Entity
public class ParkingFloor {

    @Id
    private Integer level;
    private Integer availableFourWheelerParkingSpots;
    private Integer availableTwoWheelerParkingSpots;

    public ParkingFloor(Integer level, Integer totalFourWheelerParkingSpots, Integer totalTwoWheelerParkingSpots, Integer availableFourWheelerParkingSpots, Integer availableTwoWheelerParkingSpots) {
        this.level = level;
        this.availableFourWheelerParkingSpots = availableFourWheelerParkingSpots;
        this.availableTwoWheelerParkingSpots = availableTwoWheelerParkingSpots;
    }

    public ParkingFloor() {
    }

    public Integer getAvailableFourWheelerParkingSpots() {
        return availableFourWheelerParkingSpots;
    }

    public Integer getAvailableTwoWheelerParkingSpots() {
        return availableTwoWheelerParkingSpots;
    }

    public Integer getLevel() {
        return level;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ParkingFloor that = (ParkingFloor) o;
        return Objects.equals(level, that.level) &&
                Objects.equals(availableFourWheelerParkingSpots, that.availableFourWheelerParkingSpots) &&
                Objects.equals(availableTwoWheelerParkingSpots, that.availableTwoWheelerParkingSpots);
    }

    @Override
    public int hashCode() {
        return Objects.hash(level, availableFourWheelerParkingSpots, availableTwoWheelerParkingSpots);
    }

    @Override
    public String toString() {
        return "ParkingFloor{" +
                "level=" + level +
                ", availableFourWheelerParkingSpots=" + availableFourWheelerParkingSpots +
                ", availableTwoWheelerParkingSpots=" + availableTwoWheelerParkingSpots +
                '}';
    }
}
