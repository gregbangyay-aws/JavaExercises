package BankAccountWithOOP;

public class BankAccount {
	protected double balance;
	protected boolean isFrozen;
	
	public BankAccount() {
		super();
		this.balance = 0;
		this.isFrozen = false;
	}
	
	public String deposit(double amount) {
		if(isFrozen) {
			return "This account is Frozen. Please unfreeze it first.";
		} else if (amount <= 0) {
			return "Amount deposited: " + amount + "\nInvalid amount. Must be greater than 0";
		} else {
			balance += amount;
			return String.format("Amount deposited: %.2f\nCurrent balance: %.2f", amount, balance);
		}
		
	}
	public String withdraw(double amount) {
		if(isFrozen) {
			return "This account is Frozen. Please unfreeze it first.";
		} else {
			if (amount > balance) {
				return "Amount withdrawn: " + amount + "\nInsufficient Funds.\nCurrent balance: " +balance;
			} else if (amount < 0) {
				return "Amount withdrawn: " + amount + "\nInvalid amount. Must be greater than 0";
			} else {
				balance -= amount;
				return String.format("Amount withdrawn: %.2f\nCurrent balance: %.2f", amount, balance);
			}
		}
	}
	public double getBalance() {
		return balance;
		
	}
	public void freezeAccount() {
		isFrozen = true;
	}
	public void unfreezeAccount() {
		isFrozen = false;
	}
	public boolean isFrozen() {
		return isFrozen;
	}
	
	
	
}
