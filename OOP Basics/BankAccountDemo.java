class BankAccount {
    double balance;

    void deposit(double amount) {
        balance +=amount;
    }

    void withdraw(double amount) {
        if (balance >= amount) {
            balance -= amount;
        } else {
            System.out.println("Insufficient funds."); 
        }
    }

    void checkBalance() {
        System.out.println("Account Balance: $" + balance);
    }
}

public class BankAccountDemo {
    public static void main(String[] args) {
        BankAccount account = new BankAccount();
        account.balance = 1000.0;
        account.deposit(500.00);
        account.withdraw(200.0);
        account.checkBalance();
    }
}