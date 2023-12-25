public class Animal {
    private String name;
    private String type;
    private String description;
    private String noise;
    private int id;

    public Animal(String name, String type, String description,String noise) {
        this.name = name;
        this.type = type;
        this.description = description;
        this.noise=noise;
    }

    public void updateDetails(String name, String type, String description) {
        this.name = name;
        this.type = type;
        this.description = description;
    }


    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getNoise() {
        return noise;
    }

    public void setNoise(String noise) {
        this.noise = noise;
    }

    public String getName() {
        return name;
    }

    public String getType() {
        return type;
    }
    public String getnoise() {
        return noise;
    }

    // Setter methods
    public void setName(String name) {
        this.name = name;
    }

    public void setnoise(String noise) {
        this.noise = noise;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public String getDescription() {
        return description;
    }

    public void makeNoise() {
        // Implement the logic for the animal making noise here
        System.out.println(name + " makes a noise.");
    }



}

