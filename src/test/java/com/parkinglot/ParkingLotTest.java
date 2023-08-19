package com.parkinglot;

import com.parkinglot.exception.UnrecognizedTicketException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

class ParkingLotTest {
    ParkingLot parkingLot = new ParkingLot();
    Car car = new Car();

    @Test
    void should_return_a_parking_ticket_when_parkCar_given_a_car_and_a_parkingLot() {
        //When
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        //Then
        Assertions.assertTrue(parkingTicket.isValid);
        Assertions.assertTrue(car.isParked());
    }

    @Test
    void should_return_a_parkedCar_when_fetchCar_given_a_parkedCar_and_a_parkingTicket() {
        //Given
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        //When
        Car fetchedCar = parkingLot.fetchCar(parkingTicket);
        //Then
        Assertions.assertFalse(fetchedCar.isParked());
        Assertions.assertTrue(fetchedCar.isFetched());
    }

    @Test
    void should_return_a_parkedCar_for_each_parkingTicket_when_fetchCar_given_two_parkedCar_and_two_parkingTickets() {
        //Given
        Car car2 = new Car();
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        ParkingTicket parkingTicket2 = parkingLot.parkCar(car2);
        //When
        Car fetchedCar = parkingLot.fetchCar(parkingTicket);
        Car fetchedCar2 = parkingLot.fetchCar(parkingTicket2);
        //Then
        Assertions.assertFalse(fetchedCar.isParked());
        Assertions.assertTrue(fetchedCar.isFetched());
        Assertions.assertFalse(fetchedCar2.isParked());
        Assertions.assertTrue(fetchedCar2.isFetched());
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetchCar_given_wrong_parkingTicket() {
        //Given
        ParkingTicket wrongParkingTicket = new ParkingTicket(car);
        //When & Then
        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetchCar(wrongParkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_fetchCar_given_used_parkingTicket() {
        //Given
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        //When
        Car fetchedCar = parkingLot.fetchCar(parkingTicket);
        //Then
        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            parkingLot.fetchCar(parkingTicket);
        });
    }

    @Test
    void should_return_nothing_and_an_error_message_when_parkCar_given_no_parkingLot_available() {
        //Given
        ParkingLot newParkingLot = new ParkingLot(1);
        Car car2 = new Car();
        ParkingTicket parkingTicket = newParkingLot.parkCar(car);
        //When and Then
        Assertions.assertThrows(UnrecognizedTicketException.class, () -> {
            newParkingLot.parkCar(car2);
        });
    }


}