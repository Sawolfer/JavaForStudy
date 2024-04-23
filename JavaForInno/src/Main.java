import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

/**
 * The Main class that initializes the bank system and executes the program.
 */
public class Main {

    static Scanner sc;
    /**
     * The main function that executes the banking system. It creates a BankSystem object,
     * reads input from the user, and performs various operations on the bank accounts based
     * on the user's input.
     *
     * @param  args  the command-line arguments passed to the program
     */
    public static void main(String[] args){

        BankSystem bankSystem = new BankSystem();

        sc = new Scanner(System.in);
        int count = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < count; i++){
            String line = sc.nextLine();
            String[] words = line.split(" ");
            switch (words[0]) {
                case "Create":
                    bankSystem.CreateAccount(words[3], Double.parseDouble(words[4]), words[2]);
                    break;
                case "Deposit":
                    bankSystem.DepositAccount(words[1], Double.parseDouble(words[2]));
                    break;
                case "Withdraw":
                    bankSystem.WithdrawAccount(words[1], Double.parseDouble(words[2]));
                    break;
                case "Transfer":
                    bankSystem.TransferAccount(words[1], words[2], Double.parseDouble(words[3]));
                    break;
                case "View":
                    bankSystem.ViewAccount(words[1]);
                    break;
                case "Activate":
                    bankSystem.ActivateAccount(words[1]);
                    break;
                case "Deactivate":
                    bankSystem.DeactivateAccount(words[1]);
                    break;
                default:
                    break;
            }
        }
    }
}

// Singleton pattern - creational

/**
 * Represents a banking system that manages accounts, transactions, and customers.
 */
class BankSystem{

    ThrowError error = new ThrowError();
    Map<String, BankAccount> accounts = new HashMap<>();

    public BankSystem(){}

    /**
     * Creates a new bank account based on the given type and adds it to the accounts map.
     *
     * @param  name   the name of the account holder
     * @param  sum    the initial balance of the account
     * @param  type   the type of the account (Savings, Checking, Business)
     */
    public void CreateAccount(String name, double sum, String type){
        switch (type) {
            case "Savings":
                SavingsAccount account = new SavingsAccount(sum, name);
                accounts.put(name, account);
                break;
            case "Checking":
                CheckingAccount account1 = new CheckingAccount(sum, name);
                accounts.put(name, account1);
                break;
            case "Business":
                BusinessAccount account2 = new BusinessAccount(sum, name);
                accounts.put(name, account2);
                break;
            default:
                break;
        }
    }

    /**
     * Transfer the specified sum from one account to another if conditions are met.
     *
     * @param  name   the name of the source account holder
     * @param  name2  the name of the target account holder
     * @param  sum    the amount to transfer
     */
    public void TransferAccount(String name, String name2, double sum){
        if (!accounts.containsKey(name)){
            error.setStrategy(new ErrorAccountDoesNotExist());
            error.throwErrorString(name);
        } else {
            if (!accounts.containsKey(name2)){
                error.setStrategy(new ErrorAccountDoesNotExist());
                error.throwErrorString(name2);
            } else if (!accounts.get(name).isActive()) {
                error.setStrategy(new ErrorAccountInActive());
                error.throwErrorString(name);
            } else if (accounts.get(name).sum < sum){
                error.setStrategy(new ErrorAccoutInsuffisientFounds());
                error.throwErrorString(name);
            } else {
                accounts.get(name).transfer(sum, accounts.get(name2));
            }
        }
    }

    /**
     * Deposits the specified amount into the account with the given name.
     *
     * @param  name   the name of the account holder
     * @param  sum    the amount to deposit
     */
    public void DepositAccount(String name, double sum){
        if (!accounts.containsKey(name)){
            error.setStrategy(new ErrorAccountDoesNotExist());
            error.throwErrorString(name);
        } else {
            accounts.get(name).deposit(sum);
        }
    }

    /**
     * Withdraws the specified amount from the account with the given name.
     *
     * @param  name   the name of the account holder
     * @param  sum    the amount to withdraw
     */
    public void WithdrawAccount(String name, double sum){
        if (!accounts.containsKey(name)){
            error.setStrategy(new ErrorAccountDoesNotExist());
            error.throwErrorString(name);
        } else if(!accounts.get(name).isActive()) {
            error.setStrategy(new ErrorAccountInActive());
            error.throwErrorString(name);
        } else {
            if (accounts.get(name).sum < sum){
                error.setStrategy(new ErrorAccoutInsuffisientFounds());
                error.throwErrorString(name);
            } else {
                accounts.get(name).withdraw(sum);
            }
        }
    }

