package com.parkinglot;

import com.parkinglot.exception.FullCapacityException;
import com.parkinglot.exception.UnrecognizedTicketException;

import java.util.List;

public class ParkingBoy {
    private List<ParkingLot> parkingLots;

    public ParkingBoy(List<ParkingLot> parkingLots) {
        this.parkingLots = parkingLots;
    }

    public ParkingTicket parkCar(Car car) {
        for (ParkingLot parkingLot : parkingLots) {
            if (parkingLot.availableSpace(parkingLot) <= parkingLot.getCapacity()) {
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

    public List<ParkingLot> getParkingLots() {
        return parkingLots;
    }
}
