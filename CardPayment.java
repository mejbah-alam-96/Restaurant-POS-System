// CardPayment.java
public class CardPayment implements Payment {
    private String cardNumber;

    public CardPayment(String cardNumber) {
        this.cardNumber = cardNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.printf("Processing card payment of %.2f BDT to card ending in %s...%n", amount, cardNumber.substring(cardNumber.length() - 4));
        // Simulate success
        System.out.println("Card payment APPROVED.");
        return true;
    }

    @Override
    public String getPaymentType() {
        return "CARD";
    }
}