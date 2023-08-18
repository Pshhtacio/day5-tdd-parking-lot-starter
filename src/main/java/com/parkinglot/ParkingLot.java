package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private int capacity = 10;
    private final Map<ParkingTicket, Car> ticketCarHashMap = new HashMap<>();

    public ParkingLot(int limit) {
        this.capacity = limit;
    }

    public ParkingLot() {
    }

    public ParkingTicket parkCar(Car car) {
        if (ticketCarHashMap.size() == capacity) {
            return null;
        }
        ParkingTicket newTicket = generateTicket(car);
        car.setIsParked(addCarToParkingLot(newTicket, car));
        return newTicket;
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        if (!ticketCarHashMap.containsKey(parkingTicket)) {
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

    private boolean addCarToParkingLot(ParkingTicket ticket, Car car) {
        ticketCarHashMap.put(ticket, car);
        return ticketCarHashMap.containsKey(ticket);
    }
}
