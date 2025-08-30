import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public abstract class BankAccount {
    protected String userName;
    protected String accountNumber;
    protected double balance;
    protected String cardNumber;
    private List<String> transactions = new ArrayList<>();

    // Method to set the balance
    public void setBalance(double balance) {
        this.balance = balance;
    }

    // Method to get the balance
    public double getBalance() {
        return balance;
    }

    // To return Bank Account Number
    public String getAccountNumber() {
        return accountNumber;
    }

    // Method to return userName
    public String getUserName() {
        return userName;
    }

    // Method to get the list of transactions
    public List<String> getTransactions() {
        return transactions;
    }

    // Abstract method named withdrawal
    public abstract void withdrawal(double amount);

    // Method to deposit amount by the user
    public void deposit(double amount) {
        try {
            if (amount <= 0) {
                throw new EnteredNegativeAmount("Amount must be positive. Please check the value.");
            }
        } catch (EnteredNegativeAmount e) {
            System.out.println(e.getMessage());
            return;
        }
        double total = this.getBalance() + amount;
        this.setBalance(total);
        System.out.println("Deposit successful.");
        Transaction t = new Transaction();
        String time = getTime();
        t.type = "Deposit";
        t.amt = amount;
        t.timeStamp = time;
        t.addTransaction();
    }

    public void checkBalance() {
        System.out.println("Current balance: " + getBalance());
    }

    // A method to get time in string format
    protected String getTime() {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String time = now.format(formatter);
        return time;
    }

    class Transaction {
        String type;
        double amt;
        String timeStamp;

        public void addTransaction() {
            transactions.add(this.toString());
        }

        public String toString() {
            return type + " " + amt + " from " + accountNumber + " by " + userName + " at " + timeStamp;
        }
    }
}
