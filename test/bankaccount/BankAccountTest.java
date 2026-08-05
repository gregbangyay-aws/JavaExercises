package bankaccount;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Nested;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import exception.AccountFrozenException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

class BankAccountTest {

    private BankAccount account;

    @BeforeEach
    void setUp() {
        account = new BankAccount();
    }

    @Nested
    @DisplayName("Initial State Tests")
    class InitialStateTests {

        @Test
        @DisplayName("New account should have zero balance")
        void testInitialBalance_BalanceShouldBeZero_ShouldReturnNoError() {
            assertEquals(0.0, account.getBalance(), 0.001);
        }

        @Test
        @DisplayName("New account should not be frozen")
        void testInitialFrozenState_ShouldNotBetFrozen_ShouldReturnNoError() {
            assertFalse(account.isFrozen());
        }

        @Test
        @DisplayName("New account should have empty transaction history")
        void testInitialTransactionHistory_ShouldHaveZeroTransactions_ShouldReturnNoError() {
            assertTrue(account.getTransactionHistory().isEmpty());
        }
    }

    @Nested
    @DisplayName("Deposit Tests")
    class DepositTests {

        @Test
        @DisplayName("Valid deposit updates balance, returns message, and records transaction")
        void testValidDeposit_Deposit100_ShouldDeposit100() throws Exception {
            String result = account.deposit(100.0);

            assertEquals(100.0, account.getBalance(), 0.001);
            assertEquals("Amount deposited: 100.00\nCurrent balance: 100.00", result);
            assertEquals(1, account.getTransactionHistory().size());
        }

        @ParameterizedTest
        @ValueSource(doubles = {0.0, -10.0, -0.01})
        @DisplayName("Deposit zero or negative amount throws InvalidAmountException")
        void testInvalidDepositAmount_ZeroNegativeValuesInput_ShouldReturnAnError(double amount) {
            InvalidAmountException ex = assertThrows(
                InvalidAmountException.class, 
                () -> account.deposit(amount)
            );
            assertEquals("Invalid amount. Must be greater than 0", ex.getMessage());
            assertEquals(0.0, account.getBalance(), 0.001);
            assertTrue(account.getTransactionHistory().isEmpty());
        }

        @Test
        @DisplayName("Deposit when account is frozen throws AccountFrozenException")
        void testDepositWhenFrozen_DepositWhenAccountIsFrozen_ShouldReturnAnError() {
            account.freezeAccount();

            AccountFrozenException ex = assertThrows(
                AccountFrozenException.class, 
                () -> account.deposit(50.0)
            );
            assertEquals("This account is FROZEN. Unable to transact.", ex.getMessage());
            assertEquals(0.0, account.getBalance(), 0.001);
        }
    }

    @Nested
    @DisplayName("Withdrawal Tests")
    class WithdrawTests {

        @BeforeEach
        void depositFunds_Deposit200_200ShouldbeDeposited() throws Exception {
            account.deposit(200.0);
        }

        @Test
        @DisplayName("Valid withdrawal updates balance, returns message, and records transaction")
        void testValidWithdrawal_Withdraw50_50ShouldBeWithdrawnAndRecorded() throws Exception {
            String result = account.withdraw(50.0);

            assertEquals(150.0, account.getBalance(), 0.001);
            assertEquals("Amount withdrawn: 50.00\nCurrent balance: 150.00", result);
            assertEquals(2, account.getTransactionHistory().size());
        }

        @Test
        @DisplayName("Withdrawing exact account balance leaves zero balance")
        void testWithdrawFullBalance_WithdrawFullAmount_ShouldHave0Balance() throws Exception {
            account.withdraw(200.0);
            assertEquals(0.0, account.getBalance(), 0.001);
        }

        @Test
        @DisplayName("Withdrawal with negative amount throws InvalidAmountException")
        void testNegativeWithdrawal_WithdrawNegativeAmount_ShouldReturnAnError() {
            InvalidAmountException ex = assertThrows(
                InvalidAmountException.class, 
                () -> account.withdraw(-50.0)
            );
            assertEquals("Invalid amount. Must be greater than 0", ex.getMessage());
            assertEquals(200.0, account.getBalance(), 0.001);
        }

        @Test
        @DisplayName("Withdrawal exceeding balance throws InsufficientFundsException")
        void testInsufficientFunds_WithdrawAmountMoreThanBalance_ShouldReturnAnError() {
            InsufficientFundsException ex = assertThrows(
                InsufficientFundsException.class, 
                () -> account.withdraw(250.0)
            );
            assertEquals("Insufficient balance.", ex.getMessage());
            assertEquals(200.0, account.getBalance(), 0.001);
        }

        @Test
        @DisplayName("Withdrawal when account is frozen throws AccountFrozenException")
        void testWithdrawWhenFrozen_WithdrawWhileFrozen_ShouldReturnAnError() {
            account.freezeAccount();

            AccountFrozenException ex = assertThrows(
                AccountFrozenException.class, 
                () -> account.withdraw(50.0)
            );
            assertEquals("This account is FROZEN. Unable to transact.", ex.getMessage());
            assertEquals(200.0, account.getBalance(), 0.001);
        }
    }

    @Nested
    @DisplayName("Freeze and Unfreeze Controls")
    class AccountStateTests {

        @Test
        @DisplayName("Freezing and unfreezing updates isFrozen state correctly")
        void testFreezeAndUnfreeze_FreezeAccount_AccountShouldBeFrozen() {
            account.freezeAccount();
            assertTrue(account.isFrozen());

            account.unfreezeAccount();
            assertFalse(account.isFrozen());
        }

        @Test
        @DisplayName("Transactions succeed after unfreezing account")
        void testTransactAfterUnfreeze_UnfreezeAccountDeposit_AccountUnfrozenDepositShouldWork() throws Exception {
            account.freezeAccount();
            account.unfreezeAccount();

            account.deposit(100.0);
            assertEquals(100.0, account.getBalance(), 0.001);
        }
    }
}
