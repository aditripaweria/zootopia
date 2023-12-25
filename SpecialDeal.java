public class SpecialDeal {
    private int attractionID;
    private double discountPercentage;

    public SpecialDeal(int attractionID, double discountPercentage) {
        this.attractionID = attractionID;
        this.discountPercentage = discountPercentage;
    }

    public int getAttractionID() {
        return attractionID;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }
}
