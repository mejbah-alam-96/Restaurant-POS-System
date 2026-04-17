// OrderItem.java
public class OrderItem {
    private MenuItem item;
    private int quantity;

    public OrderItem(MenuItem item, int quantity) {
        this.item = item;
        this.quantity = quantity;
    }

    public MenuItem getItem() {
        return item;
    }

    public int getQuantity() {
        return quantity;
    }

    public double getSubTotal() {
        return item.calculatePrice(quantity);
    }

    @Override
    public String toString() {
        return String.format("%-4d %-18s %-3d x %.2f = %.2f BDT", 
                             item.getId(), item.getName(), quantity, item.getPrice(), getSubTotal());
    }
}