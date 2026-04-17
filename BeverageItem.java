// BeverageItem.java
public class BeverageItem extends MenuItem {
    private boolean isHot;

    public BeverageItem(int id, String name, double price, String description, String category, boolean isHot) {
        super(id, name, price, description, category);
        this.isHot = isHot;
    }

    public boolean isHot() {
        return isHot;
    }

    @Override
    public String getItemType() {
        return "BEVERAGE";
    }

    @Override
    public double calculatePrice(int qty) {
        return super.calculatePrice(qty);
    }

    @Override
    public String toString() {
        String temp = isHot ? "Hot" : "Cold";
        return super.toString() + " | " + temp + " Beverage";
    }
}