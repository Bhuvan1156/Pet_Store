package demo;

public class Pet {
	private int Pet_ID;
    private String petCategory;
    private String petType;
    private String color;
    private int age;
    private double price;
    private boolean isVaccinated;
    private String foodHabits;

    public void setPetID(int i) {
        this.Pet_ID = i;
    }
    
    public void setPetCategory(String petCategory) {
        this.petCategory = petCategory;
    }

    public void setPetType(String petType) {
        this.petType = petType;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setVaccinated(boolean isVaccinated) {
        this.isVaccinated = isVaccinated;
    }

    public void setFoodHabits(String foodHabits) {
        this.foodHabits = foodHabits;
    }

    public String getPetCategory() {
        return petCategory;
    }

    public String getPetType() {
        return petType;
    }

    public String getColor() {
        return color;
    }

    public int getAge() {
        return age;
    }

    public double getPrice() {
        return price;
    }

    public boolean isVaccinated() {
        return isVaccinated;
    }

    public String getFoodHabits() {
        return foodHabits;
    }
    public int getPet_ID() {
        return Pet_ID;
    }

    @Override
    
    public String toString() {
        return "Pet_ID: " +Pet_ID+ ", Category:" + petCategory + ", Type:" + petType + ", Color:" + color + ", Age:" + age
                + ", Price:" + price + ", Is Vaccinated:" + (isVaccinated ? "Yes" : "No") + ", Food Habits:"
                + foodHabits + "\n";
    }
}
