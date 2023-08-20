package com.parkinglot.parkingboy;

import com.parkinglot.Car;
import com.parkinglot.ParkingBoy;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.FullCapacityException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class ParkingBoyTest {
    private ParkingLot parkingLot;
    private ParkingBoy parkingBoy;
    private Car car;

    @BeforeEach
    void set_up() {
        parkingLot = new ParkingLot();
        List<ParkingLot> parkingLots = List.of(parkingLot);
        parkingBoy = new ParkingBoy(parkingLots);
        car = new Car();
    }

    @Test
    void should_return_parking_ticket_when_park_car_given_car_and_parking_lot() {
        ParkingTicket parkingTicket = parkingBoy.parkCar(car);
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_parked_car_when_fetch_car_given_parked_car_and_parking_ticket() {
        ParkingTicket parkingTicket = parkingBoy.parkCar(car);
        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);
        Assertions.assertNotNull(fetchedCar);
    }

    @Test
    void should_return_parked_car_for_each_parking_ticket_when_fetch_car_given_two_parked_cars_and_two_parking_tickets() {
        Car car2 = new Car();
        ParkingTicket parkingTicket = parkingBoy.parkCar(car);
        ParkingTicket parkingTicket2 = parkingBoy.parkCar(car2);

        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);
        Car fetchedCar2 = parkingBoy.fetchCar(parkingTicket2);

        Assertions.assertNotNull(fetchedCar);
        Assertions.assertNotNull(fetchedCar2);
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetch_car_given_wrong_parking_ticket() {
        ParkingTicket wrongParkingTicket = new ParkingTicket(car);
        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetchCar(wrongParkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetch_car_given_used_parking_ticket() {
        ParkingTicket parkingTicket = parkingBoy.parkCar(car);
        parkingBoy.fetchCar(parkingTicket);

        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetchCar(parkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_park_car_given_no_parking_lot_available() {
        ParkingLot fullCapacityParkingLot = new ParkingLot(0);
        List<ParkingLot> parkingLots = List.of(fullCapacityParkingLot);
        ParkingBoy parkingBoy = new ParkingBoy(parkingLots);
        Car car2 = new Car();

        Assertions.assertThrows(FullCapacityException.class, () -> {
            parkingBoy.parkCar(car2);
        });
    }
}