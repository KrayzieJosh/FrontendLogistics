package za.ac.cput.model;

public class MaterialQuote {
    private String materialQuoteId;

    private String materialName;

    private double materialPrice;

    private String materialQuantity;

    private double materialWeight;

    public MaterialQuote(){

    }

    public MaterialQuote(String materialQuoteId, String materialName, double materialPrice, String materialQuantity, double materialWeight) {
        this.materialQuoteId = materialQuoteId;
        this.materialName = materialName;
        this.materialPrice = materialPrice;
        this.materialQuantity = materialQuantity;
        this.materialWeight = materialWeight;
    }

    public String getMaterialQuoteId() {
        return materialQuoteId;
    }

    public String getMaterialName() {
        return materialName;
    }

    public double getMaterialPrice() {
        return materialPrice;
    }

    public String getMaterialQuantity() {
        return materialQuantity;
    }

    public double getMaterialWeight() {
        return materialWeight;
    }

    @Override
    public String toString() {
        return "MaterialQuote{" +
                "materialQuoteId='" + materialQuoteId + '\'' +
                ", materialName='" + materialName + '\'' +
                ", materialPrice=" + materialPrice +
                ", materialQuantity='" + materialQuantity + '\'' +
                ", materialWeight=" + materialWeight +
                '}';
    }
}

