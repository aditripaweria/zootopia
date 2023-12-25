import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Admin {
    private Map<Integer, Attraction> attractions;
    private Map<String, Double> visitorDiscounts;
    private List<String> feedbackList;
    private Scanner scanner;
    private Map<String, Double> adminDiscounts;
    private Map<String, Double> specialDeals = new HashMap<>();
    private int lastAttractionID = 0;
    private Map<String, String> discountCodes = new HashMap<>();
    private Map<Integer, Animal> animals = new HashMap<>();
    private int ticketRevenue = 0;
    private int membershipRevenue = 0;


    public void scheduleEvent(Attraction attraction, Scanner scanner) {
        if (attraction != null) {
            System.out.println("Do you want to open or close the event for " + attraction.getName() + "?");
            System.out.println("1. Open Event");
            System.out.println("2. Close Event");
            int choice = scanner.nextInt();

            if (choice == 1) {
                attraction.setIsOpen(true); // Open the event
                System.out.println("Event opened for " + attraction.getName());
            } else if (choice == 2) {
                attraction.setIsOpen(false); // Close the event
                System.out.println("Event closed for " + attraction.getName());
            } else {
                System.out.println("Invalid choice. Event status remains unchanged.");
            }
        } else {
            System.out.println("Attraction not found. Unable to schedule the event.");
        }
    }

    public Admin(Map<Integer, Attraction> attractions, Map<String, Double> visitorDiscounts, Map<String, Double> specialDeals, List<String> feedbackList) {
        this.attractions = attractions;
        this.visitorDiscounts = visitorDiscounts;
        this.specialDeals = specialDeals;  // Update the type here
        this.feedbackList = feedbackList;
        adminDiscounts = new HashMap<>();
    }

    public void setDiscount(String category, double discountPercentage, String discountCode) {
        if (category.equalsIgnoreCase("minor") || category.equalsIgnoreCase("senior")) {
            adminDiscounts.put(category, discountPercentage);
            discountCodes.put(category, discountCode); // Store the discount code
            System.out.println("Discount set successfully for category: " + category);
        } else {
            System.out.println("Category not found or not eligible for discounts.");
        }
    }

    public void recordTicketSale(int amount) {
        ticketRevenue += amount;
    }

    public void recordMembershipSale(int amount) {
        membershipRevenue += amount;
    }

    public int getTotalRevenue() {
        return ticketRevenue + membershipRevenue;
    }


    public void removeDiscount(String category) {
        if (adminDiscounts.containsKey(category)) {
            adminDiscounts.remove(category);
            System.out.println("Discount for category: " + category + " removed successfully.");
        } else {
            System.out.println("Category not found. Unable to remove the discount.");
        }
    }

    public void modifyDiscount(String category, double newDiscountPercentage, String discountCode) {
        if (adminDiscounts.containsKey(category)) {
            adminDiscounts.put(category, newDiscountPercentage);
            discountCodes.put(category, discountCode); // Update the discount code
            System.out.println("Discount for category: " + category + " modified successfully.");
        } else {
            System.out.println("Category not found. Unable to modify the discount.");
        }
    }




    // Add an attraction
    public void addAttraction(String name, String description, int ticketPrice,Zoo zoo) {
        int attractionID = generateAttractionID(); // You can define this method to generate a unique ID.

        // Create a new Attraction object with the provided data.
        Attraction attraction = new Attraction(attractionID, name, description, ticketPrice);

        // Add the attraction to the attractions map.
        attractions.put(attractionID, attraction);
        zoo.getAttractions().add(attraction);

        System.out.println("Attraction added: " + name);
    }


    // Modify an attraction
    public void modifyAttraction(int attractionID, Scanner scanner) {
        Attraction attraction = attractions.get(attractionID);
        if (attraction != null) {
            System.out.print("Enter Attraction Name: ");
            String name = scanner.nextLine();
            System.out.print("Enter Attraction Description: ");
            String description = scanner.nextLine();
            System.out.print("Enter Attraction Ticket Price: ");
            int ticketPrice = scanner.nextInt();
            attraction.updateAttractionDetails(name, description, ticketPrice);
            System.out.println("Attraction details updated for attraction with ID " + attractionID);
        } else {
            System.out.println("Attraction with ID " + attractionID + " not found.");
        }
    }


    // Remove an attraction
    public void removeAttraction(int attractionID) {
        Attraction attraction = attractions.get(attractionID);
        if (attraction != null) {
            attractions.remove(attractionID);
            System.out.println("Attraction with ID " + attractionID + " removed successfully.");
        } else {
            System.out.println("Attraction with ID " + attractionID + " not found.");
        }
    }

    // Add an animal
    private static void addAnimal(Admin admin, int id, Scanner scanner) {
        System.out.print("Enter Animal Type (Mammal/Reptile/Amphibian): ");
        String animalType = scanner.nextLine().trim();

        if (!animalType.equalsIgnoreCase("Mammal") &&
                !animalType.equalsIgnoreCase("Reptile") &&
                !animalType.equalsIgnoreCase("Amphibian")) {
            System.out.println("Invalid animal type. Animal not added.");
            return;
        }

        System.out.print("Enter Animal Name: ");
        String animalName = scanner.nextLine();

        System.out.print("Enter Animal Description: ");
        String animalDescription = scanner.nextLine();

        System.out.print("Enter Animal Sound: ");
        String animalSound = scanner.nextLine();

        Animal newAnimal;

        if (animalType.equalsIgnoreCase("Mammal")) {
            newAnimal = new Mammal(animalName, animalDescription, animalSound);
        } else if (animalType.equalsIgnoreCase("Reptile")) {
            newAnimal = new Reptile(animalName, animalDescription, animalSound);
        } else if (animalType.equalsIgnoreCase("Amphibian")) {
            newAnimal = new Amphibian(animalName, animalDescription, animalSound);
        } else {
            System.out.println("Invalid animal type. Animal not added.");
            return;
        }

        admin.addAnimal(id, newAnimal);
        System.out.println("Animal added to the zoo.");
    }


    public void addAnimal(int id, Animal newAnimal) {
        newAnimal.setId(id); // Set the ID for the newAnimal
        animals.put(id, newAnimal); // Add the newAnimal to the animals map
        System.out.println("Animal added successfully!");
    }


    public void modifyAnimal(int attractionID, String animalName, String updatedType, String updatedDescription) {
        Attraction attraction = attractions.get(attractionID);
        if (attraction != null) {
            Animal animal = attraction.getAnimal(animalName);
            if (animal != null) {
                animal.setDescription(updatedType);
                animal.setnoise(updatedDescription);
                System.out.println("Animal details updated successfully.");
            } else {
                System.out.println("Animal with name " + animalName + " not found in attraction with ID " + attractionID);
            }
        } else {
            System.out.println("Attraction with ID " + attractionID + " not found.");
        }
    }




    // Remove an animal
    public void removeAnimal(int attractionID, String animalName) {
        Attraction attraction = attractions.get(attractionID);
        if (attraction != null) {
            attraction.removeAnimal(animalName);
            System.out.println("Animal " + animalName + " removed from attraction with ID " + attractionID);
        } else {
            System.out.println("Attraction with ID " + attractionID + " not found.");
        }
    }

    public void viewAttractions() {
        System.out.println("View Attractions:");
        for (Attraction att : attractions.values()) {
            System.out.println(att.toString());
        }
    }

    public void addSpecialDeal(String category, double discountPercentage) {
        specialDeals.put(category, discountPercentage);
        System.out.println("Special Deal added for category: " + category);
    }

    public void modifySpecialDeal(String category, double discountPercentage) {
        if (specialDeals.containsKey(category)) {
            specialDeals.put(category, discountPercentage);
            System.out.println("Special Deal for category " + category + " modified to " + discountPercentage + "%");
        } else {
            System.out.println("Special Deal category not found.");
        }
    }

    public void removeSpecialDeal(String category) {
        if (specialDeals.containsKey(category)) {
            specialDeals.remove(category);
            System.out.println("Special Deal for category " + category + " removed.");
        } else {
            System.out.println("Special Deal category not found.");
        }
    }

    public void viewSpecialDeals() {
        System.out.println("Special Deals:");
        for (Map.Entry<String, Double> entry : specialDeals.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue() + "% discount");
        }
    }

    public void viewFeedback(Zoo zoo) {
        System.out.println("View Feedback:");
        for (String feedback : zoo.getfeedbackList()) {
            System.out.println(feedback);
        }
    }






    public void viewVisitorStats(List<Visitor> registeredVisitors, List<Attraction> attractions) {
        int totalVisitors = registeredVisitors.size();
        int totalRevenue = 0;
        Map<Attraction, Integer> attractionVisitCounts = new HashMap<>();

        for (Visitor visitor : registeredVisitors) {
            totalRevenue += visitor.getTotalRevenue(); // Updated to get total revenue
            List<Attraction> visitedAttractions = visitor.getVisitedAttractions();
            for (Attraction attraction : visitedAttractions) {
                attractionVisitCounts.put(attraction, attractionVisitCounts.getOrDefault(attraction, 0) + 1);
            }
        }

        Attraction mostPopularAttraction = findMostPopularAttraction(attractionVisitCounts);

        System.out.println("Visitor Statistics:");
        System.out.println("Total Visitors: " + totalVisitors);
        System.out.println("Total Revenue: $" + totalRevenue);
        if (mostPopularAttraction != null) {
            System.out.println("Most Popular Attraction: " + mostPopularAttraction.getName());
        }
    }



    private Attraction findMostPopularAttraction(Map<Attraction, Integer> attractionVisitCounts) {
        int maxVisits = 0;
        Attraction mostPopularAttraction = null;

        for (Map.Entry<Attraction, Integer> entry : attractionVisitCounts.entrySet()) {
            Attraction attraction = entry.getKey();
            int visits = entry.getValue();

            if (visits > maxVisits) {
                maxVisits = visits;
                mostPopularAttraction = attraction;
            }
        }

        return mostPopularAttraction;
    }



    private int generateUniqueID() {
        return attractions.size() + 1; // Simple ID generation based on the number of attractions
    }

    private boolean isValidAnimalType(String type) {
        // Implement your validation logic here
        return true; // Placeholder logic
    }

    public Map<Integer, Attraction> getAttractions() {
        return attractions;
    }

    public Map<String, Double> getSpecialDeals() {
        return specialDeals;
    }

    public Map<String, Double> getDiscounts() {
        return visitorDiscounts;
    }

    private int generateAttractionID() {
        return ++lastAttractionID;
    }

}

