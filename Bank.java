import java.util.HashSet;
import java.util.Random;
import java.util.Scanner;
import java.util.Set;

public class Bank {
    Scanner sc;
    private static Set<String> allAccountNumbers = new HashSet<>();
    private static Set<String> allCardNumbers = new HashSet<>();
    private Random rand = new Random();
    private BankAccountAndCardRegistry linker = BankAccountAndCardRegistry.getInstance();

    Bank(Scanner sc) {
        this.sc = sc;
    }

    // Method to generate Random number based on the given digits i.e if digits is 6
    // then 6 digit random number will be generated...
    private String getRandomNumber(double digits) {
        long min = (long) Math.pow(10.0, digits - 1);
        long max = (long) Math.pow(10.0, digits) - 1;
        long number = min + (Math.abs(rand.nextLong()) % (max - min + 1));
        return String.valueOf(number);
    }

    // This isValidUserOfDebitCard() method tells the user is valid user for debit
    // card or not...
    private boolean isValidUserOfDebitCard(Card c) {
        System.out.print("Please enter your full name for verification: ");
        String name = sc.nextLine();
        name = name.trim();
        if (name.equals(c.getUserName())) {
            return true;
        }
        return false;
    }

    // This isValidUserOfAccount() method tells the user is valid user for account
    // or not...
    private boolean isValidUserOfAccount(BankAccount account) {
        System.out.print("Please enter your full name for verification: ");
        String name = sc.nextLine();
        name = name.trim();
        if (name.equals(account.getUserName())) {
            return true;
        }
        return false;
    }

    protected BankAccount createBankAccount(String name, String accountType, double initialBalance) {
        BankAccount account;
        String accountNumber = "2025" + getRandomNumber(6);
        while (allAccountNumbers.contains(accountNumber)) {
            accountNumber = "2025" + getRandomNumber(6);
        }
        allAccountNumbers.add(accountNumber);
        if (accountType.equals("savings")) {
            account = new SavingsAccount(name, accountNumber, initialBalance);
        } else {
            account = new CurrentAccount(name, accountNumber, initialBalance);
        }
        linker.mapAccount(accountNumber, account);
        System.out.println("Account created successfully.");
        System.out.println("Bank Account number is "+accountNumber);
        return account;
    }

    protected Card issueDebitCard(String accountNumber, String userName) {
        String cardNumber = "96" + getRandomNumber(10);
        while (allCardNumbers.contains(cardNumber)) {
            cardNumber = "96" + getRandomNumber(10);
        }
        allCardNumbers.add(cardNumber);
        Card c = new Card(accountNumber, userName, cardNumber);
        linker.mapCardAndAccount(cardNumber, accountNumber);
        System.out.println("Debit card issued successfully.");
        return c;
    }

    protected void unblockDebitcard(Card c) {
        try {
            if (c != null) {
                try {
                    if (isValidUserOfDebitCard(c)) {
                        c.resetAttempts();
                        System.out.println("Card unblocked successfully.");
                        System.out.println("Card Number is "+c.getCardNumber());
                    } else {
                        throw new InvalidUser("Name does not match card owner.");
                    }
                } catch (InvalidUser e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new InvalidDebitCard("Invalid debit card provided.");
            }
        } catch (InvalidDebitCard idc) {
            System.out.println(idc.getMessage());
        }
    }

    public void balanceEnquiry(BankAccount account) {
        try {
            if (account != null) {
                try {
                    if (isValidUserOfAccount(account)) {
                        System.out.println("Current balance: " + account.getBalance());
                    } else {
                        throw new InvalidUser("Name does not match account owner.");
                    }
                } catch (InvalidUser e) {
                    System.out.println(e.getMessage());
                }
            } else {
                throw new InvalidAccount("No account found. Please open an account first.");
            }
        } catch (InvalidAccount ia) {
            System.out.println(ia.getMessage());
        }

    }
}
