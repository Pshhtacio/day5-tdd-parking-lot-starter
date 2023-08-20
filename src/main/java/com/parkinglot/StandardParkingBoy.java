package com.parkinglot;

import com.parkinglot.exception.FullCapacityException;

import java.util.List;
import java.util.Optional;

public class StandardParkingBoy extends ParkingBoy {

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        Optional<ParkingLot> availableParkingLot = getAvailableParkingLot();
        return availableParkingLot.map(parkingLot -> parkingLot.parkCar(car))
                .orElseThrow(FullCapacityException::new);
    }

    private Optional<ParkingLot> getAvailableParkingLot() {
        return getParkingLots().stream()
                .filter(parkingLot -> parkingLot.availableSpace() > 0)
                .findFirst();
    }
}
