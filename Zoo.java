import java.util.ArrayList;
import java.util.List;

public class Zoo {
    private List<String> feedbackList;
    private List<Animal> animals;
    private List<Attraction> attractions;


    public Zoo() {
        this.feedbackList = new ArrayList<>();
        this.animals = new ArrayList<>();
        attractions = new ArrayList<>();

    }


    public void addAttraction(int attractionID, String name, String description, int ticketPrice) {
        int id = attractions.size() + 1;
        Attraction attraction = new Attraction(attractionID, name, description, ticketPrice);
        attractions.add(attraction);
        System.out.println("Attraction added successfully.");
    }

    public void viewAttractions() {
        if (attractions.isEmpty()) {
            System.out.println("No attractions found.");
        } else {
            System.out.println("Attractions:");
            for (Attraction attraction : attractions) {
                System.out.println(attraction.getID() + ". " + attraction.getName() + " - " + attraction.getDescription());
            }
        }
    }

    public void addAnimal(Animal animal) {
        animals.add(animal);
    }

    public boolean removeAnimal(String animalName) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                animals.remove(animal);
                return true;
            }
        }
        return false;
    }

    public Animal getAnimalByName(String animalName) {
        for (Animal animal : animals) {
            if (animal.getName().equalsIgnoreCase(animalName)) {
                return animal;
            }
        }
        return null;
    }

    public List<String> getfeedbackList() {
        // Return the list of animals in the zoo
        return feedbackList;
    }
    public List<Animal> getAnimals() {
        // Return the list of animals in the zoo
        return animals;
    }

    public List<Attraction> getAttractions() {
        // Return the list of attractions
        return attractions;
    }


    public List<Animal> getAllAnimals() {
        return animals;
    }


}
