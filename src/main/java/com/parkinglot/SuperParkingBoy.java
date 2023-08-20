package com.parkinglot;

import com.parkinglot.exception.FullCapacityException;

import java.util.Comparator;
import java.util.List;

public class SuperParkingBoy extends ParkingBoy {

    public SuperParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        ParkingLot selectedParkingLot = getParkingLots().stream()
                .filter(parkingLot -> parkingLot.availableSpace() > 0)
                .max(Comparator.comparingDouble(ParkingLot::availableSpaceRate))
                .orElseThrow(FullCapacityException::new);

        return selectedParkingLot.parkCar(car);
    }
}
