package com.design.parkinglot.controller;

import com.design.parkinglot.exception.ParkingLotFullException;
import com.design.parkinglot.exception.UnsupportedVehicleException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@RestControllerAdvice
public class EntryControllerAdvice {

    @ExceptionHandler(ParkingLotFullException.class)
    public void handleParkingFull(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Parking Lot Full.");
    }

    @ExceptionHandler(UnsupportedVehicleException.class)
    public void handleUnsupportedVehicle(HttpServletResponse response) throws IOException {
        response.sendError(HttpStatus.BAD_REQUEST.value(), "Your Vehicle is not supported.");
    }
}
