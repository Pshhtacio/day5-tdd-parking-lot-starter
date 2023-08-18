package com.parkinglot;

import java.util.ArrayList;
import java.util.List;

public class ParkingLot {
    List<Car> parkedCar = new ArrayList<>();
    private Car car;

    public ParkingTicket parkCar(Car car) {
        car.setParkingTicket(issueNewParkingTicket(car));
        car.setIsParked(addCarToParkingLot(car));
        return car.parkingTicket;
    }

    private ParkingTicket issueNewParkingTicket(Car car) {
        return new ParkingTicket(car);
    }

    private boolean addCarToParkingLot(Car car) {
        return parkedCar.add(car);
    }
}
