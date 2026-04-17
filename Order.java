// Order.java
import java.util.ArrayList;
import java.util.List;

public class Order {
    private int orderId;
    private String tableNumber;
    private List<OrderItem> items;
    private String status;  // e.g., "PENDING", "COMPLETED"

    public Order(int orderId, String tableNumber) {
        this.orderId = orderId;
        this.tableNumber = tableNumber;
        this.items = new ArrayList<>();
        this.status = "PENDING";
    }

    public int getOrderId() {
        return orderId;
    }

    public String getTableNumber() {
        return tableNumber;
    }

    public List<OrderItem> getItems() {
        return new ArrayList<>(items);
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void addItem(MenuItem item, int qty) {
        if (qty <= 0) return;
        items.add(new OrderItem(item, qty));
    }

    public boolean removeItem(int itemId) {
        return items.removeIf(oi -> oi.getItem().getId() == itemId);
    }

    public double calculateTotal() {
        return items.stream()
                    .mapToDouble(OrderItem::getSubTotal)
                    .sum();
    }

    public void displayOrder() {
        System.out.println("Order ID: " + orderId + " | Table: " + tableNumber + " | Status: " + status);
        System.out.println("----------------------------------------------------------------------------");
        for (OrderItem oi : items) {
            System.out.println(oi);
        }
        System.out.println("----------------------------------------------------------------------------");
        System.out.printf("Total: %.2f BDT%n", calculateTotal());
    }
}