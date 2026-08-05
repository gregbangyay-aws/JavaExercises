package bankaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;

class BankAccountManagerTest {

    private BankAccountManager manager;

    @BeforeEach
    void setUp() {
        manager = new BankAccountManager();
    }

    @Nested
    @DisplayName("Account Registry Tests")
    class AccountRegistryTests {

        @Test
        @DisplayName("addAccount increments ID sequentially and stores account")
        void testAddAndGetAccount_CreateNewAccounts_CreatedAccountsShouldExist() {
            BankAccount acc1 = new BankAccount();
            BankAccount acc2 = new BankAccount();

            String msg1 = manager.addAccount(acc1);
            String msg2 = manager.addAccount(acc2);

            assertEquals("Account added successfully with ID: 0", msg1);
            assertEquals("Account added successfully with ID: 1", msg2);

            assertEquals(acc1, manager.getAccount(0));
            assertEquals(acc2, manager.getAccount(1));
        }

        @Test
        @DisplayName("getAccount returns null for non-existent ID")
        void testGetNonExistentAccount_CheckNonExistingAccount_ShouldReturnAnError() {
            assertNull(manager.getAccount(999));
        }
    }

    @Nested
    @DisplayName("listAccounts Output Tests")
    class ListAccountsTests {

        @Test
        @DisplayName("listAccounts prints details for SavingsAccount and skips generic BankAccount")
        void testListAccountsFilteringAndOutput_CreateAccountsDepositToSavingsAccount_DepositShouldBeInSavingsAccount() throws Exception {
            BankAccount regularAccount = new BankAccount();
            SavingsAccount savingsAccount = new SavingsAccount("Baterbonia");
            savingsAccount.deposit(500.0);

            manager.addAccount(regularAccount);
            manager.addAccount(savingsAccount);  

            // Capture System.out output
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            PrintStream originalOut = System.out;
            System.setOut(new PrintStream(outputStream));

            try {
                manager.listAccounts();
            } finally {
                System.setOut(originalOut);
            }

            String output = outputStream.toString().trim();

            // Should print ID 1 (SavingsAccount) but NOT ID 0 (Generic BankAccount)
            assertTrue(output.contains("Account ID: 1, Account Name: Baterbonia, Balance: 500.0"));
            assertFalse(output.contains("Account ID: 0"));
        }
    }

    @Nested
    @DisplayName("Transaction Sorting Tests")
    class SortingTests {

        @Test
        @DisplayName("sortTransactionsByAmount sorts transactions in ascending order without mutating original")
        void testSortTransactionsByAmount_CreateTransactionsThenSort_TransactionsShouldBeSortedProperly() {
            Transaction t1 = new Transaction("Deposit", 100.0);
            Transaction t2 = new Transaction("Withdraw", 20.0);
            Transaction t3 = new Transaction("Deposit", 50.0);

            List<Transaction> original = Arrays.asList(t1, t2, t3);
            List<Transaction> sorted = manager.sortTransactionsByAmount(original);

            assertEquals(3, sorted.size());
            assertEquals(20.0, sorted.get(0).amount, 0.001);
            assertEquals(50.0, sorted.get(1).amount, 0.001);
            assertEquals(100.0, sorted.get(2).amount, 0.001);

            // Verify original list order is untouched
            assertEquals(100.0, original.get(0).amount, 0.001);
        }

        @Test
        @DisplayName("sortTransactionsByAmount handles null and empty lists")
        void testSortNullAndEmpty_SortEmptyTransactions_ShouldHandleNullAndEmptyLists() {
            assertNotNull(manager.sortTransactionsByAmount(null));
            assertTrue(manager.sortTransactionsByAmount(null).isEmpty());

            assertTrue(manager.sortTransactionsByAmount(Collections.emptyList()).isEmpty());
        }
    }

    @Nested
    @DisplayName("Transaction Filtering Tests")
    class FilteringTests {

        @Test
        @DisplayName("filterTransactionsAtOrAbove removes items strictly below threshold")
        void testFilterTransactionsAtOrAbove_CreateTransactionsThenFilter_TransactionsShouldBeFilteredProperly() {
            Transaction t1 = new Transaction("Deposit", 100.0);
            Transaction t2 = new Transaction("Withdraw", 20.0);
            Transaction t3 = new Transaction("Deposit", 50.0);

            List<Transaction> original = Arrays.asList(t1, t2, t3);
            List<Transaction> filtered = manager.filterTransactionsAtOrAbove(50.0, original);

            assertEquals(2, filtered.size());
            assertTrue(filtered.stream().allMatch(t -> t.amount >= 50.0));

            // Verify original list size remains unaffected
            assertEquals(3, original.size());
        }

        @Test
        @DisplayName("filterTransactionsAtOrAbove handles null and empty lists")
        void testFilterNullAndEmpty_FilterEmptyTransactions_ShouldHandleNullAndEmptyLists() {
            assertNotNull(manager.filterTransactionsAtOrAbove(50.0, null));
            assertTrue(manager.filterTransactionsAtOrAbove(50.0, null).isEmpty());

            assertTrue(manager.filterTransactionsAtOrAbove(50.0, Collections.emptyList()).isEmpty());
        }
    }
}