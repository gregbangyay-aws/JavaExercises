package bankaccount;

import java.util.ArrayList;
import java.util.List;

import exception.AccountFrozenException;
import exception.InsufficientFundsException;
import exception.InvalidAmountException;

public class BankAccount {

    /**
     * The current monetary balance of the account.
     */
    private double balance;
    /**
     * Indicates whether the account is frozen.
     */
    private boolean isFrozen;
    /**
     * The chronological log of all completed transactions on this account.
     */
    private List<Transaction> transactionHistory;

    /**
     * Constructor for this class.
     */
    public BankAccount() {
        this.balance = 0;
        this.isFrozen = false;
        this.transactionHistory = new ArrayList<>();
    }

    /**
     * Deposits a specified amount into the account.
     *
     * @param amount the amount of money to deposit
     * @return a success message showing amount and current balance
     * @throws AccountFrozenException   if account is frozen
     * @throws IllegalArgumentException if the deposit less or equal to 0
     */
    public String deposit(final double amount)
            throws AccountFrozenException, InvalidAmountException {
        if (isFrozen) {
            throw new AccountFrozenException(
                    "This account is FROZEN. Unable to transact.");
        }
        if (amount <= 0) {
            throw new InvalidAmountException(
                    "Invalid amount. Must be greater than 0");
        }
        balance += amount;
        transactionHistory.add(new Transaction("Deposit", amount));

        return String.format(
                "Amount deposited: %.2f\n" + "Current balance: %.2f", amount,
                balance);
    }

    /**
     * Withdraws a specified amount from the account.
     *
     * @param amount the amount of money to withdraw
     * @return a success message showing the withdrawn amount and current
     *         balance
     * @throws AccountFrozenException     if the account is frozen
     * @throws InvalidAmountException     if the amount is negative or exceeds
     *                                    the balance
     * @throws InsufficientFundsException
     */
    public String withdraw(final double amount) throws AccountFrozenException,
            InvalidAmountException, InsufficientFundsException {
        if (isFrozen) {
            throw new AccountFrozenException(
                    "This account is FROZEN. Unable to transact.");
        }
        if (amount < 0) {
            throw new InvalidAmountException(
                    "Invalid amount. Must be greater than 0");
        }
        if (amount > balance) {
            throw new InsufficientFundsException("Insufficient balance.");
        }
        balance -= amount;
        transactionHistory.add(new Transaction("Withdraw", amount));

        return String.format("Amount withdrawn: %.2f\nCurrent balance: %.2f",
                amount, balance);
    }

    /**
     * @return Current balance
     */
    public double getBalance() {
        return balance;
    }

    /**
     * Freezes current account.
     */
    public void freezeAccount() {
        isFrozen = true;
    }

    /**
     * Unfreeze current account.
     */
    public void unfreezeAccount() {
        isFrozen = false;
    }

    /**
     * @return checks if account is frozen or not
     */
    public boolean isFrozen() {
        return isFrozen;
    }

    /**
     * @return the list of transaction history for the account
     */
    public List<Transaction> getTransactionHistory() {
        return transactionHistory;
    }

}
