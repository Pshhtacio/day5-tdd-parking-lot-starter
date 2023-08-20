package com.parkinglot;

import java.util.Objects;

public class ParkingTicket {
    int ticketId;

    public ParkingTicket(Car car) {
        this.ticketId = car.carId;
    }
}
