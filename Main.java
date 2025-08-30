import java.util.Scanner;

public class Main {
    static Scanner sc;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        Bank bank = new Bank(sc);
        User user = new User(sc);
        ATM atm = new ATM(sc);
        Card card = null;
        int choice;
        boolean exit = false;
        while(!exit){
            System.out.println("\n=============== Welcome to Simple Bank and ATM System Project ===============\n");
            System.out.println("1. Visit Bank");
            System.out.println("2. Go to ATM");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            try{
                String val = sc.nextLine().trim();
                choice = Integer.parseInt(val);
                //card = callOption(choice, user, bank, atm, card);
            }
            catch(NumberFormatException e){
                System.out.println("Choice must be digit.");
                break;
            }
            switch(choice){
                case 1: card = callHelper(user, bank, atm, card);
                        break;
                case 2: if(card == null){
                            System.out.println("No card found. Please first get card by visiting bank.");
                        }
                        else
                        {
                            user.accessATM(atm, card);
                        }
                        break;
                case 3: System.out.println("=============== Exiting Successfully ===============");
                        exit = true;
                        break;
                default: System.out.println("Enter one option between 1-3 only.");
                        break;
            }
        }
    }
    private static Card callHelper(User user, Bank bank, ATM atm, Card card)
    {
        int choice = -1;
        boolean exit = false;
        BankAccount account = null;
        while(!exit){
            System.out.println("\n\n=============== Banking System Menu ===============");
            System.out.println("-----------------------------------------");
            System.out.println("1. Open Bank Account");
            System.out.println("2. Request to get Debit Card");
            System.out.println("3. Balance Enquiry");
            System.out.println("4. Request to unblock Debit Card");
            System.out.println("5. Exit Bank");
            System.out.println("-----------------------------------------\n");
            System.out.print("Enter option (1 - 5): ");
            try{
                String val = sc.nextLine().trim();
                choice = Integer.parseInt(val);
            }
            catch(NumberFormatException e){
                System.out.println("Choice must be digit.");
            }
            switch(choice){
                case 1: account = user.openAccount(bank);
                        break;
                case 2: card = user.requestToGetDebitCard();
                        break;
                case 3: user.requestToCheckBalance();
                        break;
                case 4: user.requestToUnblockDebitCard(card);
                        break;
                case 5: System.out.println("Thank you for visiting Bank. Exiting Bank Successfully");
                        exit = true;
                        break;
                default: System.out.println("Enter one option between 1-5 only.");
                        break;
            }
        }
        return card;
    }
}
