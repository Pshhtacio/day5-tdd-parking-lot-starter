package com.parkinglot;

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
    void should_return_nothing_when_fetchCar_given_wrong_parkingTicket() {
        //Given
        ParkingTicket wrongParkingTicket = new ParkingTicket(car);
        //When
        Car fetchedCar = parkingLot.fetchCar(wrongParkingTicket);
        //Then
        Assertions.assertNull(fetchedCar);
    }

    @Test
    void should_return_nothing_when_fetchCar_given_used_parkingTicket() {
        //Given
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        //When
        Car fetchedCar = parkingLot.fetchCar(parkingTicket);
        Car fetchUsingUsedTicket = parkingLot.fetchCar(parkingTicket);
        //Then
        Assertions.assertNull(fetchUsingUsedTicket);
    }

    @Test
    void should_return_nothing_when_parkCar_given_no_parkingLot_available() {
        //Given
        ParkingLot newParkingLot = new ParkingLot(1);
        Car car2 = new Car();
        ParkingTicket parkingTicket = newParkingLot.parkCar(car);
        //When
        ParkingTicket parkingTicket2 = newParkingLot.parkCar(car2);
        //Then
        Assertions.assertNull(parkingTicket2);
    }


}