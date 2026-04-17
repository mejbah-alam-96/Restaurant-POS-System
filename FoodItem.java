// FoodItem.java
public class FoodItem extends MenuItem {
    private String cuisineType; // e.g., "Italian", "Bangladeshi", "Chinese"

    public FoodItem(int id, String name, double price, String description, String category, String cuisineType) {
        super(id, name, price, description, category);
        this.cuisineType = cuisineType;
    }

    public String getCuisineType() {
        return cuisineType;
    }

    @Override
    public String getItemType() {
        return "FOOD";
    }

    @Override
    public double calculatePrice(int qty) {
        return super.calculatePrice(qty);
    }

    @Override
    public String toString() {
        return super.toString() + " | Cuisine: " + cuisineType;
    }
}