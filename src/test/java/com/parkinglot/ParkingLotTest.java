package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    ParkingLot parkingLot = new ParkingLot();
    Car car = new Car(0);

    @Test
    void should_return_a_parking_ticket_when_parkCar_given_a_car_and_a_parkingLot() {
        //When
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        //Then
        Assertions.assertTrue(parkingTicket.isValid);
        Assertions.assertTrue(car.isParked());
    }

    @Test
    void should_return_a_parkedCar_when_fetchParkedCar_given_a_parkedCar_and_a_parkingTicket() {
        //Given
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        //When
        Car fetchedCar = parkingLot.fetchCar(parkingTicket);
        //Then
        Assertions.assertFalse(fetchedCar.isParked());
        Assertions.assertTrue(fetchedCar.IsFetched());
    }

}