package bankaccount;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class BankAccountManager implements Bank {
    /**
     * A registry mapping unique account identifiers to their respective
     * {@link BankAccount} instances.
     */
    private Map<Integer, BankAccount> accounts;

    /**
     * The id assigned.
     */
    private int id;

    /**
     * Constructor for this class.
     */
    public BankAccountManager() {
        super();
        this.accounts = new HashMap<>();
        this.id = 0;
    }

    @Override
    public String addAccount(BankAccount account) {
        accounts.put(id, account);
        return "Account added successfully with ID: " + id++;
    }

    @Override
    public BankAccount getAccount(int id) {
        return accounts.get(id);
    }

    public void listAccounts() {
        for (Map.Entry<Integer, BankAccount> entry : accounts.entrySet()) {
            Integer id = entry.getKey();
            BankAccount account = entry.getValue();

            if (account instanceof SavingsAccount) {
                SavingsAccount savings = (SavingsAccount) account;

                System.out.println("Account ID: " + id + ", Account Name: "
                        + savings.getOwnerName() + ", Balance: "
                        + account.getBalance());
            }
        }
    }

    /**
     * Sorts a list of transactions by their amount in ascending order.
     *
     * @param txList the original list of transactions to sort
     * @return a new list containing the sorted transactions
     */
    public List<Transaction> sortTransactionsByAmount(
            final List<Transaction> txList) {
        if (txList == null) {
            return new ArrayList<>();
        }
        // Create a copy so we don't modify the original list order
        List<Transaction> sortedList = new ArrayList<>(txList);

        // Lambda: compares the amounts of two transaction objects (t1 and t2)
        sortedList.sort((t1, t2) -> Double.compare(t1.amount, t2.amount));

        return sortedList;
    }

    /**
     * Filters transactions to only include those with an amount greater than or
     * equal to the specified threshold.
     *
     * @param amount the minimum threshold amount
     * @param txList the original list of transactions to filter
     * @return a new list containing only transactions at or above the specified
     *         amount
     */
    public List<Transaction> filterTransactionsAtOrAbove(final double amount,
            final List<Transaction> txList) {
        if (txList == null) {
            return new ArrayList<>();
        }

        // Create a copy of the list.
        List<Transaction> filteredList = new ArrayList<>(txList);

        // Lambda: removes items if their amount is less than the target amount
        filteredList.removeIf(tx -> tx.amount < amount);

        return filteredList;
    }
}
