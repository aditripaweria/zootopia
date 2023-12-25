public class Purchase {
    private String description; // E.g., "Ticket" or "Membership"
    private double amount; // The purchase amount

    public Purchase(String description, double amount) {
        this.description = description;
        this.amount = amount;
    }

    public String getDescription() {
        return description;
    }

    public double getAmount() {
        return amount;
    }

}

