package za.ac.cput.model;

public class Tracking {
    private String tripId;
    private int stops;
    private int detours;

    public Tracking() {
    }

    public Tracking(String tripId, int stops, int detours) {
        this.tripId = tripId;
        this.stops = stops;
        this.detours = detours;
    }

    public String getTripId() {
        return tripId;
    }

    public void setTripId(String tripId) {
        this.tripId = tripId;
    }

    public int getStops() {
        return stops;
    }

    public void setStops(int stops) {
        this.stops = stops;
    }

    public int getDetours() {
        return detours;
    }

    public void setDetours(int detours) {
        this.detours = detours;
    }

    @Override
    public String toString() {
        return "Tracking{" +
                "tripId='" + tripId + '\'' +
                ", stops=" + stops +
                ", detours=" + detours +
                '}';
    }
}
