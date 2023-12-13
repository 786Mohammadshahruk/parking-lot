import org.example.Car;
import org.example.CarISNotParkedException;
import org.example.ParkingCapacityExceedException;
import org.example.ParkingLot;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;


public class ParkingLotTest {


    @Test
    void shouldBeAbleToParkACar() throws ParkingCapacityExceedException {
        Car car = new Car(false);
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.park(car);
    }

    @Test
    void shouldNotAbleToParkACar() throws ParkingCapacityExceedException {
        Car car = new Car(true);
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLot.park(car);
    }

    @Test
    void shouldThrowParkingCapacityExceedException() throws ParkingCapacityExceedException {
        Car car1 = new Car(false);
        Car car2 = new Car(false);
        Car car3 = new Car(false);
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.park(car1);
        parkingLot.park(car2);
        Assertions.assertThrows(ParkingCapacityExceedException.class, () -> parkingLot.park(car3));
    }


    @Test
    void shouldBeAbleToUnPark() throws CarISNotParkedException {
        Car car = new Car(true);
        ParkingLot parkingLot = new ParkingLot(5);
        Assertions.assertTrue(parkingLot.unPark(car));
    }

    @Test
    void shouldNotAbleToUnPark() {
        Car car = new Car(false);
        ParkingLot parkingLot = new ParkingLot(5);
        Assertions.assertThrows(CarISNotParkedException.class, () -> parkingLot.unPark(car));
    }

    @Test
    void shouldMaxCapacityNot() throws ParkingCapacityExceedException, CarISNotParkedException {
        Car car = new Car(false);
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.park(car);
        Assertions.assertFalse(parkingLot.unPark(car));
    }
}
