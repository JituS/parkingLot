package observers;

public interface ParkingLotObserver {
    void observe(int totalCars, int totalSpace);
    int parkedCars();
    boolean isEightyPercentFull();
}
