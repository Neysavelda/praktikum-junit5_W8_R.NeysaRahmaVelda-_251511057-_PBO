package id.ac.polban.pbo;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.junit.jupiter.api.Assertions.*;

@DisplayName("Pengujian Kelas BankAccount")
class BankAccountTest {

    private BankAccount acc;

    // PERCOBAAN 3: LIFECYCLE @BeforeAll
    @BeforeAll
    static void initAll() {
        System.out.println("@BeforeAll  : sekali di awal");
    }

    // PERCOBAAN 3: LIFECYCLE @BeforeEach
    @BeforeEach
    void setUp() {
        System.out.println("@BeforeEach : objek baru dibuat");
        acc = new BankAccount("001", "Andi", 100_000);
    }

    // PERCOBAAN 3: LIFECYCLE @AfterEach
    @AfterEach
    void tearDown() {
        System.out.println("@AfterEach  : test selesai");
    }

    // PERCOBAAN 3: LIFECYCLE @AfterAll
    @AfterAll
    static void tearDownAll() {
        System.out.println("@AfterAll   : sekali di akhir");
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

        // PERCOBAAN 4: PARAMETERIZED TEST DENGAN @ValueSource
        @ParameterizedTest
        @ValueSource(doubles = {-100, -1, 0})
        @DisplayName("Deposit jumlah tidak valid harus melempar IllegalArgumentException")
        void deposit_jumlahTidakValid_melemparException(double amount) {
            assertThrows(IllegalArgumentException.class, () -> acc.deposit(amount));
        }

        // PERCOBAAN 4: PARAMETERIZED TEST DENGAN @CsvSource (TERMASUK BOUNDARY VALUE 0.01)
        @ParameterizedTest
        @CsvSource({
            "0.01, 100000.01",
            "10000.0, 110000.0",
            "50000.0, 150000.0",
            "100000.0, 200000.0"
        })
        @DisplayName("Deposit berbagai nominal valid harus meng-update saldo")
        void deposit_berbagaiNominal_menambahSaldo(double amount, double expectedBalance) {
            acc.deposit(amount);
            assertEquals(expectedBalance, acc.getBalance(), 0.001);
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

    // LATIHAN 1: TRANSFER KE REKENING NULL
    @Test
    @DisplayName("L1: Transfer ke rekening null harus melempar IllegalArgumentException")
    void transfer_keRekeningNull_melemparIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class, () -> acc.transfer(null, 50_000));
        assertEquals("Rekening tujuan tidak boleh null", ex.getMessage());
        assertEquals(100_000, acc.getBalance(), 0.001);
    }

    // LATIHAN 2: TRANSFER KE REKENING SENDIRI
    @Test
    @DisplayName("L2: Transfer ke rekening sendiri harus melempar IllegalArgumentException")
    void transfer_keRekeningSendiri_melemparIllegalArgumentException() {
        IllegalArgumentException ex = assertThrows(
            IllegalArgumentException.class, () -> acc.transfer(acc, 50_000));
        assertEquals("Tidak dapat transfer ke rekening sendiri", ex.getMessage());
        assertEquals(100_000, acc.getBalance(), 0.001);
    }
}