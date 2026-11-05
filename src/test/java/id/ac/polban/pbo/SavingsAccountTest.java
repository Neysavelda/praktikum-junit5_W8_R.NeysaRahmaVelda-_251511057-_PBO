package id.ac.polban.pbo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pengujian Kelas SavingsAccount (Inheritance)")
class SavingsAccountTest {

    @Test
    @DisplayName("SavingsAccount mewarisi fungsionalitas BankAccount dan menambahkan bunga")
    void addInterest_menambahSaldoSesuaiBunga() {
        SavingsAccount sa = new SavingsAccount("SA-01", "Budi", 1_000_000, 0.05);
        sa.deposit(500_000); // menguji method warisan dari BankAccount
        
        sa.addInterest(); // 1.500.000 * 0.05 = 75.000 -> saldo akhir 1.575.000
        assertEquals(1_575_000, sa.getBalance(), 0.01);
    }
}