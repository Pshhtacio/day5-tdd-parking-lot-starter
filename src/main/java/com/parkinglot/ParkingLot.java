package com.parkinglot;

import java.util.HashMap;
import java.util.Map;

public class ParkingLot {
    private final int DEFAULT_CAPACITY = 10;
    private final int capacity;
    private final Map<ParkingTicket, Car> ticketCarHashMap = new HashMap<>();

    public ParkingLot(int capacity) {
        this.capacity = capacity;
    }

    public ParkingLot() {
        this.capacity = DEFAULT_CAPACITY;
    }

    public ParkingTicket parkCar(Car car) {
        ParkingTicket newTicket = generateTicket(car);
        ticketCarHashMap.put(newTicket, car);
        return newTicket;
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        return ticketCarHashMap.remove(parkingTicket);
    }

    private ParkingTicket generateTicket(Car car) {
        return new ParkingTicket(car);
    }

    public boolean hasAvailableSpace(ParkingLot parkingLot) {
        return !(parkingLot.ticketCarHashMap.size() >= capacity);
    }
}
