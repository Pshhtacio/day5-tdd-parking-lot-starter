package com.parkinglot;

import com.parkinglot.exception.FullCapacityException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.ArrayList;
import java.util.List;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots = new ArrayList<>();

    public ParkingBoy(ParkingLot parkingLots) {
        this.parkingLots.add(parkingLots);
    }

    public ParkingTicket parkCar(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.hasAvailableSpace(parkingLot)) {
                return parkingLot.parkCar(car);
            }
        }
        throw new FullCapacityException();
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        for (ParkingLot parkingLot : parkingLots) {
            Car car = parkingLot.fetchCar(parkingTicket);
            if (car != null) {
                return car;
            }
        }
        throw new UnrecognizedTicketException();
    }
}
