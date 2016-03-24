import exception.ParkingLotFullException;
import observers.*;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import parkingPlace.Car;
import parkingPlace.ParkingLot;
import parkingPlace.ParkingLots;
import parkingPlace.Token;

import static org.junit.Assert.*;

public class ParkingAttendantTest {
    @org.junit.Rule
    public ExpectedException thrown = ExpectedException.none();


    @Test
    public void parking_attendant_can_park_a_car_and_give_the_token_to_car_owner() throws ParkingLotFullException {
        ParkingLots parkingLots = new ParkingLots();
        parkingLots.add(new ParkingLot(2));
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        Token token1 = parkingAttendant.parkACar(new Car());
        Token token2 = parkingAttendant.parkACar(new Car());
        assertEquals(new Token(0, 0),token1);
        assertEquals(new Token(0, 1),token2);
    }

    @Test
    public void parking_attendant_can_park_a_car_if_space_is_available() throws ParkingLotFullException {
        ParkingLots parkingLots = new ParkingLots();
        parkingLots.add(new ParkingLot(1));
        parkingLots.add(new ParkingLot(1));
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        Token token1 = parkingAttendant.parkACar(new Car());
        assertEquals(new Token(0, 0), token1);
        Token token2 = parkingAttendant.parkACar(new Car());
        assertEquals(new Token(1, 0), token2);
    }

    @Test
    public void parking_attendant_can_check_whether_the_lot_is_full() throws ParkingLotFullException {
        ParkingLots parkingLots = new ParkingLots();
        parkingLots.add(new ParkingLot(2));
        parkingLots.add(new ParkingLot(2));

        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        parkingAttendant.parkACar(new Car());
        parkingAttendant.parkACar(new Car());
        boolean lotFull;
        lotFull = parkingAttendant.checkIfLotIsFull();
        assertFalse(lotFull);
        parkingAttendant.parkACar(new Car());
        parkingAttendant.parkACar(new Car());
        lotFull = parkingAttendant.checkIfLotIsFull();
        assertTrue(lotFull);
    }
    @Test
    public void parking_attendant_get_notified_if_lot_is_full() throws ParkingLotFullException {
        ParkingLots parkingLots = new ParkingLots();
        parkingLots.add(new ParkingLot(2));
        parkingLots.add(new ParkingLot(2));

        thrown.expect(ParkingLotFullException.class);
        thrown.expectMessage("Parking lot is full");
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        parkingAttendant.parkACar(new Car());
        parkingAttendant.parkACar(new Car());
        boolean lotFull;
        lotFull = parkingAttendant.checkIfLotIsFull();
        assertFalse(lotFull);
        parkingAttendant.parkACar(new Car());
        parkingAttendant.parkACar(new Car());
        lotFull = parkingAttendant.checkIfLotIsFull();
        assertTrue(lotFull);
        parkingAttendant.parkACar(new Car());
    }

    @Test
    public void parking_attendant_can_unpark_a_car() throws ParkingLotFullException {
        ParkingLots parkingLots = new ParkingLots();
        parkingLots.add(new ParkingLot(2));
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        Car car1 = new Car();
        Car car2 = new Car();
        Token token1 = parkingAttendant.parkACar(car1);
        Token token2 = parkingAttendant.parkACar(car2);
        assertEquals(car1, parkingAttendant.unparkCar(token1));
    }

    @Test
    public void parking_attendant_have_an_assistant_who_can_monitor_the_parking_lot() throws ParkingLotFullException {
        ParkingLots parkingLots = new ParkingLots();
        parkingLots.add(new ParkingLot(2));
        parkingLots.add(new ParkingLot(2));
        ParkingLotObserver assistant = new Assistant();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        parkingAttendant.addObserver(assistant);
        parkingAttendant.parkACar(new Car());
        parkingAttendant.parkACar(new Car());
        parkingAttendant.parkACar(new Car());
        parkingAttendant.parkACar(new Car());
        assertEquals(4, assistant.parkedCars());
    }

    @Test
    public void manager_will_notify_when_lot_get_eighty_percent_full() throws ParkingLotFullException {
        ParkingLots parkingLots = new ParkingLots();
        parkingLots.add(new ParkingLot(5));
        parkingLots.add(new ParkingLot(5));
        ParkingLotObserver manager = new Manager();
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        parkingAttendant.addObserver(manager);
        for (int i = 0; i < 8; i++){
            parkingAttendant.parkACar(new Car());
        }
        assertEquals(true, manager.isEightyPercentFull());
    }

    @Test
    public void parking_attendant_should_be_able_to_know_whether_he_can_promote_or_not() throws ParkingLotFullException {
        ParkingLots parkingLots = new ParkingLots();
        parkingLots.add(new ParkingLot(5));
        parkingLots.add(new ParkingLot(5));
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        parkingAttendant.parkACar(new Car());
        parkingAttendant.parkACar(new Car());
        assertTrue(parkingAttendant.canPromote());
    }
    @Test
    public void civic_body_shold_be_able_to_know_if_lot_is_80_percent_full() throws ParkingLotFullException {
        ParkingLots parkingLots = new ParkingLots();
        parkingLots.add(new ParkingLot(5));
        parkingLots.add(new ParkingLot(5));
        ParkingAttendant parkingAttendant = new ParkingAttendant(parkingLots);
        ParkingLotObserver civicBody = new Manager();
        parkingAttendant.addObserver(civicBody);
        for (int i = 0; i < 8; i++){
            parkingAttendant.parkACar(new Car());
        }
        assertTrue(civicBody.isEightyPercentFull());
    }
}