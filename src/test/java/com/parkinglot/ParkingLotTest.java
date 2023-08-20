package com.parkinglot;

import com.parkinglot.exception.FullCapacityException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class ParkingLotTest {

    private ParkingLot parkingLot;
    private Car car;

    @BeforeEach
    void set_up() {
        parkingLot = new ParkingLot();
        car = new Car();
    }

    @Test
    void should_return_parking_ticket_when_park_car_given_car_and_parking_lot() {
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_parked_car_when_fetch_car_given_parked_car_and_parking_ticket() {
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        Car fetchedCar = parkingLot.fetchCar(parkingTicket);
        Assertions.assertNotNull(fetchedCar);
    }

    @Test
    void should_return_parked_car_for_each_parking_ticket_when_fetch_car_given_two_parked_cars_and_two_parking_tickets() {
        Car car2 = new Car();
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        ParkingTicket parkingTicket2 = parkingLot.parkCar(car2);
        Car fetchedCar = parkingLot.fetchCar(parkingTicket);
        Car fetchedCar2 = parkingLot.fetchCar(parkingTicket2);
        Assertions.assertNotNull(fetchedCar);
        Assertions.assertNotNull(fetchedCar2);
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetch_car_given_wrong_parking_ticket() {
        ParkingTicket wrongParkingTicket = new ParkingTicket(car);
        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetchCar(wrongParkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetch_car_given_used_parking_ticket() {
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        Car fetchedCar = parkingLot.fetchCar(parkingTicket);
        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetchCar(parkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_park_car_given_no_parking_lot_available() {
        ParkingLot newParkingLot = new ParkingLot(1);
        Car car2 = new Car();
        ParkingTicket parkingTicket = newParkingLot.parkCar(car);
        Assertions.assertThrows(FullCapacityException.class, () -> {
            newParkingLot.parkCar(car2);
        });
    }
}
