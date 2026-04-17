// RestaurantPOS.java
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class RestaurantPOS {
    private List<MenuItem> menu;
    private List<Order> activeOrders;
    private double dailySales;
    private int nextOrderId;
    private int nextItemId;

    public RestaurantPOS() {
        this.menu = new ArrayList<>();
        this.activeOrders = new ArrayList<>();
        this.dailySales = 0.0;
        this.nextOrderId = 1;
        this.nextItemId = 1;
        loadSampleMenu();  // populate menu with sample items
    }

    private void loadSampleMenu() {
        menu.add(new FoodItem(nextItemId++, "Beef Burger", 250.0, "Juicy beef patty with lettuce and cheese", "MAIN", "Western"));
        menu.add(new FoodItem(nextItemId++, "Chicken Tikka", 300.0, "Grilled chicken tikka with salad", "MAIN", "Indian"));
        menu.add(new FoodItem(nextItemId++, "Mashed Potatoes", 120.0, "Creamy mashed potatoes", "SIDE", "Western"));
        menu.add(new FoodItem(nextItemId++, "Chocolate Cake", 180.0, "Rich chocolate cake with cream", "DESSERT", "Western"));

        menu.add(new BeverageItem(nextItemId++, "Coke", 50.0, "Cold cola drink", "DRINK", false));
        menu.add(new BeverageItem(nextItemId++, "Coffee", 70.0, "Hot coffee", "DRINK", true));
        menu.add(new BeverageItem(nextItemId++, "Mineral Water", 30.0, "Bottled mineral water", "DRINK", false));
    }

    // DISPLAY MENU
    public void displayMenu() {
        System.out.println("\n================ MENU =================");
        for (MenuItem item : menu) {
            System.out.println(item);
        }
        System.out.println("=======================================");
    }

    // CREATE NEW ORDER
    public Order createNewOrder(String table) {
        Order order = new Order(nextOrderId++, table);
        activeOrders.add(order);
        System.out.println("New order created for table: " + table + " | Order ID: " + order.getOrderId());
        return order;
    }

    // ADD ORDER TO ORDER (could be reused in future UI)
    public void takeOrder(Order order) {
        Scanner sc = new Scanner(System.in);
        while (true) {
            displayMenu();
            System.out.print("Enter item ID (0 to finish): ");
            int id = sc.nextInt();
            if (id == 0) break;

            MenuItem item = menu.stream()
                                .filter(m -> m.getId() == id)
                                .findFirst()
                                .orElse(null);

            if (item == null) {
                System.out.println("Invalid item ID.");
                continue;
            }

            System.out.print("Quantity: ");
            int qty = sc.nextInt();
            order.addItem(item, qty);
        }
        sc.close();
    }

    // GENERATE BILL & PROCESS PAYMENT
    public void generateBill(Order order, Payment payment) {
        System.out.println("\n=== BILL ===");
        order.displayOrder();

        double total = order.calculateTotal();
        boolean success = payment.processPayment(total);

        if (success) {
            order.setStatus("COMPLETED");
            dailySales += total;
            System.out.printf("Payment method: %s%n", payment.getPaymentType());
            System.out.println("Order completed & added to daily sales.");
        } else {
            System.out.println("Payment failed. Order remains pending.");
        }
    }

    // SHOW DAILY SALES REPORT
    public void showSalesReport() {
        System.out.println("\n=== DAILY SALES REPORT ===");
        System.out.printf("Total daily sales: %.2f BDT%n", dailySales);
        System.out.println("Active orders:");
        activeOrders.stream()
                    .filter(o -> "PENDING".equals(o.getStatus()))
                    .forEach(o -> System.out.printf("Order ID: %d | Table: %s | Total: %.2f%n",
                                                    o.getOrderId(), o.getTableNumber(), o.calculateTotal()));
    }

    // SIMPLE MENU-DRIVEN CONSOLE UI
    public void run() {
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("\n=== Restaurant POS ===");
            System.out.println("1. Display Menu");
            System.out.println("2. New Order");
            System.out.println("3. Take Order (Add items)");
            System.out.println("4. Generate Bill & Pay");
            System.out.println("5. Sales Report");
            System.out.println("0. Exit");
            System.out.print("Choose option: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1:
                    displayMenu();
                    break;

                case 2: {
                    System.out.print("Table number: ");
                    String table = sc.nextLine();
                    createNewOrder(table);
                    break;
                }

                case 3: {
                    System.out.print("Enter Order ID: ");
                    int orderId = sc.nextInt();
                    Order order = activeOrders.stream()
                                              .filter(o -> o.getOrderId() == orderId)
                                              .findFirst()
                                              .orElse(null);

                    if (order == null) {
                        System.out.println("Order not found.");
                    } else {
                        takeOrder(order);
                    }
                    break;
                }

                case 4: {
                    System.out.print("Enter Order ID: ");
                    int orderId = sc.nextInt();
                    Order order = activeOrders.stream()
                                              .filter(o -> o.getOrderId() == orderId)
                                              .findFirst()
                                              .orElse(null);

                    if (order == null || !"PENDING".equals(order.getStatus())) {
                        System.out.println("Order not found or already completed.");
                    } else {
                        System.out.println("Payment methods:");
                        System.out.println("1. Cash");
                        System.out.println("2. Card");
                        System.out.println("3. Mobile (bKash)");
                        System.out.print("Choose payment method: ");
                        int payChoice = sc.nextInt();

                        Payment payment = null;

                        if (payChoice == 1) {
                            payment = new CashPayment();
                        } else if (payChoice == 2) {
                            System.out.print("Card number: ");
                            String card = sc.next();
                            payment = new CardPayment(card);
                        } else if (payChoice == 3) {
                            System.out.print("Phone number: ");
                            String phone = sc.next();
                            payment = new MobilePayment(phone);
                        } else {
                            System.out.println("Invalid payment method.");
                        }

                        if (payment != null) {
                            generateBill(order, payment);
                        }
                    }
                    break;
                }

                case 5:
                    showSalesReport();
                    break;

                case 0:
                    System.out.println("Exiting POS...");
                    sc.close();
                    return;

                default:
                    System.out.println("Invalid choice. Try again.");
            }
        }
    }
}