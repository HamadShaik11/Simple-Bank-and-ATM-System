import java.util.List;
import java.util.Scanner;

public class ATM {
    private Scanner sc;
    private BankAccountAndCardRegistry accountAndCard;

    ATM(Scanner sc) {
        this.sc = sc;
        accountAndCard = BankAccountAndCardRegistry.getInstance();
    }

    public void helper(Card c) {
        int times = 0;
        if (c.isPinSet() == false) {
            System.out.println("PIN not set. Please set a 6-digit PIN to use this card.");
            while (times < 3) {
                System.out.print("Would you like to set your PIN now? (yes/no): ");
                String check = sc.nextLine().trim();
                check = check.toLowerCase();
                if (check.equals("yes")) {
                    System.out.print("Enter a 6-digit PIN: ");
                    String pin = sc.nextLine();
                    pin = pin.trim();
                    try{
                        int value = Integer.parseInt(pin);
                        if(value < 0){
                            throw new NumberFormatException();
                        }
                    }
                    catch(NumberFormatException e){
                        System.out.println("PIN must contain digits only.");
                        continue;
                    }
                    if (pin.length() != 6) {
                        System.out.println("PIN must be exactly 6 digits.");
                        times++;
                        continue;
                    } else {
                        c.setPin(pin);
                        System.out.println("PIN setup completed.");
                        break;
                    }
                } else {
                    System.out.println("PIN setup skipped. You can set it later at the ATM.");
                    break;
                }
            }
        } else {
            authenticateUser(c);
        }
    }

    public void authenticateUser(Card c) {
        if (!c.isCardBlocked()) {
            while (c.getAttempts() < 3) {
                System.out.print("Enter pin: ");
                String cpin = sc.nextLine().trim();
                if (cpin.equals(c.getPin())) {
                    c.resetAttempts();
                    showMenu(c);
                    return;
                } else {
                    System.out.println("Incorrect PIN. Please try again.");
                    c.increaseAttempts();
                }
            }
            c.blockCard();
            System.out.println("Your card has been temporarily blocked. Please try again later or contact the bank.");
        } else {
            try {
                throw new InvalidDebitCard("This card is blocked. Please visit your bank to unblock it.");
            } catch (InvalidDebitCard idc) {
                System.out.println(idc.getMessage());
            }
        }
    }

    public void showMenu(Card c) {
        System.out.println(
                "1. withdrawal\n2. Deposit\n3. Check Balance\n4. Update Pin\n5. View Transaction History\n6. Exit");
        System.out.print("Choose an option (1-6): ");
        String opt = sc.nextLine().trim();
        try {
            int option = Integer.parseInt(opt);
            BankAccount account = accountAndCard.getAccountViaCard(c);
            performOperation(option, c, account);
        } catch (Exception e) {
            System.out.println("Invalid selection. Please enter a number between 1 and 6.");
        }
    }

    public void performOperation(int choice, Card c, BankAccount account) {
        double amount;
        if (account == null) {
            System.out.println("No account linked to this card. Please contact your bank.");
            return;
        }
        switch (choice) {
            case 1:
                System.out.print("Amount to withdraw: ");
                try{
                    String amt = sc.nextLine();
                    amount = Double.parseDouble(amt);

                }
                catch(NumberFormatException nfe){
                    System.out.println("Please enter a valid numeric amount.");
                    return;
                }
                account.withdrawal(amount);
                break;
            case 2:
                System.out.print("Amount to deposit: ");
                try{
                    String amt = sc.nextLine();
                    amount = Double.parseDouble(amt);

                }
                catch(NumberFormatException nfe){
                    System.out.println("Please enter a valid numeric amount.");
                    return;
                }
                account.deposit(amount);
                break;
            case 3:
                account.checkBalance();
                break;
            case 4:
                updateCardPin(c);
                break;
            case 5:
                List<String> trnxs = account.getTransactions();
                int len = trnxs.size();
                int count = 0;
                for (int i = len - 1; i >= 0; i--) {
                    System.out.println(trnxs.get(i));
                    count++;
                    if (count == 5) {
                        break;
                    }
                }
                break;
            case 6:
                System.out.println("Session ended. Please take your card.");
                break;
            default:
                System.out.println("Invalid option. Please try again.");
                break;

        }
    }

    public void updateCardPin(Card c) {
        System.out.print("Enter new 6-digit PIN: ");
        String pin = sc.nextLine();
        pin = pin.trim();
        if (pin.length() != 6) {
            System.out.println("PIN must be exactly 6 digits.");
            return;
        } else {
            try {
                int value = Integer.parseInt(pin);
                c.setPin(pin);
                System.out.println("PIN updated successfully.");
            } catch (NumberFormatException nfe) {
               System.out.println("PIN must contain digits only.");
            }
        }
    }

}
