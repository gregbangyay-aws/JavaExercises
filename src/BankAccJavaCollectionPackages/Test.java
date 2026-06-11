package BankAccJavaCollectionPackages;

public class Test {
	public static void main(String[] args) {
		BankAccount acc1 = new SavingsAccount("Test account");
		
		System.out.println(acc1.deposit(1000.125));
		
		//System.out.println(acc1.deposit(-500));
		System.out.println();
		
		System.out.println(acc1.getTransactionHistory());
		
		BankAccountManager accM1 = new BankAccountManager();
		System.out.println(accM1.addAccount(acc1));
		System.out.println(accM1.addAccount(new SavingsAccount("Test1")));
		
		BankAccount acc2 = new SavingsAccount("Test account2");
		System.out.println(acc2.deposit(9999999));
		System.out.println(accM1.addAccount(acc2));
		
		
		accM1.listAccounts();
	}
}
