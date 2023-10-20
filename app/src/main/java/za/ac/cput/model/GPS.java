package za.ac.cput.model;

public class GPS {
    private String tripId;
    private String startLocationId;
    private String storeLocationId;

    public GPS() {
    }

    public GPS(String tripId, String startLocationId, String storeLocationId) {
        this.tripId = tripId;
        this.startLocationId = startLocationId;
        this.storeLocationId = storeLocationId;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public String getStartLocationId() {
        return startLocationId;
    }

    public void setStartLocationId(String startLocationId) {
        this.startLocationId = startLocationId;
    }

    public String getStoreLocationId() {
        return storeLocationId;
    }

    public void setStoreLocationId(String storeLocationId) {
        this.storeLocationId = storeLocationId;
    }

    @Override
    public String toString() {
        return "GPS{" +
                "tripId='" + tripId + '\'' +
                ", startLocationId='" + startLocationId + '\'' +
                ", storeLocationId='" + storeLocationId + '\'' +
                '}';
    }
}
