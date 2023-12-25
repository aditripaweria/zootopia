import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Visitor {
    private String name;
    private int age;
    private String phoneNumber;
    private int balance;
    private String email;
    private String password;
    private List<Attraction> visitedAttractions;
    private Map<String, Integer> discountCodes;

    private Scanner scanner;
    private Zoo zoo;
    private Map<Integer, Attraction> attractions;
    private List<Purchase> purchases;


    public Visitor(String name, int age, String phoneNumber, int balance, String email, String password, List<Attraction> attractions,Zoo zoo) {
        this.name = name;
        this.age = age;
        this.phoneNumber = phoneNumber;
        this.balance = balance;
        this.email = email;
        this.password = password;
        this.visitedAttractions = new ArrayList<>();
        this.discountCodes = new HashMap<>();
        this.visitedAttractions = new ArrayList<>();
        this.scanner = new Scanner(System.in);
        this.zoo = zoo;
        this.attractions = new HashMap<>();
        purchases = new ArrayList<>();

    }



    // Getter methods for accessing visitor information
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public int getBalance() {
        return balance;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    // Method to register a visitor
    public static Visitor registerVisitor(String name, int age, String phoneNumber, int balance, String email, String password, Zoo zoo,List<Attraction> attractions) {
        return new Visitor(name, age, phoneNumber, balance, email, password, attractions, zoo);
    }




    public static Visitor loginVisitor(Scanner scanner, List<Visitor> registeredVisitors) {
        System.out.print("Enter your email: ");
        String email = scanner.next();
        System.out.print("Enter your password: ");
        String password = scanner.next();

        for (Visitor visitor : registeredVisitors) {
            if (email.equals(visitor.getEmail()) && password.equals(visitor.getPassword())) {
                System.out.println("Login Successful.");
                return visitor; // Return the logged-in visitor
            }
        }

        System.out.println("Login failed. Invalid email or password.");
        return null;
    }



    public void viewAttractions(Zoo zoo) {
        System.out.println("View Attractions:");
        for (Attraction att : zoo .getAttractions()) {
            System.out.println("Attraction Name: " + att.getName());
            System.out.println("Attraction ID: " + att.getID());
            System.out.println("Attraction Description: " + att.getDescription());
            System.out.println("Ticket Price: $" + att.getTicketPrice());
            System.out.println();
        }
    }






    public void viewAnimals() {
        System.out.println("View Animals:");
        for (Animal animal : zoo.getAnimals()) {
            System.out.println("Animal Name: " + animal.getName());
            System.out.println("Animal Noise: " + animal.getNoise());
            System.out.println("Animal Description: " + animal.getDescription());
            System.out.println();
        }
    }




    public void buyMembership(int choice, Map<String, Double> discounts){
        double membershipPrice = 0;

        if (choice == 1) {
            membershipPrice = 20.0;
        } else if (choice == 2) {
            membershipPrice = 50.0;
        } else {
            System.out.println("Invalid membership choice.");
            return;
        }

        if (discounts.containsKey("membership")) {
            double discountPercentage = discounts.get("membership");
            membershipPrice -= (membershipPrice * discountPercentage / 100.0);
        }

        if (balance >= membershipPrice) {
            balance -= membershipPrice;
            System.out.println("Membership purchased successfully. Your balance is now ₹" + balance + ".");
        } else {
            System.out.println("Insufficient balance to buy the membership.");
        }
    }




    private void purchaseBasicMembership() {
        int membershipPrice = 20;
        balance -= membershipPrice;
        System.out.println("Basic Membership purchased successfully. Your balance is now ₹" + balance + ".");
    }

    private void purchasePremiumMembership(int choice) {
        int membershipPrice = 50;
        balance -= membershipPrice;
        System.out.println("Premium Membership purchased successfully. Your balance is now ₹" + balance + ".");
    }


    public void buyTickets(List<Attraction> attractions) {
        System.out.println("Buy Tickets:");

        // Create a map to keep track of open attractions
        Map<Integer, Attraction> openAttractions = new HashMap<>();

        for (int i = 0; i < attractions.size(); i++) {
            Attraction attraction = attractions.get(i);

            if (attraction.isOpen()) {
                System.out.println((i + 1) + ". " + attraction.getName() + " (₹" + attraction.getTicketPrice() + ")");
                openAttractions.put(i + 1, attraction);
            }
        }

        if (openAttractions.isEmpty()) {
            System.out.println("No attractions are currently open.");
            return;
        }

        System.out.print("Enter the attraction number to buy a ticket or 0 to exit: ");
        int choice = scanner.nextInt();

        if (choice > 0 && choice <= openAttractions.size()) {
            Attraction selectedAttraction = openAttractions.get(choice);
            double ticketPrice = selectedAttraction.getTicketPrice();

            if (balance >= ticketPrice) {
                balance -= ticketPrice;
                System.out.println("Ticket purchased for " + selectedAttraction.getName() + ". Your balance is now ₹" + balance + ".");
            } else {
                System.out.println("Insufficient balance to buy a ticket for " + selectedAttraction.getName());
            }
        } else if (choice == 0) {
            System.out.println("Exiting Buy Tickets.");
        } else {
            System.out.println("Invalid choice.");
        }
    }





    public void viewDiscounts(Map<String, Double> discountCodes) {
        System.out.println("View Discounts:");
        for (Map.Entry<String, Double> entry : discountCodes.entrySet()) {
            System.out.println(entry.getKey() + " (" + entry.getValue() + "% discount)");
        }
    }


    public void applyDiscount(String discountCode, Attraction attraction) {
        if (discountCodes.containsKey(discountCode)) {
            int discountPercentage = discountCodes.get(discountCode);
            double ticketPrice = attraction.getBasicMemberPrice(); // Get the ticket price
            double discountAmount = (ticketPrice * discountPercentage) / 100;
            double finalPrice = ticketPrice - discountAmount;

            System.out.println("Discount applied: " + discountCode);
            System.out.println("Original Ticket Price: $" + ticketPrice);
            System.out.println("Final Price after Discount: $" + finalPrice);
        } else {
            System.out.println("Invalid discount code.");
        }
    }



    public void applySpecialDeals(List<Attraction> selectedAttractions, Map<Integer, Double> specialDeals) {
        int numSelectedAttractions = selectedAttractions.size();
        for (Map.Entry<Integer, Double> deal : specialDeals.entrySet()) {
            if (numSelectedAttractions >= deal.getKey()) {
                double discountPercentage = deal.getValue();
                double totalTicketPrice = selectedAttractions.stream().mapToDouble(Attraction::getBasicMemberPrice).sum();
                double discountAmount = (discountPercentage / 100) * totalTicketPrice;
                balance -= discountAmount;
                System.out.println("Special deal applied: " + discountPercentage + "% discount. Your balance is now ₹" + balance + ".");
                break;
            }
        }
    }

    public void visitAttractions(Admin admin) {
        System.out.println("Visiting Attractions:");

        for (Attraction attraction : admin.getAttractions().values()) {
            System.out.println((attraction.getID()) + ". " + attraction.getName());
        }

        System.out.print("Enter the attraction ID to visit or 0 to exit: ");
        int choice = scanner.nextInt();

        if (choice > 0 && admin.getAttractions().containsKey(choice)) {
            Attraction selectedAttraction = admin.getAttractions().get(choice);

            if (selectedAttraction.getAvailableVisits() > 0) {
                System.out.println("You are visiting " + selectedAttraction.getName() + ":");
                System.out.println("Attraction Description: " + selectedAttraction.getDescription());
                System.out.println("Remaining Visits: " + selectedAttraction.getAvailableVisits());

                // Reduce the available visits for the attraction
                selectedAttraction.reduceAvailableVisits();
            } else {
                System.out.println("Sorry, no more visits available for " + selectedAttraction.getName());
            }
        } else if (choice == 0) {
            System.out.println("Exiting Visit Attractions.");
        } else {
            System.out.println("Invalid choice.");
        }
    }




    public void provideFeedback(String feedback) {
        System.out.println("Feedback received: " + feedback);
        zoo.getfeedbackList().add(feedback);
        System.out.println("Thank you for your feedback!");
    }

    public int getTotalSpent() {

        return balance;
    }

    public List<Attraction> getVisitedAttractions() {
        return visitedAttractions;
    }


    public void addPurchase(String description, double amount) {
        Purchase purchase = new Purchase(description, amount);
        purchases.add(purchase);
    }

    // Get the total revenue spent by the visitor
    public double getTotalRevenue() {
        double totalRevenue = 0.0;
        for (Purchase purchase : purchases) {
            totalRevenue += purchase.getAmount();
        }
        return totalRevenue;
    }

}
