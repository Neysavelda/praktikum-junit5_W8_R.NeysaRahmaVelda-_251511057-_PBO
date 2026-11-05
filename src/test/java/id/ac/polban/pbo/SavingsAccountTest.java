package id.ac.polban.pbo;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pengujian Kelas SavingsAccount (Inheritance & Taxable)")
class SavingsAccountTest {

    // PERCOBAAN 5: TEST INSTANCEOF
    @Test
    @DisplayName("SavingsAccount adalah instance dari BankAccount")
    void savingsAccount_adalahBankAccount() {
        SavingsAccount sa = new SavingsAccount("SA-01", "Budi", 1_000_000, 0.05);
        assertInstanceOf(BankAccount.class, sa);
    }

    // PERCOBAAN 5: TEST MEWARISI METODE & BUNGA
    @Test
    @DisplayName("SavingsAccount mewarisi fungsionalitas BankAccount dan menambahkan bunga")
    void addInterest_menambahSaldoSesuaiBunga() {
        SavingsAccount sa = new SavingsAccount("SA-01", "Budi", 1_000_000, 0.05);
        sa.deposit(500_000);
        sa.addInterest();
        assertEquals(1_575_000, sa.getBalance(), 0.01);
    }

    // LATIHAN 3: TEST BUNGA NEGATIF
    @Test
    @DisplayName("L3: Suku bunga negatif harus melempar IllegalArgumentException")
    void constructor_sukuBungaNegatif_melemparIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class,
            () -> new SavingsAccount("SA-02", "Cici", 500_000, -0.05)
        );
        assertEquals("Suku bunga tidak boleh negatif", ex.getMessage());
    }

    // LATIHAN 5: TEST INTERFACE TAXABLE
    @Test
    @DisplayName("L5: SavingsAccount menghitung pajak 20% dari bunga dengan benar")
    void calculateTax_menghitungPajakBungaSesuai() {
        Taxable taxable = new SavingsAccount("SA-03", "Dedi", 1_000_000, 0.10);
        assertEquals(20_000, taxable.calculateTax(), 0.001);
    }
}