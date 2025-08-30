# Simple Bank and ATM System

## Project Overview
This project simulates a simple Bank and ATM system using **Object-Oriented Programming (OOP) concepts in Java**.  
It allows users to create a bank account, request a debit card, set a PIN, and perform ATM transactions.  
The system demonstrates **classes, objects, inheritance, exception handling, and user input handling**.

---

## Features
- **PIN Authentication** with 3 attempts rule
- Supports **Savings and Current Accounts**
- **Overdraft facility** for Current Accounts
- **Transaction History** with timestamps
- **Temporary card blocking** after 3 wrong PIN attempts
- Demonstrates **OOP concepts**: Encapsulation, Inheritance, Composition
- **Console-based menu-driven UI**
- **Single transaction per ATM session** for clarity and simplicity

---

## Tech Stack
- **Language:** Java
- **Core Concepts:** Classes, Objects, Inheritance, Encapsulation, Exception Handling
- **User Input Handling:** Scanner class

---

## Project Structure
- `Main.java` - Entry point of the application with main menu
- `Bank.java` - Handles account creation, debit card issuance, and balance enquiry
- `User.java` - Represents a user and their actions
- `ATM.java` - Handles ATM transactions, PIN setup, and authentication
- `Card.java` - Represents debit card with PIN and blocking functionality
- `BankAccount.java` - Abstract class for bank accounts
- `SavingsAccount.java` - Implements savings account behavior
- `CurrentAccount.java` - Implements current account with overdraft
- `BankAccountAndCardRegistry.java` - Singleton class to link accounts with cards
- Exception classes - Custom exceptions like `InvalidAccountType`, `EnteredNegativeAmount`, `InvalidDebitCard`, etc.

---

## How to Run
1. Clone the repository
2. Open the project in any Java IDE or VS Code
3. Compile all Java files
4. Run `Main.java` to start the simulation
5. Follow console prompts to:
   - Open a bank account
   - Request a debit card
   - Set a PIN
   - Perform ATM transactions (Deposit, Withdraw, Check Balance, View Transaction History)

---
## How It Works
1. **Bank Menu**
   - Open Bank Account (Savings or Current)
   - Request Debit Card
   - Balance Enquiry
   - Request to unblock Debit Card
   - Exit Bank

2. **ATM Menu**
   - Withdrawal
   - Deposit
   - Check Balance
   - Update PIN
   - View last 5 Transactions
   - Exit ATM session

3. **Rules & Validations**
   - PIN must be exactly 6 digits
   - Maximum 3 PIN entry attempts; card gets temporarily blocked after 3 failed attempts
   - Withdrawals cannot exceed balance in Savings Account
   - Current Account allows overdraft up to draft limit
   - Each ATM session allows **one transaction at a time**
   - All inputs are validated for correctness

---

## Security & Exception Handling
- **PIN Setup & Authentication:** Ensures only the correct user accesses the ATM
- **Card Blocking:** Automatically blocks card on repeated incorrect PIN entries
- **Custom Exceptions:** Handle invalid account type, negative balance, invalid debit card, and insufficient funds
- **User Verification:** Ensures that debit card and account actions match the correct user

---

## Future Improvements
- Persistent storage for accounts and cards (file or database)
- GUI interface using JavaFX or Swing
- Support multiple users and multiple accounts per user
- Detailed transaction history and reporting
- Add interest calculation for savings accounts

---

## Tech Stack
- **Language:** Java
- **Concepts Used:** Object-Oriented Programming, Exception Handling, Collections, User Input
- **Development Environment:** VS Code, IntelliJ IDEA, or any Java IDE

---

## Conclusion
This project demonstrates a **fully functional single-user Bank and ATM simulation**.  
It is ideal for practicing **Java OOP concepts, exception handling, and console-based menu design**.  
Each ATM session is limited to **one transaction at a time** to keep the simulation simple and clear.
