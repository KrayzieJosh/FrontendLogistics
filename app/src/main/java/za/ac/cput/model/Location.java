package za.ac.cput.model;

public class Location {
    private String locationId;
    private String name;
    private int streetNumber;
    private String streetName;
    private String townOrCity;
    private int areaCode;

    public Location() {
    }

    public Location(String locationId, String name, int streetNumber, String streetName, String townOrCity, int areaCode) {
        this.locationId = locationId;
        this.name = name;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.townOrCity = townOrCity;
        this.areaCode = areaCode;
    }

    public String getLocationId() {
        return locationId;
    }

    public void setLocationId(String locationId) {
        this.locationId = locationId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getStreetNumber() {
        return streetNumber;
    }

    public void setStreetNumber(int streetNumber) {
        this.streetNumber = streetNumber;
    }

    public String getStreetName() {
        return streetName;
    }

    public void setStreetName(String streetName) {
        this.streetName = streetName;
    }

    public String getTownOrCity() {
        return townOrCity;
    }

    public void setTownOrCity(String townOrCity) {
        this.townOrCity = townOrCity;
    }

    public int getAreaCode() {
        return areaCode;
    }

    public void setAreaCode(int areaCode) {
        this.areaCode = areaCode;
    }

    @Override
    public String toString() {
        return "Location{" +
                "locationId='" + locationId + '\'' +
                ", name='" + name + '\'' +
                ", streetNumber=" + streetNumber +
                ", streetName='" + streetName + '\'' +
                ", townOrCity='" + townOrCity + '\'' +
                ", areaCode=" + areaCode +
                '}';
    }
}
