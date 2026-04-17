// MobilePayment.java
public class MobilePayment implements Payment {
    private String phoneNumber;

    public MobilePayment(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    @Override
    public boolean processPayment(double amount) {
        System.out.printf("Sending payment of %.2f BDT to mobile number: %s...%n", amount, phoneNumber);
        // Simulate success
        System.out.println("Mobile payment SUCCESS.");
        return true;
    }

    @Override
    public String getPaymentType() {
        return "MOBILE";
    }
}