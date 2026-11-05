package id.ac.polban.pbo;

public class SavingsAccount extends BankAccount implements Taxable {    private final double interestRate;

    public SavingsAccount(String accountNumber, String owner, double initialBalance, double interestRate) {
        super(accountNumber, owner, initialBalance);
        if (interestRate < 0) {
            throw new IllegalArgumentException("Suku bunga tidak boleh negatif");
        }
        this.interestRate = interestRate;
    }

    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
    }

    @Override
    public double calculateTax() {
        double interest = balance * interestRate;
        return interest * 0.20; // Pajak 20% dari bunga
    }

    public double getInterestRate() {
        return interestRate;
    }
}