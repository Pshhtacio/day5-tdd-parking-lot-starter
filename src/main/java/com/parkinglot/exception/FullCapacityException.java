package com.parkinglot.exception;

public class FullCapacityException extends RuntimeException{
    public FullCapacityException(){
        super("No available position.");
    }
}

