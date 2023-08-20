package com.parkinglot;

import com.parkinglot.exception.CapacityLimitException;
import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

class ParkingLotTest {
    ParkingLot parkingLot = new ParkingLot();
    Car car = new Car();
    ParkingBoy parkingBoy = new ParkingBoy(parkingLot);

    @Test
    void should_return_a_parking_ticket_when_parkCar_given_a_car_and_a_parkingLot() {
        //When
        ParkingTicket parkingTicket = parkingBoy.parkCar(car);
        //Then
        Assertions.assertNotNull(parkingTicket);
    }

    @Test
    void should_return_a_parkedCar_when_fetchCar_given_a_parkedCar_and_a_parkingTicket() {
        //Given
        ParkingTicket parkingTicket = parkingBoy.parkCar(car);
        //When
        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);
        //Then
        Assertions.assertNotNull(fetchedCar);
    }

    @Test
    void should_return_a_parkedCar_for_each_parkingTicket_when_fetchCar_given_two_parkedCar_and_two_parkingTickets() {
        //Given
        Car car2 = new Car();
        ParkingTicket parkingTicket = parkingBoy.parkCar(car);
        ParkingTicket parkingTicket2 = parkingBoy.parkCar(car2);
        //When
        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);
        Car fetchedCar2 = parkingBoy.fetchCar(parkingTicket2);
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
            parkingBoy.fetchCar(wrongParkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetchCar_given_used_parkingTicket() {
        //Given
        ParkingTicket parkingTicket = parkingBoy.parkCar(car);
        //When
        Car fetchedCar = parkingBoy.fetchCar(parkingTicket);
        //Then
        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            parkingBoy.fetchCar(parkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_parkCar_given_no_parkingLot_available() {
        //Given
        ParkingLot newParkingLot = new ParkingLot(1);
        ParkingBoy newParkingBoy = new ParkingBoy(newParkingLot);
        Car car2 = new Car();
        ParkingTicket parkingTicket = newParkingBoy.parkCar(car);
        //When and Then
        Assertions.assertThrows(CapacityLimitException.class, () -> {
            newParkingBoy.parkCar(car2);
        });
    }
}