    /**
     * A function that views the account based on the given name.
     *
     * @param  name   the name of the account holder
     */
    public void ViewAccount(String name){
        if (!accounts.containsKey(name)){
            error.setStrategy(new ErrorAccountDoesNotExist());
            error.throwErrorString(name);
        } else {
            accounts.get(name).view();
        }
    }

    /**
     * Activates the account with the given name if it exists and is not already active.
     *
     * @param  name  the name of the account to activate
     */
    public void ActivateAccount(String name){
        if (!accounts.containsKey(name)){
            error.setStrategy(new ErrorAccountDoesNotExist());
            error.throwErrorString(name);
        } else {
            if (accounts.get(name).isActive()){
                error.setStrategy(new ErrorAccountIsAlreadyActivated());
                error.throwErrorString(name);
            } else {
                accounts.get(name).activate();
            }
        }
    }

    /**
     * Deactivates the account with the given name if it exists and is currently active.
     *
     * @param  name  the name of the account to deactivate
     */
    public void DeactivateAccount(String name){
        if (!accounts.containsKey(name)){
            error.setStrategy(new ErrorAccountDoesNotExist());
            error.throwErrorString(name);
        } else {
            if (!accounts.get(name).isActive()){
                error.setStrategy(new ErrorAccountIsAlreadyDeactivated());
                error.throwErrorString(name);
            } else {
                accounts.get(name).deactivate();
            }
        }
    }

}

//Decorator pattern - structural

/**
 * BankAccountVariables is an abstract class that contains common variables and methods for all bank accounts.
 */
abstract class BankAccountVariables {
    double transactionFee = 0.0;
    double sum;
    String name;
    String type = "Null";
    boolean active = true;
    List<String> operations = new ArrayList<>();
}
/**
 * BankAccount is a parent class which represent all bank account actions and extends variables from BankAccountVariables
 */
class BankAccount extends BankAccountVariables {

    public BankAccount(){};

    /**
     * Constructs a new bank account with the specified initial balance and name.
     *
     * @param  sum   the initial balance
     * @param  name  the name of the account holder
     */
    public BankAccount(double sum, String name) {
        this.sum = sum;
        this.name = name;
        this.type = getClass().getName();
        type = type.substring(0, type.length()-"Account".length());
        String message = String.format("A new %s account created for %s with an initial balance of $%.3f.", type, name, sum);
        System.out.println(message);
        operations.add(String.format("Initial Deposit $%.3f", sum));
    }

    /**
     * Returns the active status of the object.
     *
     * @return  true if the object is active, false otherwise
     */
    public boolean isActive(){
        return active;
    }


    /**
     * Withdraws the specified amount from the bank account.
     *
     * @param  amount  the amount to withdraw
     */
    public void withdraw(double amount) {
        sum -= amount;
        String message = String.format("%s successfully withdrew $%.3f. ", name, amount - transactionFee * amount/100);
        message += String.format("New Balance: $%.3f. ", sum);
        message += String.format("Transaction Fee: $%.3f (%.1f%%) in the system. ", transactionFee * amount/100, transactionFee);
        System.out.println(message);
        operations.add(String.format("Withdrawal $%.3f", amount));
    }

    /**
     * Deposits the specified amount into the bank account.
     *
     * @param  amount  the amount to deposit (double)
     */
    public void deposit(double amount){
        sum += amount;
        String message = String.format("%s successfully deposited $%.3f.", name, amount);
        message += String.format(" New Balance: $%.3f.", sum);
        System.out.println(message);
        operations.add(String.format("Deposit $%.3f", amount));
    }

    /**
     * Transfers the specified amount from this bank account to the target account.
     *
     * @param  amount        the amount to transfer
     * @param  targeAccount  the target bank account to transfer the amount to
     */
    public void transfer(double amount, BankAccount targeAccount) {
        sum -= amount;
        targeAccount.sum += (amount - transactionFee * amount/100);
        String message = String.format("%s successfully transferred $%.3f to %s.", name, amount - transactionFee * amount/100, targeAccount.name);
        message += String.format(" New Balance: $%.3f. ", sum);
		message += String.format("Transaction Fee: $%.3f (%.1f%%) in the system. ", transactionFee * amount/100, transactionFee);
        System.out.println(message);
        operations.add(String.format("Transfer $%.3f", amount));
    }

