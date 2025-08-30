public class SavingsAccount extends BankAccount {
    SavingsAccount(String name, String accountNumber, double balance) {
        this.userName = name;
        this.accountNumber = accountNumber;
        this.balance = balance;
    }

    public void withdrawal(double amount) {
        try {
            if (amount < 0) {
                throw new EnteredNegativeAmount("You entered negative amount, Please check it ....");
            }
        } catch (EnteredNegativeAmount e) {
            System.out.println(e.getMessage());
            return;
        }
        try {
            if (balance < amount) {
                throw new InsufficientBalance("Insufficient Balance.....");
            } else {
                balance = balance - amount;
                Transaction t = new Transaction();
                String time = getTime();
                t.type = "Withdrawal";
                t.amt = amount;
                t.timeStamp = time;
                t.addTransaction();
                System.out.println("Amount is withdrawal successfully....");
            }
        } catch (InsufficientBalance e) {
            System.out.println(e.getMessage());
            return;
        }
    }
}
