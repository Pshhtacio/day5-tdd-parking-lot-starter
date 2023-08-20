package com.parkinglot.smartparkingboy;

import com.parkinglot.*;
import com.parkinglot.exception.FullCapacityException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SmartParkingBoyTest {

    private ParkingLot parkingLot;
    private SmartParkingBoy smartParkingBoy;
    private Car car;

    @BeforeEach
    void set_up() {
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(20);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        smartParkingBoy = new SmartParkingBoy(parkingLots);
        car = new Car();
    }
    @Test
    void should_return_parking_ticket_when_park_car_given_car_a_standard_parking_boy_and_two_parking_lots() {
        ParkingTicket parkingTicket = smartParkingBoy.parkCar(car);
        Assertions.assertNotNull(parkingTicket);
        Assertions.assertTrue(smartParkingBoy.getParkingLots().get(1).equals(parkingTicket.getParkingLocation()));
    }

    @Test
    void should_return_parked_car_when_fetch_car_given_parked_car_a_parking_ticket_a_standard_parking_boy_and_two_parking_lots() {
        ParkingTicket parkingTicket = smartParkingBoy.parkCar(car);
        Car fetchedCar = smartParkingBoy.fetchCar(parkingTicket);
        Assertions.assertNotNull(fetchedCar);
    }

    @Test
    void should_return_parked_car_for_each_parking_ticket_when_fetch_car_given_a_standard_parking_boy_two_parked_cars_two_parking_tickets_and_two_parking_lots() {
        Car car2 = new Car();
        ParkingTicket parkingTicket = smartParkingBoy.parkCar(car);
        ParkingTicket parkingTicket2 = smartParkingBoy.parkCar(car2);

        Car fetchedCar = smartParkingBoy.fetchCar(parkingTicket);
        Car fetchedCar2 = smartParkingBoy.fetchCar(parkingTicket2);

        Assertions.assertNotNull(fetchedCar);
        Assertions.assertNotNull(fetchedCar2);
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetch_car_a_standard_parking_boy_given_wrong_parking_ticket_two_parked_cars_and_two_parking_lots() {
        ParkingTicket wrongParkingTicket = new ParkingTicket(car);

        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            smartParkingBoy.fetchCar(wrongParkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetch_car_given_used_parking_ticket_a_standard_parking_boy_and_two_parking_lots() {
        ParkingTicket parkingTicket = smartParkingBoy.parkCar(car);
        smartParkingBoy.fetchCar(parkingTicket);

        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            smartParkingBoy.fetchCar(parkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_park_car_given_two_parking_lots_with_full_capacity_a_standard_parking_boy_and_a_car() {
        ParkingLot fullCapacityParkingLot1 = new ParkingLot(0);
        ParkingLot fullCapacityParkingLot2 = new ParkingLot(0);
        List<ParkingLot> parkingLots = List.of(fullCapacityParkingLot1, fullCapacityParkingLot2);
        SmartParkingBoy smartParkingBoy = new SmartParkingBoy(parkingLots);

        Assertions.assertThrows(FullCapacityException.class, () -> {
            smartParkingBoy.parkCar(car);
        });
    }
}