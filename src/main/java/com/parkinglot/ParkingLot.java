package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity;
    private final Map<ParkingTicket, Car> ticketCarHashMap = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
    }

    public ParkingTicket parkCar(Car car) {
        if (ticketCarHashMap.size() >= capacity) {
            displayExceptionMessage();
            return null;
        }
        ParkingTicket newTicket = generateTicket(car);
        car.setIsParked(true);
        ticketCarHashMap.put(newTicket, car);
        return newTicket;
    }

    private void displayExceptionMessage() {
        throw new UnrecognizedTicketException();
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if (parkingTicket == null || !ticketCarHashMap.containsKey(parkingTicket)) {
            displayExceptionMessage();
            return null;
        }

        Car fetchedCar = ticketCarHashMap.remove(parkingTicket);
        fetchedCar.setIsParked(false);
        fetchedCar.setIsFetched(true);

        return fetchedCar;
    }

    private ParkingTicket generateTicket(Car car) {
        return new ParkingTicket(car);
    }
}
