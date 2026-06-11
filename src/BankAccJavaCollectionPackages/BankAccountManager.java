package BankAccJavaCollectionPackages;

import java.util.HashMap;
import java.util.Map;

public class BankAccountManager implements Bank {
	
	Map<Integer, BankAccount> accounts;
	int id;
	
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

	            System.out.println("Account ID: " + id + 
	                               ", Account Name: " + savings.getOwnerName() + 
	                               ", Balance: " + account.getBalance());
	        }
	    }
	}
	
	

}
