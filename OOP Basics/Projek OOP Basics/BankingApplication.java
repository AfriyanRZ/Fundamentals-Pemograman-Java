class BankAccount {
    private double balance;

    public BankAccount(String accountNumber, double balance, Customer owner) {
        this.balance = balance;
    }

    public void deposit(double amount) {
        balance += amount;
    }

    public void withdraw(double amount) throws InsufficientFundsException {
        if (balance < amount) {
            throw new InsufficientFundsException("Insufficient funds in account.");
        }
        balance -= amount;
    }

    public double getBalance() {
        return balance;
    }
}

class Customer {

    public Customer(String name, String address, String phoneNumber) {
    }
}

class InsufficientFundsException extends Exception {
    public InsufficientFundsException(String message) {
        super(message);
    }
}

public class BankingApplication {
    public static void main(String[] args) {
        Customer customer = new Customer("John Doe", "123 Main St", "555-1234");
        BankAccount account = new BankAccount("12345678", 1000.0, customer);

        account.deposit(500.0);
        try {
            account.withdraw(800.0);
        } catch (InsufficientFundsException e) {
            System.out.println(e.getMessage());
        }

        System.out.println("Current Balance: $" + account.getBalance());
    }
}