package com.parkinglot.exception;

public class fullCapacityException extends RuntimeException{
    public fullCapacityException(){
        super("No available position.");
    }
}

