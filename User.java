import java.util.Scanner;

public class User {
    Scanner sc;
    private String name;
    private String accountType; // Savings or Current
    private String accType;
    private double initialBalance;
    private Bank bank; //
    private String accountNumber;
    private BankAccount account;
    private Card card;
    private boolean debitCardIssued = false;

    User(Scanner sc) {
        this.sc = sc;
    }

    public BankAccount openAccount(Bank bank) {
        if(account == null){
            this.bank = bank;
            System.out.print("Full name: ");
            name = sc.nextLine();
            boolean valid = false;
            while (!valid) {
                System.out.print("Account type (Savings/Current): ");
                this.accountType = sc.nextLine();
                accType = this.accountType.toLowerCase();
                try {
                    if (!(accType.equals("savings")) && !(accType.equals("current"))) {
                        throw new InvalidAccountType("Invalid account type. Enter 'Savings' or 'Current'.");
                    } else {
                        valid = true;
                    }
                } catch (InvalidAccountType e) {
                    System.out.println(e.getMessage());
                }
            }
            valid = false;
            while (!valid) {
                System.out.print("Initial balance: ");
                try{
                    String amt = sc.nextLine().trim();
                    this.initialBalance = Double.parseDouble(amt);
                }
                catch(NumberFormatException nfe){
                    System.out.println("Please enter a valid numeric amount for initial balance.");
                    return null;
                }
                try {
                    if (initialBalance <= 0) {
                        throw new InvalidInitialBalance("Initial balance must be a positive amount.");
                    }
                    valid = true;
                } catch (InvalidInitialBalance e) {
                    System.out.println(e.getMessage());
                }
            }
            account = bank.createBankAccount(this.name, this.accType, this.initialBalance);
            accountNumber = account.accountNumber;
            
        }
        else{
            System.out.println("You have created account already");
        }
        return account;
    }

    public Card requestToGetDebitCard() {
        try {
            if (account != null) {
                try {
                    if (debitCardIssued) {
                        throw new DebitCardIssuedAlready("A debit card has already been issued for this account.");
                    } else {
                        card = bank.issueDebitCard(this.accountNumber, this.name);
                        debitCardIssued = true;
                        return card;
                    }
                } catch (DebitCardIssuedAlready cis) {
                    System.out.println(cis.getMessage());
                    return card;
                }
            } else {
                throw new InvalidAccount("No account found. Please open an account first.");
            }
        } catch (InvalidAccount e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

    public void requestToUnblockDebitCard(Card c) {
        try {
            if (account == null) {
                throw new InvalidAccount("No account found. Please open an account first.");
            } else {
                try {
                    if (c != null) {
                        bank.unblockDebitcard(c);
                    } else {
                        throw new InvalidDebitCard("Invalid debit card provided.");
                    }
                } catch (InvalidDebitCard idc) {
                    System.out.println(idc.getMessage());
                }
            }
        } catch (InvalidAccount e) {
            System.out.println(e.getMessage());
        }
    }

    public void accessATM(ATM atm, Card c) {

        try {
            if (atm == null) {
                System.out.println("ATM not available at the moment.");
                return;
            }
            if (c != null) {
                atm.helper(c);
            } else {
                throw new InvalidDebitCard("Invalid debit card provided.");
            }
        } catch (InvalidDebitCard idc) {
            System.out.println(idc.getMessage());
        }
    }
    
    public void requestToCheckBalance(){
        try{
            if (account == null) {
                throw new InvalidAccount("No account found. Please open an account first.");
            }
            else{
                bank.balanceEnquiry(account);
            }
        }
        catch(InvalidAccount iva){
            System.out.println(iva.getMessage());
        }
    }
}
