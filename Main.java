import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        Admin admin = new Admin(new HashMap<>(), new HashMap<>(), new HashMap<>(), new ArrayList<>());
        List<Visitor> registeredVisitors = new ArrayList<>();
        List<Attraction> attractions = new ArrayList<>();
        Zoo zoo = new Zoo();


        int e = 1;

        admin.addAnimal(e++, new Mammal("Lion", "The king of the jungle", "Roar"));
        admin.addAnimal(e++, new Mammal("Elephant", "The largest land mammal", "Trumpet"));
        admin.addAnimal(e++, new Reptile("Crocodile", "A large aquatic reptile", "Grunt"));
        admin.addAnimal(e++, new Reptile("Turtle", "A slow-moving reptile", "Hiss"));
        admin.addAnimal(e++, new Amphibian("Frog", "An amphibious creature", "Croak"));
        admin.addAnimal(e++, new Amphibian("Salamander", "A small amphibian", "Ribbit"));

        ArrayList<Discount> discounts = new ArrayList<Discount>();
        Discount d1 = new Discount("Minor", 10, "MINOR10");
        Discount d2 = new Discount("Senior", 20, "SENIOR20");
        discounts.add(d1); discounts.add(d2);

        ArrayList<SpecialDeal> sdeals = new ArrayList<SpecialDeal>();
        SpecialDeal deal1 = new SpecialDeal(2, 15);
        SpecialDeal deal2 = new SpecialDeal(3, 30);



        while(true){
            System.out.println("Welcome to ZOOtopia!");
            System.out.println("1. Enter as Admin");
            System.out.println("2. Enter as a Visitor");
            System.out.println("3. View Special Deals");

            int choice = scanner.nextInt();
            scanner.nextLine();

            if (choice == 1) {
                System.out.print("Enter Admin Username: ");
                String username = scanner.next();
                scanner.nextLine();
                System.out.print("Enter Admin Password: ");
                String password = scanner.next();
                scanner.nextLine();

                if (username.equals("admin") && password.equals("admin123")) {
                    System.out.println("Logged in as Admin.");

                    while (true) {
                        System.out.println("Admin Menu:");
                        System.out.println("1. Manage Attractions");
                        System.out.println("2. Manage Animals");
                        System.out.println("3. Schedule Events");
                        System.out.println("4. Set Discounts");
                        System.out.println("5. Set Special Deal");
                        System.out.println("6. View Visitor Stats");
                        System.out.println("7. View Feedback");
                        System.out.println("8. Exit");

                        int adminChoice = scanner.nextInt();
                        scanner.nextLine(); // Consume newline

                        if (adminChoice == 1) {
                            manageAttractions(admin,zoo, scanner);
                        } else if (adminChoice == 2) {
                            manageAnimals(zoo, scanner);

                        } else if (adminChoice == 3) {
                            System.out.println("Schedule Event");
                            System.out.println("1.change status");
                            System.out.println("2.change ticket price");
                            int d=scanner.nextInt();
                            if (d==1) {

                                System.out.print("Enter Attraction ID to schedule an event: ");
                                int attractionID = scanner.nextInt();
                                Attraction attraction = admin.getAttractions().get(attractionID);

                                if (attraction != null) {
                                    admin.scheduleEvent(attraction, scanner);
                                } else {
                                    System.out.println("Attraction not found. Unable to schedule the event.");
                                }
                            }
                            else if(d==2){
                                System.out.print("Enter Attraction ID to schedule an event: ");
                                int attractionID = scanner.nextInt();
                                System.out.print("Enter price of attraction: ");
                                int price = scanner.nextInt();
                                Attraction attraction = admin.getAttractions().get(attractionID);
                                attraction.setTicketPrice(price);
                            }
                        }


                        else if (adminChoice == 4) {
                            setDiscounts(admin, scanner);
                        } else if (adminChoice == 5) {
                            System.out.println("Special Deals Menu:");
                            System.out.println("1. Add Special Deal");
                            System.out.println("2. Modify Special Deal");
                            System.out.println("3. Remove Special Deal");
                            System.out.println("4. Exit");

                            int specialDealChoice = scanner.nextInt();
                            scanner.nextLine(); // Consume newline

                            if (specialDealChoice == 1) {
                                System.out.print("Enter Special Deal Category: ");
                                String category = scanner.next();
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter Special Deal Percentage (e.g., 10 for 10% discount): ");
                                double discountPercentage = scanner.nextDouble();
                                scanner.nextLine(); // Consume newline
                                admin.addSpecialDeal(category, discountPercentage);
                            } else if (specialDealChoice == 2) {
                                System.out.print("Enter Special Deal Category to Modify: ");
                                String category = scanner.next();
                                scanner.nextLine(); // Consume newline
                                System.out.print("Enter New Special Deal Percentage: ");
                                double discountPercentage = scanner.nextDouble();
                                scanner.nextLine(); // Consume newline
                                admin.modifySpecialDeal(category, discountPercentage);
                            } else if (specialDealChoice == 3) {
                                System.out.print("Enter Special Deal Category to Remove: ");
                                String category = scanner.next();
                                scanner.nextLine(); // Consume newline
                                admin.removeSpecialDeal(category);
                            } else if (specialDealChoice == 4) {
                                System.out.println("Exiting Special Deals Menu.");
                            } else {
                                System.out.println("Invalid choice");
                            }
                        }
                        else if (adminChoice == 6) {
                            admin.viewVisitorStats(registeredVisitors, attractions);;
                        } else if (adminChoice == 7) {
                            admin.viewFeedback(zoo);
                        } else
                        if (adminChoice == 8) {
                            System.out.println("Exiting Admin Menu.");
                            break;
                        } else {
                            System.out.println("Invalid choice");
                        }
                    }
                } else {
                    System.out.println("Invalid username or password. Access denied.");
                }
            }

            else if (choice == 2) {
                while(true){
                    System.out.println("1. Register");
                    System.out.println("2. Login");
                    System.out.println("3. Exit");

                    int visitorChoice = scanner.nextInt();
                    scanner.nextLine(); // Consume newline

                    if (visitorChoice == 1) {
                        System.out.println("Enter Visitor Name: ");
                        String name = scanner.next();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter Visitor Age: ");
                        int age = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter Visitor Phone Number: ");
                        String phoneNumber = scanner.next();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter Visitor Balance: ");
                        int balance = scanner.nextInt();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter Visitor Email: ");
                        String email = scanner.next();
                        scanner.nextLine(); // Consume newline
                        System.out.println("Enter Visitor Password: ");
                        String password = scanner.next();
                        scanner.nextLine(); // Consume newline
                        List<Attraction> attractionList = new ArrayList<>(admin.getAttractions().values());
                        Visitor visitor = Visitor.registerVisitor(name, age, phoneNumber, balance, email, password, zoo, attractionList);
                        registeredVisitors.add(visitor);
                    }
                    else if (visitorChoice == 2) {
                        Visitor visitor = Visitor.loginVisitor(scanner, registeredVisitors);
                        if (visitor != null) {
                            visitorMenu(visitor, admin,zoo, scanner);
                        }
                        else {
                            System.out.println("Invalid choice");
                        }
                    }else{
                        System.out.println("Exiting");
                        break;
                    }
                }
            }

            else if (choice == 3) {
                admin.viewSpecialDeals();
            }

            else{
                System.out.println("Invalid choice");
            }
        }


    }


    private static void manageAttractions(Admin admin,Zoo zoo, Scanner scanner) {
        while (true) {
            System.out.println("Manage Attractions:");
            System.out.println("1. Add Attraction");
            System.out.println("2. View Attractions");
            System.out.println("3. Modify Attraction");
            System.out.println("4. Remove Attraction");
            System.out.println("5. Exit");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter Attraction Name: ");
                String name = scanner.next();
                System.out.print("Enter Attraction Description: ");
                String description = scanner.next();
                System.out.print("Enter ticket price: ");
                int ticket = scanner.nextInt();
                admin.addAttraction(name, description, ticket, zoo);

            } else if (choice == 2) {
                admin.viewAttractions();
            } else if (choice == 3) {
                System.out.print("Enter Attraction ID to modify: ");
                int attractionID = scanner.nextInt();
                System.out.print("Enter new name: ");
                String newnoise = scanner.next();
                scanner.nextLine();
                System.out.print("Enter new description ");
                String newdes= scanner.next();
                scanner.nextLine();
                admin.modifyAttraction(attractionID, scanner);

            } else if (choice == 4) {
                System.out.print("Enter Attraction ID to Remove: ");
                int attractionID = scanner.nextInt();
                admin.removeAttraction(attractionID);
            } else if (choice == 5) {
                System.out.println("Exiting Manage Attractions.");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }

    private static void manageAnimals(Zoo zoo, Scanner scanner) {
        while (true) {
            System.out.println("Manage Animals:");
            System.out.println("1. Add Animal");
            System.out.println("2. Update Animal Details");
            System.out.println("3. Remove Animal");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();
            scanner.nextLine(); // Consume newline

            if (choice == 1) {
                System.out.print("Enter Animal Name: ");
                String animalName = scanner.nextLine();

                System.out.print("Enter Animal Type: ");
                String animalType = scanner.nextLine();

                System.out.print("Enter Animal Description: ");
                String animalDescription = scanner.nextLine();

                System.out.print("Enter Animal noise: ");
                String noise = scanner.nextLine();

                Animal animal = new Animal(animalName, animalType, animalDescription, noise);
                zoo.addAnimal(animal);
                System.out.println("Animal added to the zoo.");
            } else if (choice == 2) {
                System.out.print("Enter Animal Name: ");
                String animalName = scanner.nextLine();

                Animal existingAnimal = zoo.getAnimalByName(animalName);
                if (existingAnimal == null) {
                    System.out.println("Animal not found in the zoo.");
                } else {
                    System.out.print("Enter New Animal description: ");
                    String updatedType = scanner.nextLine();

                    System.out.print("Enter New Animal noise: ");
                    String updatedNoise = scanner.nextLine();

                    existingAnimal.setType(updatedType);
                    existingAnimal.setNoise(updatedNoise);

                    System.out.println("Animal details updated.");
                }
            } else if (choice == 3) {
                System.out.print("Enter Animal Name to Remove: ");
                String animalName = scanner.nextLine();
                boolean removed = zoo.removeAnimal(animalName);
                if (removed) {
                    System.out.println("Animal removed from the zoo.");
                } else {
                    System.out.println("Animal not found in the zoo.");
                }
            } else if (choice == 4) {
                System.out.println("Exiting Manage Animals.");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }


    private static void setDiscounts(Admin admin, Scanner scanner) {
        while (true) {
            System.out.println("Set Discounts:");
            System.out.println("1. Add Discount");
            System.out.println("2. Modify Discount");
            System.out.println("3. Remove Discount");
            System.out.println("4. Exit");

            int choice = scanner.nextInt();

            if (choice == 1) {
                System.out.print("Enter Discount Category (minor/senior): ");
                String category = scanner.next();
                System.out.print("Enter Discount Percentage (e.g., 20 for 20%): ");
                double discountPercentage = scanner.nextDouble();
                System.out.print("Enter Discount Code: "); // Prompt the user for the discount code
                String discountCode = scanner.next();
                admin.setDiscount(category, discountPercentage, discountCode);

            } else if (choice == 2) {
                System.out.print("Enter Discount Category to Modify: ");
                String category = scanner.next();
                System.out.print("Enter New Discount Percentage (e.g., 20 for 20%): ");
                double newDiscountPercentage = scanner.nextDouble();
                System.out.print("Enter New Discount Code: "); // Prompt the user for the new discount code
                String discountCode = scanner.next();
                admin.modifyDiscount(category, newDiscountPercentage, discountCode);

            } else if (choice == 3) {
                System.out.print("Enter Discount Category to Remove: ");
                String category = scanner.next();

                // Check if the category exists in the discounts map
                if (admin.getDiscounts().containsKey(category)) {
                    // Remove the discount category
                    admin.removeDiscount(category);
                    System.out.println("Discount for " + category + " removed.");
                } else {
                    System.out.println("Discount category not found. Cannot remove.");
                }
            } else if (choice == 4) {
                System.out.println("Exiting Set Discounts.");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }


    private static void visitorMenu(Visitor visitor, Admin admin, Zoo zoo, Scanner scanner) {
        while (true) {
            System.out.println("Visitor Menu:");
            System.out.println("1. Explore the Zoo");
            System.out.println("2. Buy Membership");
            System.out.println("3. Buy Tickets");
            System.out.println("4. View Discounts");
            System.out.println("5. View Special Deals");
            System.out.println("6. Visit Animals");
            System.out.println("7. Visit Attractions");
            System.out.println("8. Leave Feedback");
            System.out.println("9. Log Out");

            int visitorChoice = scanner.nextInt();

            if (visitorChoice == 1) {
                System.out.println("Explore the Zoo:");
                System.out.println("1. View Attractions");
                System.out.println("2. View Animals");
                System.out.println("3. Exit");
                int choice = scanner.nextInt();
                if (choice == 1) {
                    visitor.viewAttractions(zoo);
                } else if (choice == 2) {
                    visitor.viewAnimals();
                } else if (choice == 3) {
                    System.out.println("Exiting Explore the Zoo.");
                } else {
                    System.out.println("Invalid choice.");
                }
            } else if (visitorChoice == 2) {
                System.out.println("Buy Membership:");
                System.out.println("1. Basic Membership (₹20)");
                System.out.println("2. Premium Membership (₹50)");
                System.out.print("Enter your choice: ");
                int choice = scanner.nextInt();
                visitor.buyMembership(choice, admin.getDiscounts());
            } else if (visitorChoice == 3) {
                System.out.print("Enter the number of tickets: ");
                int numTickets = scanner.nextInt();
                visitor.buyTickets(new ArrayList<>(admin.getAttractions().values()));
            } else if (visitorChoice == 4) {
                visitor.viewDiscounts(admin.getDiscounts());
            } else if (visitorChoice == 5) {
                viewSpecialDeals(admin);
            } else if (visitorChoice == 6) {
                System.out.print("Enter the name of the animal you want to interact with: ");
                String animalName = scanner.next();
                for (Animal a : zoo.getAnimals()) {
                    if (a.getName().equals(animalName)) {
                        System.out.println("You are visiting " + a.getName() + " at the zoo:");
                        System.out.println("1. Feed the animal");
                        System.out.println("2. Read about the animal");
                        int choice = scanner.nextInt();

                        if (choice == 1) {
                            System.out.println("You are feeding the " + a.getName() + ". It makes the following noise: " + a.getNoise());
                        } else if (choice == 2) {
                            System.out.println("You are reading about " + a.getName() + ":");
                            System.out.println("Description: " + a.getDescription());
                        }
                    } else {
                        System.out.println("No animal of this type");
                    }
                }
            } else if (visitorChoice == 7) {
                visitor.visitAttractions(admin);
            } else if (visitorChoice == 8) {
                System.out.print("Enter feedback: ");
                scanner.nextLine(); // Consume the newline
                String feedback = scanner.nextLine(); // Use nextLine to read the entire line
                visitor.provideFeedback(feedback);
            }
            else if (visitorChoice == 9) {
                System.out.println("Logging out.");
                break;
            } else {
                System.out.println("Invalid choice");
            }
        }
    }


    private static void viewSpecialDeals(Admin admin) {

        admin.viewSpecialDeals();
    }

}
