package BankAccJavaCollectionPackages;

import java.time.LocalDateTime;

public class Transaction {
	String type;
	double amount;
	LocalDateTime time_stamp;
	
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
