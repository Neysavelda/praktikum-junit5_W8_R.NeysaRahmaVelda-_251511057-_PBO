package id.ac.polban.pbo;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pengujian Kelas BankAccount")
class BankAccountTest {

    private BankAccount acc;

    // PERCOBAAN 3: SETUP LIFECYCLE (@BeforeEach)
    @BeforeEach
    void setUp() {
        acc = new BankAccount("001", "Andi", 100_000);
    }

    // PERCOBAAN 1: ASSERTION DASAR & CONSTRUCTOR
    @Test
    @DisplayName("Constructor harus menyimpan data rekening dengan benar")
    void constructor_menyimpanDataDenganBenar() {
        assertAll("data rekening",
            () -> assertEquals("001", acc.getAccountNumber()),
            () -> assertEquals("Andi", acc.getOwner()),
            () -> assertEquals(100_000, acc.getBalance(), 0.001)
        );
    }

    // PERCOBAAN 3: NESTED TEST DEPOSIT
    @Nested
    @DisplayName("Pengujian Method Deposit")
    class DepositTest {

        @Test
        @DisplayName("Deposit dengan jumlah valid harus menambah saldo")
        void deposit_menambahSaldo() {
            acc.deposit(50_000);
            assertEquals(150_000, acc.getBalance(), 0.001);
        }

        // PERCOBAAN 2: EXCEPTION PADA DEPOSIT
        @Test
        @DisplayName("Deposit dengan jumlah nol atau negatif harus melempar IllegalArgumentException")
        void deposit_jumlahTidakValid_melemparIllegalArgument() {
            IllegalArgumentException ex = assertThrows(
                IllegalArgumentException.class, () -> acc.deposit(-10));
            assertEquals("Jumlah setoran harus lebih dari 0", ex.getMessage());
        }
    }

    // PERCOBAAN 3: NESTED TEST WITHDRAW
    @Nested
    @DisplayName("Pengujian Method Withdraw")
    class WithdrawTest {

        @Test
        @DisplayName("Withdraw dengan jumlah valid harus mengurangi saldo")
        void withdraw_jumlahValid_tidakMelempar() {
            assertDoesNotThrow(() -> acc.withdraw(30_000));
            assertEquals(70_000, acc.getBalance(), 0.001);
        }

        // PERCOBAAN 2: EXCEPTION PADA WITHDRAW
        @Test
        @DisplayName("Withdraw melebihi saldo harus melempar InsufficientBalanceException dan saldo tetap")
        void withdraw_melebihiSaldo_melemparInsufficientBalance() {
            assertThrows(InsufficientBalanceException.class, () -> acc.withdraw(200_000));
            assertEquals(100_000, acc.getBalance(), 0.001);
        }
    }
}