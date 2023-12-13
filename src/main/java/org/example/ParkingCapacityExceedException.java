package org.example;

public class ParkingCapacityExceedException extends Exception {

    ParkingCapacityExceedException(String message) {
        super(message);
    }
}
