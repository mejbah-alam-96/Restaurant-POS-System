// MenuItem.java
import java.util.Objects;

public abstract class MenuItem {
    protected int id;
    protected String name;
    protected double price;
    protected String description;
    protected String category; // e.g., "STARTER", "MAIN", "DESSERT"

    public MenuItem(int id, String name, double price, String description, String category) {
        this.id = id;
        this.name = name;
        this.price = price;
        this.description = description;
        this.category = category;
    }

    public int getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public double getPrice() {
        return price;
    }

    public String getDescription() {
        return description;
    }

    public String getCategory() {
        return category;
    }

    public double calculatePrice(int qty) {
        return price * qty;
    }

    public abstract String getItemType();  // "FOOD" or "BEVERAGE"

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof MenuItem)) return false;
        MenuItem that = (MenuItem) o;
        return id == that.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    @Override
    public String toString() {
        return String.format("%-4d %-18s %.2f BDT | %s", id, name, price, description);
    }
}