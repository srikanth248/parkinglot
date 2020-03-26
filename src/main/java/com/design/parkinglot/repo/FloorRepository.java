package com.design.parkinglot.repo;

import com.design.parkinglot.model.ParkingFloor;
import org.springframework.data.repository.CrudRepository;

import java.util.Set;

public interface FloorRepository extends CrudRepository<ParkingFloor, Integer> {
    Set<ParkingFloor> findByAvailableFourWheelerParkingSpotsGreaterThan(int i);
    Set<ParkingFloor> findByAvailableTwoWheelerParkingSpotsGreaterThan(int i);
}
