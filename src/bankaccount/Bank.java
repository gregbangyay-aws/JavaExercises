package bankaccount;

public interface Bank {
    /**
     * Registers a new bank account into the system.
     *
     * @param account the {@link BankAccount} object to be added to the
     *                registry; must not be null
     * @return a confirmation message or unique status string indicating the
     *         outcome of the addition
     */
    String addAccount(BankAccount account);

    /**
     * Retrieves a registered bank account using its unique identifier.
     *
     * @param id the unique integer ID of the account to look up
     * @return the matching {@link BankAccount} instance, or {@code null} if no
     *         account exists with the specified ID
     */
    BankAccount getAccount(int id);
}
