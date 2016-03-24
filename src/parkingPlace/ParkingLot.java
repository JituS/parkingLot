package parkingPlace;

import exception.ParkingLotFullException;

import java.util.ArrayList;

public class ParkingLot {
    private ArrayList<Car> parkedCars;
    private int limitOfCars;

    public ParkingLot(int limitOfCars) {
        this.limitOfCars = limitOfCars;
        parkedCars = new ArrayList<Car>();
    }

    public int park(Car car) throws ParkingLotFullException {
        if(parkedCars.size() >= limitOfCars){
            return -1;
        }
        int position = parkedCars.size();
        parkedCars.add(car);
        return position;
    }

    public boolean isFull() {
        return limitOfCars == parkedCars.size();
    }

    public Car unparkCar(int integer) {
        return parkedCars.remove(integer);
    }

    public int totalSpace() {
        return limitOfCars;
    }
}
