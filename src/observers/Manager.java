package observers;

public class Manager implements ParkingLotObserver {
    private int availableCars;
    private int totalSpace;

    public boolean isEightyPercentFull() {
        return availableCars >= (int) Math.floor(totalSpace * 0.8);
    }

    @Override
    public void observe(int totalCars, int totalSpace) {
        this.availableCars = totalCars;
        this.totalSpace = totalSpace;
    }

    @Override
    public int parkedCars() {
        return availableCars;
    }
}
