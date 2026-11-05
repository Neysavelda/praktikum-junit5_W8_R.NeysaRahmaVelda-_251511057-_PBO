package id.ac.polban.pbo;

// PERCOBAAN 5 & LATIHAN 5: INHERITANCE & INTERFACE TAXABLE
public class SavingsAccount extends BankAccount implements Taxable {
    private final double interestRate;
    public static final double MIN_BALANCE = 50_000;

    // LATIHAN 3: VALIDASI BUNGA NEGATIF
    public SavingsAccount(String accountNumber, String owner, double initialBalance, double interestRate) {
        super(accountNumber, owner, initialBalance);
        if (interestRate < 0) {
            throw new IllegalArgumentException("Suku bunga tidak boleh negatif");
        }
        this.interestRate = interestRate;
    }

    // PERCOBAAN 5: METHOD TAMBAHAN BUNGA
    public void addInterest() {
        double interest = balance * interestRate;
        balance += interest;
    }

    // PERCOBAAN 5: OVERRIDE WITHDRAW (SALDO MINIMUM RP 50.000)
    @Override
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (getBalance() - amount < MIN_BALANCE) {
            throw new InsufficientBalanceException("Saldo minimum Rp50.000 harus dipertahankan");
        }
        super.withdraw(amount);
    }

    // LATIHAN 5: IMPLEMENTASI INTERFACE TAXABLE (PAJAK 20%)
    @Override
    public double calculateTax() {
        double interest = balance * interestRate;
        return interest * 0.20;
    }

    public double getInterestRate() {
        return interestRate;
    }
}