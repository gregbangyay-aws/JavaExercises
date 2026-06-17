package bankaccount;

import java.time.LocalDateTime;

public class Transaction {
    /**
     * Type of the transaction.
     */
    private String type;
    /**
     * Amount of the current transaction.
     */
    private double amount;
    /**
     * Timestamp of the transaction.
     */
    private LocalDateTime time_stamp;

    /**
     * Constructs a new Transaction with the specified type and monetary amount.
     *
     * @param type   the type of transaction (e.g., "Deposit", "Withdraw")
     * @param amount the monetary value associated with this transaction
     */
    public Transaction(final String type, final double amount) {
        this.type = type;
        this.amount = amount;
        this.time_stamp = LocalDateTime.now();
    }

    /**
     * @return a formatted string containing the type, amount, and timestamp
     */
    @Override
    public String toString() {
        return String.format("Type: %s%nAmount: %.2f%nTimestamp: %s%n", type,
                amount, time_stamp);
    }

}
