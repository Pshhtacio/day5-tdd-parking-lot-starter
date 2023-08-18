package com.parkinglot;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ParkingLotTest {
    @Test
    void should_return_a_parking_ticket_when_parkCar_given_a_car_and_a_parkingLot() {
        //Given
        ParkingLot parkingLot = new ParkingLot();
        Car car = new Car(0);
        //When
        ParkingTicket parkingTicket = parkingLot.parkCar(car);
        //Then
        Assertions.assertEquals(true, parkingTicket.isValid);
        Assertions.assertEquals(true, car.isParked());
    }

}