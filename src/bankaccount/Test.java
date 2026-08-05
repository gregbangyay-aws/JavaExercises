package bankaccount;

import exception.AccountFrozenException;
import exception.InvalidAmountException;

public class Test {
    public static void main(String[] args) {
        BankAccount acc1 = new SavingsAccount("Test account");

        try {
            System.out.println(acc1.deposit(1000.125));
        } catch (AccountFrozenException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidAmountException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        System.out.println();

        System.out.println(acc1.getTransactionHistory());

        BankAccountManager accM1 = new BankAccountManager();
        System.out.println(accM1.addAccount(acc1));
        System.out.println(accM1.addAccount(new SavingsAccount("Test1")));
        BankAccount acc2 = new SavingsAccount("Test account2");
        try {
            System.out.println(acc2.deposit(9999999));
            System.out.println(acc1.deposit(9999999));
            System.out.println(acc1.deposit(90));
            System.out.println(acc1.deposit(100));
            System.out.println(acc1.deposit(500));
        } catch (AccountFrozenException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (InvalidAmountException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        System.out.println(accM1.addAccount(acc2));
        System.out.println();
        System.out.println();
        System.out.println();
        System.out.println();
        // acc1.getTransactionHistory().forEach(System.out::println);

        System.out.println(accM1.filterTransactionsAtOrAbove(500,
                acc1.getTransactionHistory()));
        // accM1.listAccounts();
    }
}
