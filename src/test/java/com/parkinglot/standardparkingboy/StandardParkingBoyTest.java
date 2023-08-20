package com.parkinglot.standardparkingboy;

import com.parkinglot.Car;
import com.parkinglot.StandardParkingBoy;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.FullCapacityException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

class StandardParkingBoyTest {

    private StandardParkingBoy standardParkingBoy;
    private Car car;

    @BeforeEach
    void set_up() {
        ParkingLot firstParkingLot = new ParkingLot(10);
        ParkingLot secondParkingLot = new ParkingLot(20);
        List<ParkingLot> parkingLots = List.of(firstParkingLot, secondParkingLot);
        standardParkingBoy = new StandardParkingBoy(parkingLots);
        car = new Car();
    }

    @Test
    void should_return_parking_ticket_when_park_car_given_car_a_standard_parking_boy_and_two_parking_lots() {
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);
        Assertions.assertNotNull(parkingTicket);
        Assertions.assertTrue(standardParkingBoy.getParkingLots().get(1).equals(parkingTicket.getParkingLocation()));
    }

    @Test
    void should_return_parked_car_when_fetch_car_given_parked_car_a_parking_ticket_a_standard_parking_boy_and_two_parking_lots() {

        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);
        Car fetchedCar = standardParkingBoy.fetchCar(parkingTicket);
        Assertions.assertNotNull(fetchedCar);
    }

    @Test
    void should_return_parked_car_for_each_parking_ticket_when_fetch_car_given_a_standard_parking_boy_two_parked_cars_two_parking_tickets_and_two_parking_lots() {
        Car car2 = new Car();
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);
        ParkingTicket parkingTicket2 = standardParkingBoy.parkCar(car2);

        Car fetchedCar = standardParkingBoy.fetchCar(parkingTicket);
        Car fetchedCar2 = standardParkingBoy.fetchCar(parkingTicket2);

        Assertions.assertNotNull(fetchedCar);
        Assertions.assertNotNull(fetchedCar2);
        Assertions.assertTrue(standardParkingBoy.getParkingLots().get(1).equals(parkingTicket.getParkingLocation()));
        Assertions.assertTrue(standardParkingBoy.getParkingLots().get(1).equals(parkingTicket2.getParkingLocation()));
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetch_car_a_standard_parking_boy_given_wrong_parking_ticket_two_parked_cars_and_two_parking_lots() {
        ParkingTicket wrongParkingTicket = new ParkingTicket(car);

        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetchCar(wrongParkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetch_car_given_used_parking_ticket_a_standard_parking_boy_and_two_parking_lots() {
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);
        standardParkingBoy.fetchCar(parkingTicket);

        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetchCar(parkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_park_car_given_two_parking_lots_with_full_capacity_a_standard_parking_boy_and_a_car() {
        ParkingLot fullCapacityParkingLot1 = new ParkingLot(0);
        ParkingLot fullCapacityParkingLot2 = new ParkingLot(0);
        List<ParkingLot> fullParkingLots = List.of(fullCapacityParkingLot1, fullCapacityParkingLot2);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(fullParkingLots);

        Assertions.assertThrows(FullCapacityException.class, () -> {
            standardParkingBoy.parkCar(car);
        });
    }
}
