public class CurrentAccount extends BankAccount {
    CurrentAccount(String name, String accountNumber, double balance) {
        this.userName = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    private final double draftLimit = 10000;

    public void withdrawal(double amount) {
        try {
            if (amount < 0) {
                throw new EnteredNegativeAmount("Amount must be positive. Please check the value.");
            }
        } catch (EnteredNegativeAmount e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            if (balance + draftLimit < amount) {
                throw new InsufficientBalance("Insufficient funds for this withdrawal.");
            } else {
                balance = balance - amount;
                Transaction t = new Transaction();
                String time = getTime();
                t.type = "Withdrawal";
                t.amt = amount;
                t.timeStamp = time;
                t.addTransaction();
                System.out.println("Withdrawal successful.");
            }
        } catch (InsufficientBalance e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
