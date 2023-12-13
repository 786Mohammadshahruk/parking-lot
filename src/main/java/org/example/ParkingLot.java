package org.example;

public class ParkingLot {
    public int parkingCapacity;
    public int maxCapacity;

    public ParkingLot(int parkingCapacity) {
        this.parkingCapacity = parkingCapacity;
        this.maxCapacity = parkingCapacity;
    }

    public boolean checkCapacity() {
        return parkingCapacity <= 0;
    }

    public void park(Car car) throws ParkingCapacityExceedException {
        if (checkCapacity()) {
            throw new ParkingCapacityExceedException("Parking Capacity Exceed");
        }
        car.isParked = true;
        parkingCapacity--;

    }

    public boolean unPark(Car car) throws CarISNotParkedException {
        if(this.parkingCapacity != maxCapacity){
            return false;
        }
        if (car.isParked) {
            this.parkingCapacity++;
            car.isParked = false;
            return true;
        } else {
            throw new CarISNotParkedException("Car is Not Parked");
        }
    }
}
