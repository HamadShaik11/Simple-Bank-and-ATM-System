public class Card {
    private String cardNumber;
    private String accountNumber;
    private String userName;
    private String pin;
    private int attempts;
    private boolean isPinSet = false;
    private boolean isDebitCardBlocked = false;

    Card(String accountNumber, String userName, String cardNumber) {
        this.accountNumber = accountNumber;
        this.userName = userName;
        this.cardNumber = cardNumber;
    }

    public String getUserName() {
        return this.userName;
    }

    public String getAccountNumber() {
        return this.accountNumber;
    }

    public boolean isPinSet() {
        return isPinSet;
    }

    public void setPin(String pin) {
        this.pin = pin;
        isPinSet = true;
    }

    public int getAttempts() {
        return attempts;
    }

    public void increaseAttempts() {
        attempts = attempts + 1;
    }

    public void resetAttempts() {
        attempts = 0;
        isDebitCardBlocked = false;
    }

    public String getCardNumber() {
        return cardNumber;
    }

    public String getPin() {
        return pin;
    }

    public boolean isCardBlocked() {
        return isDebitCardBlocked;
    }

    public void blockCard() {
        isDebitCardBlocked = true;
    }
}
