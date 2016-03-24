package observers;

public class CivicBody implements ParkingLotObserver {
    private int totalCars;
    private int totalSpace;

    @Override
    public void observe(int totalCars, int totalSpace) {
        this.totalCars = totalCars;
        this.totalSpace = totalSpace;
    }

    @Override
    public int parkedCars() {
        return 0;
    }

    @Override
    public boolean isEightyPercentFull() {
        return totalCars >= (int) Math.floor(totalSpace * 0.8);
    }
}
