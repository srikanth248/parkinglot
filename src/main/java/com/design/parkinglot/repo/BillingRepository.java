package com.design.parkinglot.repo;

import com.design.parkinglot.model.Billing;
import com.design.parkinglot.model.VehicleType;
import org.springframework.data.repository.CrudRepository;

public interface BillingRepository extends CrudRepository<Billing, Integer> {
    Billing findByMinHoursGreaterThanAndMaxHoursLessThanEqualAndVehicleType(int numberOfBillableHours, VehicleType vehicleType);
}
