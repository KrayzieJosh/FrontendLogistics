package za.ac.cput.model;

public class DeliveryOrder {


    private String deliveryOrderId;


    private String deliveryAddress;


    private String deliveryDate;


    public DeliveryOrder(String deliveryOrderId, String deliveryAddress, String deliveryDate) {
        this.deliveryOrderId=deliveryOrderId;
        this.deliveryAddress=deliveryAddress;
        this.deliveryDate=deliveryDate;
    }

    public String getDeliveryOrderId() {
        return deliveryOrderId;
    }



    public String getDeliveryAddress() {
        return deliveryAddress;
    }

    public String getDeliveryDate() {
        return deliveryDate;
    }




    @Override
    public String toString() {
        return "DeliveryOrderBuilder{" +
                "deliveryOrderId='" + deliveryOrderId + '\'' +
                ", deliveryAddress='" + deliveryAddress + '\'' +
                ", deliveryDate='" + deliveryDate + '\'' +
                '}';
    }





}
