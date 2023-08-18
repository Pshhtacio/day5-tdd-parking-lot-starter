package com.parkinglot;

import java.util.Objects;

public class ParkingTicket {
    int ticketId;
    boolean isValid = false;

    public ParkingTicket(Car car) {
        isValid = !(car.isParked());
    }

    public boolean isValid() {
        return isValid;
    }

}
