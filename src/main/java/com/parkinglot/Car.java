package com.parkinglot;

public class Car {
    int carId;
    private boolean isParked;
    private boolean isFetched;

    public Car(int carId) {
        this.carId = carId;

    }

    public int getCarId() {
        return carId;
    }

    public boolean isParked() {
        return isParked;
    }

    public boolean IsFetched() {
        return this.isFetched;
    }

    public void setIsParked(boolean isParked) {
        this.isParked = isParked;
    }

    public void setIsFetched(boolean isFetched) {
        this.isFetched = isFetched;
    }

}
