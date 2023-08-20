package com.parkinglot;

import java.util.Objects;

public class ParkingTicket {
    int ticketId;
    ParkingLot parkingLocation;

    public ParkingTicket(Car car) {
        this.ticketId = car.carId;
    }

    public ParkingLot getParkingLocation() {
        return parkingLocation;
    }

    public void setParkingLocation(ParkingLot parkingLot) {
        this.parkingLocation = parkingLot;
    }

}
