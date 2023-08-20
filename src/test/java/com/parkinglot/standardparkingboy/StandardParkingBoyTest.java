package com.parkinglot.standardparkingboy;

import com.parkinglot.Car;
import com.parkinglot.StandardParkingBoy;
import com.parkinglot.ParkingLot;
import com.parkinglot.ParkingTicket;
import com.parkinglot.exception.FullCapacityException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

class StandardParkingBoyTest {
    ParkingLot parkingLot = new ParkingLot();
    List<ParkingLot> parkingLots = List.of(parkingLot);
    Car car = new Car();
    StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLots);

    @Test
    void should_return_a_parking_ticket_when_parkCar_given_a_car_and_a_parkingLot() {
        //When
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);
        //Then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_a_parkedCar_when_fetchCar_given_a_parkedCar_and_a_parkingTicket() {
        //Given
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);
        //When
        Car fetchedCar = standardParkingBoy.fetchCar(parkingTicket);
        //Then
        Assertions.assertNotNull(fetchedCar);
    }

    @Test
    void should_return_a_parkedCar_for_each_parkingTicket_when_fetchCar_given_two_parkedCar_and_two_parkingTickets() {
        //Given
        Car car2 = new Car();
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);
        ParkingTicket parkingTicket2 = standardParkingBoy.parkCar(car2);
        //When
        Car fetchedCar = standardParkingBoy.fetchCar(parkingTicket);
        Car fetchedCar2 = standardParkingBoy.fetchCar(parkingTicket2);
        //Then
        Assertions.assertNotNull(fetchedCar);
        Assertions.assertNotNull(fetchedCar2);
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetchCar_given_wrong_parkingTicket() {
        //Given
        ParkingTicket wrongParkingTicket = new ParkingTicket(car);
        //When & Then
        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetchCar(wrongParkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetchCar_given_used_parkingTicket() {
        //Given
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);
        //When
        Car fetchedCar = standardParkingBoy.fetchCar(parkingTicket);
        //Then
        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetchCar(parkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_parkCar_given_no_parkingLot_available() {
        //Given
        ParkingLot fullCapacityParkingLot = new ParkingLot(0);
        List<ParkingLot> parkingLot = List.of(fullCapacityParkingLot);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        Car car2 = new Car();
        //When and Then
        Assertions.assertThrows(FullCapacityException.class, () -> {
            standardParkingBoy.parkCar(car2);
        });
    }

    //Story 4 test cases
    @Test
    void should_return_a_parking_ticket_when_parkCar_given_a_car_a_standard_parkingBoy_and_two_parkingLot() {
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLot = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy newStandardParkingBoy = new StandardParkingBoy(parkingLot);
        //When
        ParkingTicket parkingTicket = newStandardParkingBoy.parkCar(car);
        //Then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_a_parkedCar_when_fetchCar_given_a_parkedCar_a_parkingTicket_a_standard_parkingBoy_and_two_parkingLot() {
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLot = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy newStandardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingTicket parkingTicket = newStandardParkingBoy.parkCar(car);
        //When
        Car fetchedCar = newStandardParkingBoy.fetchCar(parkingTicket);
        //Then
        Assertions.assertNotNull(fetchedCar);
    }

    @Test
    void should_return_a_parkedCar_for_each_parkingTicket_when_fetchCar_given_a_standard_parkingBoy_two_parkedCar_two_parkingTickets_and_two_parking_lot() {
        //Given
        Car car2 = new Car();
        ParkingTicket parkingTicket = standardParkingBoy.parkCar(car);
        ParkingTicket parkingTicket2 = standardParkingBoy.parkCar(car2);
        //When
        Car fetchedCar = standardParkingBoy.fetchCar(parkingTicket);
        Car fetchedCar2 = standardParkingBoy.fetchCar(parkingTicket2);
        //Then
        Assertions.assertNotNull(fetchedCar);
        Assertions.assertNotNull(fetchedCar2);
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetchCar_a_standard_parkingBoy_given_wrong_parkingTicket_two_parked_car_and_two_parking_lot() {
        //Given
        ParkingTicket wrongParkingTicket = new ParkingTicket(car);
        //When & Then
        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            standardParkingBoy.fetchCar(wrongParkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetchCar_given_used_parkingTicket_a_standard_parkingBoy_and_two_parkingLot() {
        //Given
        ParkingLot firstParkingLot = new ParkingLot();
        ParkingLot secondParkingLot = new ParkingLot();
        List<ParkingLot> parkingLot = List.of(firstParkingLot, secondParkingLot);
        StandardParkingBoy newStandardParkingBoy = new StandardParkingBoy(parkingLot);
        ParkingTicket parkingTicket = newStandardParkingBoy.parkCar(car);
        //When
        Car fetchedCar = newStandardParkingBoy.fetchCar(parkingTicket);
        //Then
        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            newStandardParkingBoy.fetchCar(parkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_parkCar_given_two_parkingLot_with_full_capacity_a_standard_parkingBoy_and_a_car() {
        //Given
        ParkingLot fullCapacityParkingLot1 = new ParkingLot(0);
        ParkingLot fullCapacityParkingLot2 = new ParkingLot(0);
        List<ParkingLot> parkingLot = List.of(fullCapacityParkingLot1,fullCapacityParkingLot2);
        StandardParkingBoy standardParkingBoy = new StandardParkingBoy(parkingLot);
        //When and Then
        Assertions.assertThrows(FullCapacityException.class, () -> {
            standardParkingBoy.parkCar(car);
        });
    }

}