    /**
     * Prints a formatted message displaying the account details of the given account.
     */
    public void view(){
        String message = String.format("%s's Account: ", name);
        message += String.format("Type: %s, ", type);
        message += String.format("Balance: $%.3f, ", sum);
        message += String.format("State: %s, ", active ? "Active" : "Inactive");
        message += ("Transactions: [");

        for (int i = 0; i < operations.size(); i++){
            message +=  operations.get(i);
            if (i != operations.size() - 1){
                message += ", ";
            }
        }
        message += "].";
        System.out.println(message);
    }

    /**
     * Deactivates the account by setting the active status to false.
     */
    public void deactivate() {
        active = false;
        String message = String.format("%s's account is now deactivated.", name);
        System.out.println(message);
    }

    /**
     * Activates the account. Prints a message indicating that the account is now activated.
     */
    public void activate(){
        active = true;
        String message = String.format("%s's account is now activated.", name);
        System.out.println(message);
    }

}
/**
 * SavingsAccount is a subclass of BankAccount which represents a savings account.
 */
class SavingsAccount extends BankAccount{

    public SavingsAccount(){
        transactionFee = 1.5;
    }

    /**
     * Creates a new savings account with the given initial balance and name.
     *
     * @param sum
     * @param name
     */
    public SavingsAccount(double sum, String name) {
        super(sum, name);
        transactionFee = 1.5;
        type = "Savings";
    }
}

/**
 * CheckingAccount is a subclass of BankAccount which represents a checking account.
 */
class CheckingAccount extends BankAccount{

    public CheckingAccount(){
        transactionFee = 2;
    }
    /**
     * Creates a new checking account with the given initial balance and name.
     *
     * @param sum
     * @param name
     */
    public CheckingAccount(double sum, String name) {
        super(sum, name);
        transactionFee = 2;
        type = "Checking";
    }
}

/**
 * BusinessAccount is a subclass of BankAccount which represents a business account.
 */
class BusinessAccount extends BankAccount{

    public BusinessAccount(){
        transactionFee = 2.5;
    }
    /**
     * Creates a new business account with the given initial balance and name.
     *
     * @param sum
     * @param name
     */
    public BusinessAccount(double sum, String name) {
        super(sum, name);
        transactionFee = 2.5;
        type = "Business";
    }
}


// Strategy pattern - behavioral

/**
 * ThrowError class that implements the ErrorHandlingStrategy interface.
 * It throws an error message based on the error type and name of the account.
 */
class ThrowError {
    private ErrorHandlingStrategy strategy;

    /**
     * Sets the error handling strategy.
     *
     * @param strategy
     */
    public void setStrategy(ErrorHandlingStrategy strategy) {
        this.strategy = strategy;
    }

    /**
     * Throws an error message based on the error type and name of the account.
     *
     * @param name
     */

    public void throwErrorString(String name) {
        strategy.handle(name);
    }
}

/**
 * Interface for the error handling strategy.
 */
interface ErrorHandlingStrategy {
    void handle(String name);
}

// ErrorAccountDoesNotExist class implementing ErrorHandlingStrategy
class ErrorAccountDoesNotExist implements ErrorHandlingStrategy {

    /**
     * Prints an error message indicating that the account does not exist.
     */
    @Override
    public void handle(String name) {
        System.out.println("Error: Account " + name + " does not exist.");
    }
}

// ErrorAccountInActive class implementing ErrorHandlingStrategy
class ErrorAccountInActive implements ErrorHandlingStrategy {
    /**
     * Prints an error message indicating that the account is inactive.
     */
    @Override
    public void handle(String name) {
        System.out.println("Error: Account " + name + " is inactive.");
    }
}

// ErrorAccoutInsuffisientFounds class implementing ErrorHandlingStrategy
class ErrorAccoutInsuffisientFounds implements ErrorHandlingStrategy {
    /**
     * Prints an error message indicating that the account has insufficient funds.
     */
    @Override
    public void handle(String name) {
        System.out.println("Error: Insufficient funds for " + name + ".");
    }
}

// ErrorAccountIsAlreadyActivated class implementing ErrorHandlingStrategy
class ErrorAccountIsAlreadyActivated implements ErrorHandlingStrategy {
    /**
     * Prints an error message indicating that the account is already activated.
     */
    @Override
    public void handle(String name) {
        System.out.println("Error: Account " + name + " is already activated.");
    }
}

// ErrorAccountIsAlreadyDeactivated class implementing ErrorHandlingStrategy
class ErrorAccountIsAlreadyDeactivated implements ErrorHandlingStrategy {
    /**
     * Prints an error message indicating that the account is already deactivated.
     */
    @Override
    public void handle(String name) {
        System.out.println("Error: Account " + name + " is already deactivated.");
    }
}
