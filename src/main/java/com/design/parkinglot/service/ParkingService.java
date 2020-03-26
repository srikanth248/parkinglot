package com.design.parkinglot.service;

import com.design.parkinglot.model.ParkingFloor;
import com.design.parkinglot.model.VehicleType;
import com.design.parkinglot.repo.FloorRepository;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.Random;
import java.util.Set;

@Service
public class ParkingService {

    private FloorRepository floorRepository;

    public ParkingService(FloorRepository floorRepository) {
        this.floorRepository = floorRepository;
    }

    public Integer getParkingVacancies(VehicleType vehicleType) {
        int vacancies = 0;
        Iterable<ParkingFloor> parkingFloors = floorRepository.findAll();
        for (ParkingFloor parkingFloor : parkingFloors) {
            vacancies += parkingFloor.getAvailableFourWheelerParkingSpots();
            if(vehicleType == VehicleType.TWO_WHEELER) {
                vacancies += parkingFloor.getAvailableTwoWheelerParkingSpots();
            }
        }
        return vacancies;
    }

    @Async
    @Transactional
    public void parkVehicle(VehicleType vehicleType) {
        if(vehicleType == VehicleType.TWO_WHEELER) {
            Set<ParkingFloor> availableFloorsByTwoWheelers = floorRepository.findByAvailableTwoWheelerParkingSpotsGreaterThan(0);
            if(availableFloorsByTwoWheelers.isEmpty()) {
                availableFloorsByTwoWheelers = floorRepository.findByAvailableFourWheelerParkingSpotsGreaterThan(0);
            }
            parkInRandomFloor(availableFloorsByTwoWheelers, true);
        } else {
            Set<ParkingFloor> availableFloors = floorRepository.findByAvailableFourWheelerParkingSpotsGreaterThan(0);
            parkInRandomFloor(availableFloors, false);
        }
    }

    private void parkInRandomFloor(Set<ParkingFloor> availableFloors, boolean twoWheeler) {
        ParkingFloor[] parkingFloors = (ParkingFloor[]) availableFloors.toArray();
        Random rn = new Random();
        ParkingFloor parkedFloor = parkingFloors[rn.nextInt(parkingFloors.length)];
        ParkingFloor updatedParkingFloor = null;
        if(twoWheeler) {
            updatedParkingFloor = new ParkingFloor(parkedFloor.getLevel(), parkedFloor.getTotalFourWheelerParkingSpots(), parkedFloor.getTotalTwoWheelerParkingSpots()-1, parkedFloor.getAvailableFourWheelerParkingSpots(), parkedFloor.getAvailableTwoWheelerParkingSpots());
        } else {
            updatedParkingFloor = new ParkingFloor(parkedFloor.getLevel(), parkedFloor.getTotalFourWheelerParkingSpots(), parkedFloor.getTotalTwoWheelerParkingSpots(), parkedFloor.getAvailableFourWheelerParkingSpots()-1, parkedFloor.getAvailableTwoWheelerParkingSpots());

        }
        floorRepository.save(updatedParkingFloor);
    }

}
