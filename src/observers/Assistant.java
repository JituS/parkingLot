package observers;

public class Assistant implements ParkingLotObserver {
    private int totalCars = 0;
    private int totalSpace;

//    public HashMap<String, Integer> display() {
//        int parkingAreaCount = 0;
//        HashMap<String, Integer> carsSummary = new HashMap<String, Integer>();
//        for (parkingPlace.ParkingLot parkingArea : parkingAreas) {
//            carsSummary.put("parkingPlace.ParkingLots No: "+parkingAreaCount, parkingArea.totalCars());
//        }
//        return carsSummary;
//    }

    @Override
    public void observe(int totalCars, int totalSpace) {
        this.totalCars = totalCars;
        this.totalSpace = totalSpace;
    }

    public int parkedCars() {
        return totalCars;
    }

    @Override
    public boolean isEightyPercentFull() {
        return false;
    }
}
