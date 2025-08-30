import java.util.HashMap;

class BankAccountAndCardRegistry {
    private HashMap<String, BankAccount> accountMap = new HashMap<>();
    private HashMap<String, String> cardAndAccountMap = new HashMap<>();

    private BankAccountAndCardRegistry() {
    }

    private static BankAccountAndCardRegistry instance;

    public static BankAccountAndCardRegistry getInstance() {
        if (instance == null) {
            instance = new BankAccountAndCardRegistry();
        }
        return instance;
    }

    public void mapAccount(String accountNumber, BankAccount account) {
        if (!accountMap.containsKey(accountNumber)) {
            accountMap.put(accountNumber, account);
        }
    }

    public void mapCardAndAccount(String cardNumber, String accountNumber) {
        if (!cardAndAccountMap.containsKey(cardNumber)) {
            cardAndAccountMap.put(cardNumber, accountNumber);
        }
    }

    public BankAccount getAccountViaCard(Card c) {
        try {
            if (!c.isCardBlocked()) {
                String cardNumber = c.getCardNumber();
                String accountNumber = cardAndAccountMap.get(cardNumber);
                BankAccount account = accountMap.get(accountNumber);
                return account;
            } else {
                throw new InvalidDebitCard("This card is blocked. Please contact your bank.");
            }
        } catch (InvalidDebitCard idc) {
            System.out.println(idc.getMessage());
            return null;
        }
    }
}
