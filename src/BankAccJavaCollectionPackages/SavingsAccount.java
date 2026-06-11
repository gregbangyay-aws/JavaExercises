package BankAccJavaCollectionPackages;

public class SavingsAccount extends BankAccount{
	protected String ownerName;

	public SavingsAccount(String ownername) {
		this.ownerName = ownername;
	}
	
	public String getOwnerName() {
		return ownerName;
	}
	
}
