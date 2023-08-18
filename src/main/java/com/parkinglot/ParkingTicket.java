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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ParkingTicket)) return false;
        ParkingTicket that = (ParkingTicket) o;
        return ticketId == that.ticketId;
    }

    @Override
    public int hashCode() {
        return Objects.hash(ticketId);
    }
}
