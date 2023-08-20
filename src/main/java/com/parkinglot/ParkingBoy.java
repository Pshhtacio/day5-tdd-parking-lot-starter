package com.parkinglot;

import com.parkinglot.exception.FullCapacityException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

public class ParkingBoy {
    private final List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket parkCar(Car car) {
        Optional<ParkingLot> selectedParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.availableSpace() > 0)
                .findFirst();

        return selectedParkingLot.map(parkingLot -> parkingLot.parkCar(car))
                .orElseThrow(FullCapacityException::new);
    }

    public Car fetchCar(ParkingTicket parkingTicket) {
        return parkingLots.stream()
                .map(parkingLot -> parkingLot.fetchCar(parkingTicket))
                .filter(Objects::nonNull)
                .findFirst()
                .orElseThrow(UnrecognizedTicketException::new);
    }

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
