package parkingPlace;

import exception.ParkingLotFullException;

import java.util.ArrayList;

public class ParkingLots {

    private final ArrayList<ParkingLot> parkingLots = new ArrayList<ParkingLot>();
    private int totalCars = 0;

    public int totalSpace() {
        int count = 0;
        for (ParkingLot parkingLot : parkingLots) {
            count += parkingLot.totalSpace();
        }
        return count;
    }

    public int totalCars() {
        return totalCars;
    }

    public boolean isFull() {
        for (ParkingLot parkingLot : parkingLots) {
            if (!parkingLot.isFull()) {
                return false;
            }
        }
        return true;
    }

    public Token parkCar(Car car) throws ParkingLotFullException {
        if(isFull()){
            throw new ParkingLotFullException();
        }
        int parkingLotNumber;
        int parkingPosition = 0;
        for (parkingLotNumber = 0; parkingLotNumber < parkingLots.size(); parkingLotNumber++) {
            parkingPosition = parkingLots.get(parkingLotNumber).park(car);
            if (parkingPosition != -1) break;
        }
        totalCars++;
        return new Token(parkingLotNumber, parkingPosition);
    }

    public boolean add(ParkingLot parkingLot) {
        return parkingLots.add(parkingLot);
    }

    public Car findCar(Token token) {
        Car car = null;
        for (int key : token.keySet()) {
            car = parkingLots.get(key).unparkCar(token.get(key));
        }
        return car;
    }
}
