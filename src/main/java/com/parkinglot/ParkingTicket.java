package com.parkinglot;

public class ParkingTicket {
    boolean isValid = false;
    int carId;

    public ParkingTicket(Car car) {
        this.carId = car.carId;
        isValid = true;
    }

    public boolean isValid() {
        return isValid;
    }

    public int getCarId() {
        return carId;
    }
}
