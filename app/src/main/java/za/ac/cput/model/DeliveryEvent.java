package za.ac.cput.model;

import java.time.LocalDateTime;

public class DeliveryEvent {
    private String deliveryEventId;
    private String deliveryName;
    private LocalDateTime deliveryDate;
    private int streetNumber;
    private String streetName;
    private String townOrCity;
    private int areaCode;

    public DeliveryEvent() {
    }

    public DeliveryEvent(String deliveryEventId, String deliveryName, LocalDateTime deliveryDate,
                         int streetNumber, String streetName, String townOrCity, int areaCode) {
        this.deliveryEventId = deliveryEventId;
        this.deliveryName = deliveryName;
        this.deliveryDate = deliveryDate;
        this.streetNumber = streetNumber;
        this.streetName = streetName;
        this.townOrCity = townOrCity;
        this.areaCode = areaCode;
    }

    public String getDeliveryEventId() {
        return deliveryEventId;
    }

    public void setDeliveryEventId(String deliveryEventId) {
        this.deliveryEventId = deliveryEventId;
    }

    public String getDeliveryName() {
        return deliveryName;
    }

    public void setDeliveryName(String deliveryName) {
        this.deliveryName = deliveryName;
    }

    public LocalDateTime getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(LocalDateTime deliveryDate) {
        this.deliveryDate = deliveryDate;
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
        return
                "\ndeliveryEventId='" + deliveryEventId + '\'' +
                        ", \ndeliveryName='" + deliveryName + '\'' +
                        ", \ndeliveryDate=" + deliveryDate +
                        ", \nstreetNumber=" + streetNumber +
                        ", \nstreetName='" + streetName + '\'' +
                        ", \ntownOrCity='" + townOrCity + '\'' +
                        ", \nareaCode=" + areaCode
                ;
    }
}