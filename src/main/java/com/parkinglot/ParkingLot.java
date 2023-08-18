package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final Map<ParkingTicket, Car> ticketLog = new HashMap<>();

    public ParkingTicket parkCar(Car car) {
        ParkingTicket newTicket = generateTicket(car);
        car.setIsParked(addCarToParkingLot(newTicket, car));
        return newTicket;
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if (!ticketLog.containsKey(parkingTicket)){
            return null;
        }
        Car parkedCar = ticketLog.get(parkingTicket);
        parkedCar.setIsParked(false);
        parkedCar.setIsFetched(true);
        return parkedCar;
    }

    private ParkingTicket generateTicket(Car car) {
        return new ParkingTicket(car);
    }

    private boolean addCarToParkingLot(ParkingTicket ticket, Car car) {
        ticketLog.put(ticket, car);
        return ticketLog.containsKey(ticket);
    }
}
