package com.parkinglot;

public class Car {
    ParkingTicket parkingTicket = null;
    int carId;
    private boolean isParked;

    public Car(int carId) {
        this.carId = carId;

    }

    public ParkingTicket getParkingTicket() {
        return parkingTicket;
    }

    public int getCarId() {
        return carId;
    }

    public boolean isParked() {
        return isParked;
    }

    public void setParkingTicket(ParkingTicket newTicket) {
        this.parkingTicket = newTicket;
    }

    public void setIsParked(boolean isParked) {
        this.isParked = isParked;
    }
}
