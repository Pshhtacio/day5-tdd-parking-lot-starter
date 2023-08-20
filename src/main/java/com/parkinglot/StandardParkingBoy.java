package com.parkinglot;

import com.parkinglot.exception.FullCapacityException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.List;

public class StandardParkingBoy extends ParkingBoy {
    private List<ParkingLot> parkingLots;

    public StandardParkingBoy(List<ParkingLot> parkingLots) {
        super(parkingLots);
        this.parkingLots = super.getParkingLots();
    }

    @Override
    public ParkingTicket parkCar(Car car) {
        ParkingLot availableParkingLot = getAvailableParkingLot();
        return availableParkingLot.parkCar(car);
    }

    private ParkingLot getAvailableParkingLot() {
        return parkingLots.stream()
                .filter(parkingLot -> parkingLot.availableSpace(parkingLot) != 0)
                .findFirst()
                .orElseThrow(FullCapacityException::new);
    }

    @Override
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
