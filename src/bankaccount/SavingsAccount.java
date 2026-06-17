package bankaccount;

public class SavingsAccount extends BankAccount {
    /** The name of the account owner. */
    private String ownerName;

    /**
     * Constructs a new savings account with the specified owner.
     * @param ownername the name of the account owner
     */
    public SavingsAccount(final String ownername) {
        this.ownerName = ownername;
    }

    /**
     * Gets the account owner's name.
     * @return the owner's name
     */
    public String getOwnerName() {
        return ownerName;
    }

}
