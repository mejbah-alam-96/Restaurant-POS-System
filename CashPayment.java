// CashPayment.java
public class CashPayment implements Payment {
    @Override
    public boolean processPayment(double amount) {
        System.out.printf("Processing cash payment of %.2f BDT...%n", amount);
        // Simulate success
        System.out.println("Cash payment SUCCESS.");
        return true;
    }

    @Override
    public String getPaymentType() {
        return "CASH";
    }
}