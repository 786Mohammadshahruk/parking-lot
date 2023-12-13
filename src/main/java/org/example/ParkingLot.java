package org.example;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class ParkingLot {
    public int parkingCapacity;
    public Set<Car> cars;

    private List<ParkingLotObserver> parkingLotObserver;

    public ParkingLot(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
        cars = new HashSet<>();
        parkingLotObserver = new ArrayList<>();
    }


    public void park(Car car) throws ParkingCapacityExceedException {
        if (cars.size() == parkingCapacity) {
            throw new ParkingCapacityExceedException("Parking Capacity Exceed");
        }
        if (isParked(car)) {
            new VehicleAlreadyParkedException();
        }
        cars.add(car);
        if (cars.size() == parkingCapacity) {
            parkingLotObserver.forEach(ParkingLotObserver::notifyParkingFull);
        }
    }

    public boolean isParked(Car car) {
        return cars.contains(car);
    }


    public void unPark(Car car) {
        cars.remove(car);
        if (cars.size() + 1 == parkingCapacity) {
            parkingLotObserver.forEach(ParkingLotObserver::notifyParkingAvailable);
        }


    }

    public void register(ParkingLotObserver parkingLotObserver) {
        this.parkingLotObserver.add(parkingLotObserver);
    }
}
