package BankAccountWithOOP;

public class Test {
	public static void main(String[] args) {
		BankAccount acc1 = new SavingsAccount("Test account");
		
		System.out.println(acc1.deposit(1000.125));
		
		//System.out.println(acc1.deposit(-500));
		System.out.println();
		
		acc1.freezeAccount();
		//System.out.println(acc1.deposit(99999));
		System.out.println(acc1.withdraw(100));
		
		
		System.out.println();
		acc1.unfreezeAccount();
		System.out.println(acc1.withdraw(100));
		
		
	}
}
