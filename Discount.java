public class Discount {
    private String category;
    private double discountPercentage;
    private String discountCode;

    public Discount(String category, double discountPercentage, String discountCode) {
        this.category = category;
        this.discountPercentage = discountPercentage;
        this.discountCode = discountCode;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public double getDiscountPercentage() {
        return discountPercentage;
    }

    public void setDiscountPercentage(double discountPercentage) {
        this.discountPercentage = discountPercentage;
    }

    public String getDiscountCode() {
        return discountCode;
    }

    public void setDiscountCode(String discountCode) {
        this.discountCode = discountCode;
    }
}
