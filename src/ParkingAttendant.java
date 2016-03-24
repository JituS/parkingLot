import exception.ParkingLotFullException;
import observers.*;
import parkingPlace.Car;
import parkingPlace.ParkingLots;
import parkingPlace.Token;

import java.util.ArrayList;

public class ParkingAttendant {

    private final ArrayList<ParkingLotObserver> observers;
    private ParkingLots parkingLots;

    public ParkingAttendant(ParkingLots parkingLots) {
        this.parkingLots = parkingLots;
        this.observers = new ArrayList<ParkingLotObserver>();
    }

    public Token parkACar(Car car) throws ParkingLotFullException {
        Token token = parkingLots.parkCar(car);
        for (ParkingLotObserver observer : observers) {
            observer.observe(parkingLots.totalCars(), parkingLots.totalSpace());
        }
        return token;
    }

    public boolean checkIfLotIsFull() {
        return parkingLots.isFull();
    }

    public Car unparkCar(Token token) {
        return parkingLots.findCar(token);
    }


    public void addObserver(ParkingLotObserver observer) {
        observers.add(observer);
    }

    public boolean canPromote() {
        return parkingLots.totalCars() <= (int) (Math.round(parkingLots.totalSpace()*0.2));
    }
}