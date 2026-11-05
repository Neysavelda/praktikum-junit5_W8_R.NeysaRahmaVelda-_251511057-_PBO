package id.ac.polban.pbo;

public class BankAccount {
    private final String accountNumber;
    private final String owner;
    protected double balance;

    // PERCOBAAN 1 & 2: CONSTRUCTOR & VALIDASI SALDO AWAL
    public BankAccount(String accountNumber, String owner, double initialBalance) {
        if (accountNumber == null || accountNumber.isBlank()) {
            throw new IllegalArgumentException("Nomor rekening wajib diisi");
        }
        if (initialBalance < 0) {
            throw new IllegalArgumentException("Saldo awal tidak boleh negatif");
        }
        this.accountNumber = accountNumber;
        this.owner = owner;
        this.balance = initialBalance;
    }

    // PERCOBAAN 1: METHOD DEPOSIT
    public void deposit(double amount) {
        if (amount <= 0) {
            throw new IllegalArgumentException("Jumlah setoran harus lebih dari 0");
        }
        balance += amount;
    }

    // PERCOBAAN 2: METHOD WITHDRAW & EXCEPTION
    public void withdraw(double amount) throws InsufficientBalanceException {
        if (amount <= 0) {
            throw new IllegalArgumentException("Jumlah penarikan harus lebih dari 0");
        }
        if (amount > balance) {
            throw new InsufficientBalanceException("Saldo tidak mencukupi");
        }
        balance -= amount;
    }

    // LATIHAN 1 & 2: TRANSFER DENGAN VALIDASI NULL & REKENING SENDIRI
    public void transfer(BankAccount target, double amount) throws InsufficientBalanceException {
        // LATIHAN 1: Validasi target null
        if (target == null) {
            throw new IllegalArgumentException("Rekening tujuan tidak boleh null");
        }
        // LATIHAN 2: Validasi transfer ke rekening sendiri
        if (target == this || target.getAccountNumber().equals(this.accountNumber)) {
            throw new IllegalArgumentException("Tidak dapat transfer ke rekening sendiri");
        }
        withdraw(amount);
        target.deposit(amount);
    }

    // GETTER METHODS
    public String getAccountNumber() { return accountNumber; }
    public String getOwner() { return owner; }
    public double getBalance() { return balance; }
}