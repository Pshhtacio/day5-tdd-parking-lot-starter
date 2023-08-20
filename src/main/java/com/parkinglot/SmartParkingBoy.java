package com.parkinglot;

import com.parkinglot.exception.FullCapacityException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.Comparator;
import java.util.List;

public class SmartParkingBoy extends ParkingBoy {

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        ParkingLot selectedParkingLot = getParkingLots().stream()
                .filter(parkingLot -> parkingLot.availableSpace() > 0)
                .max(Comparator.comparingInt(ParkingLot::availableSpace))
                .orElseThrow(FullCapacityException::new);

        return selectedParkingLot.parkCar(car);
    }

    @Override
    public Car fetchCar(ParkingTicket parkingTicket) {
        ParkingLot location = parkingTicket.getParkingLocation();

        if (location == null) {
            throw new UnrecognizedTicketException();
        }

        return location.fetchCar(parkingTicket);
    }
}
