import org.example.*;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.Mockito;


public class ParkingLotTest {

    @Mock
    private ParkingLotObserver parkingLotObserver;

    @BeforeEach
    void setUp() {
        parkingLotObserver = Mockito.mock(ParkingLotObserver.class);
    }



    @Test
    void shouldBeAbleToParkACar() throws ParkingCapacityExceedException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.park(car);
    }

    @Test
    void shouldNotAbleToParkACar() throws ParkingCapacityExceedException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(10);
        parkingLot.park(car);
    }

    @Test
    void shouldThrowParkingCapacityExceedException() throws ParkingCapacityExceedException {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot = new ParkingLot(2);
        parkingLot.register(parkingLotObserver);
        parkingLot.park(car1);
        parkingLot.park(car2);
        Assertions.assertThrows(ParkingCapacityExceedException.class, () -> parkingLot.park(car3));
    }


    @Test
    void shouldBeAbleToUnPark() throws CarISNotParkedException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.unPark(car);
    }

    @Test
    void shouldNotAbleToUnPark() {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.unPark(car);
    }

    @Test
    void shouldMaxCapacityNot() throws ParkingCapacityExceedException {
        Car car = new Car();
        ParkingLot parkingLot = new ParkingLot(5);
        parkingLot.park(car);
        parkingLot.unPark(car);
    }

    @Test
    void checkNotifyParkingFull() throws ParkingCapacityExceedException {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot = new ParkingLot(3);

        parkingLot.register(parkingLotObserver);

        parkingLot.park(car1);
        parkingLot.park(car2);
        parkingLot.park(car3);

        Mockito.verify(parkingLotObserver).notifyParkingFull();
    }

    @Test
    void checkNotifyParkingAvailable() throws ParkingCapacityExceedException {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot = new ParkingLot(3);

        parkingLot.register(parkingLotObserver);

        parkingLot.park(car1);
        parkingLot.park(car2);
        parkingLot.park(car3);
        parkingLot.unPark(car2);

        Mockito.verify(parkingLotObserver).notifyParkingFull();
        Mockito.verify(parkingLotObserver).notifyParkingAvailable();
    }
    @Test
    void checkNotifyParkingAvailable1() throws ParkingCapacityExceedException {
        Car car1 = new Car();
        Car car2 = new Car();
        Car car3 = new Car();
        ParkingLot parkingLot = new ParkingLot(3);

        parkingLot.register(parkingLotObserver);

        parkingLot.park(car1);
        parkingLot.park(car2);
        parkingLot.park(car3);

        parkingLot.unPark(new Car());

        Mockito.verify(parkingLotObserver).notifyParkingFull();
        Mockito.verify(parkingLotObserver,Mockito.times(0)).notifyParkingAvailable();
    }

}
