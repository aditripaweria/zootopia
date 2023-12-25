import java.util.List;

import java.util.List;
import java.util.ArrayList; // Import ArrayList

public class Attraction {
    private static int lastUsedID = 0;
    private int attractionID;
    private String name;
    private String description;
    private double premiumMemberPrice;
    private double basicMemberPrice;
    private int currentVisitors;
    private double totalRevenue;
    private List<Animal> animals; // Initialize the List
    private int availableVisits;
    private int ticketedVisitors;
    private String openingTime;
    private String closingTime;
    private boolean isOpen;
    private double ticketPrice;



    public Attraction(int a, String name, String description, double ticketPrice) {
        this.attractionID = a;
        this.name = name;
        this.description = description;
        this.premiumMemberPrice = 0.0; // Initialize with a value
        this.basicMemberPrice = 0.0;   // Initialize with a value
        this.currentVisitors = 0;
        this.totalRevenue = 0.0;
        this.animals = new ArrayList<>(); // Initialize with an empty ArrayList
        this.isOpen = false;
        this.ticketPrice = ticketPrice;
    }


    public int getID() {
        return attractionID;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }
    @Override
    public String toString() {
        return "Attraction Name: " + getName() + "\n" +
                "Attraction Description: " + getDescription() + "\n" +
                "Ticket Price: $" + getTicketPrice() + "\n";
    }
    public double getPremiumMemberPrice() {
        return premiumMemberPrice;
    }

    public double getBasicMemberPrice() {
        return basicMemberPrice;
    }


    public int getCurrentVisitors() {
        return currentVisitors;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }

    public void incrementVisitors() {
        currentVisitors++;
    }

    public void increaseRevenue(double amount) {
        totalRevenue += amount;
    }

    private int generateUniqueID() {
        lastUsedID++;
        return lastUsedID;
    }

    public void updateAttractionDetails(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public void updateAttractionDetails(String name, String description, int ticketPrice) {
        this.name = name;
        this.description = description;
        this.ticketPrice = ticketPrice;
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public Animal getAnimal(String animalName) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                return animal;
            }
        }
        return null;
    }

    public void removeAnimal(String animalName) {
        Animal animalToRemove = null;
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                animalToRemove = animal;
                break;
            }
        }
        if (animalToRemove != null) {
            animals.remove(animalToRemove);
        }
    }

    public int getAvailableVisits() {
        return availableVisits;
    }

    public void reduceAvailableVisits() {
        if (availableVisits > 0) {
            availableVisits--;
        }
    }

    public String getOpeningTime() {
        return openingTime;
    }

    public void setOpeningTime(String openingTime) {
        this.openingTime = openingTime;
    }

    public String getClosingTime() {
        return closingTime;
    }

    public void setClosingTime(String closingTime) {
        this.closingTime = closingTime;
    }

    public double getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public void toggleStatus() {
        isOpen = !isOpen; // Toggles the status (open to closed, or closed to open)
        String status = isOpen ? "opened" : "closed";
        System.out.println(getName() + " is now " + status);
    }

    public void resetTicketCount() {
        ticketedVisitors = 0;
    }

    public boolean isOpen() {
        return isOpen;
    }

    public void setIsOpen(boolean isOpen) {
        this.isOpen = isOpen;
    }

}
