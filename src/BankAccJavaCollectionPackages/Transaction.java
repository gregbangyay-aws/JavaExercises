package BankAccJavaCollectionPackages;

import java.time.LocalDateTime;

public class Transaction {
	protected String type;
	protected double amount;
	protected LocalDateTime time_stamp;
	
	public Transaction(String type, double amount) {
		this.type = type;
		this.amount = amount;
		this.time_stamp = LocalDateTime.now();
	}
	
	public String toString() {
	    return "Type: " + type + 
	           "\nAmount: " + amount + 
	           "\nTimestamp: " + time_stamp;
	}

}
