package com.parkinglot;

import com.parkinglot.exception.FullCapacityException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

public class SmartParkingBoy extends ParkingBoy {
    private List<ParkingLot> parkingLots;

    public SmartParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.parkingLots = super.getParkingLots();
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        ParkingLot selectedParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.availableSpace(parkingLot) != 0)
                .max(Comparator.comparingDouble(this::calculateAvailablePositionRate))
                .orElseThrow(FullCapacityException::new);

        return selectedParkingLot.parkCar(car);
    }

    @Override
    public Car fetchCar(ParkingTicket parkingTicket) {
        ParkingLot location = parkingTicket.getParkingLocation();

        if (location == null) {
            throw new UnrecognizedTicketException();
        }

        ParkingLot selectedParkingLot = parkingLots.stream()
                .filter(parkingLot -> parkingLot.equals(location))
                .max(Comparator.comparingDouble(this::calculateAvailablePositionRate))
                .orElseThrow(UnrecognizedTicketException::new);

        return selectedParkingLot.fetchCar(parkingTicket);
    }

    private double calculateAvailablePositionRate(ParkingLot parkingLot) {
        double totalCapacity = parkingLot.getCapacity();
        double availableSpaces = parkingLot.availableSpace(parkingLot);
        return totalCapacity - availableSpaces;
    }
}
