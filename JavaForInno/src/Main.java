import java.io.*;
import java.lang.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;


public class Main  {

    static Scanner sc;
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

//TODOsingleton pattern - creativial bankSystem

class BankSystem{

    ThrowError error = new ThrowError();
    Map<String, BankAccount> accounts = new HashMap<>();

    public BankSystem(){}

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

    public void TransferAccount(String name, String name2, double sum){
        if (!accounts.containsKey(name)){
            error.throwErrorstring("AccountDoesNotExist", name);
        } else {
            if (!accounts.containsKey(name2)){
                error.throwErrorstring("AccountDoesNotExist", name2);
            } else if (!accounts.get(name).isActive()) {
                error.throwErrorstring("AccountInActive", name);
            } else if (accounts.get(name).sum < sum){
                error.throwErrorstring("AccoutInsuffisientFounds", name);
            } else {
                accounts.get(name).transfer(sum, accounts.get(name2));
            }
        }
    }

    public void DepositAccount(String name, double sum){
        if (!accounts.containsKey(name)){
            error.throwErrorstring("AccountDoesNotExist", name);
        } else {
            accounts.get(name).deposit(sum);
        }
    }

    public void WithdrawAccount(String name, double sum){
        if (!accounts.containsKey(name)){
            error.throwErrorstring("AccountDoesNotExist", name);
        } else if(!accounts.get(name).isActive()) {
            error.throwErrorstring("AccountInActive", name);
        } else {
            if (accounts.get(name).sum < sum){
                error.throwErrorstring("AccoutInsuffisientFounds", name);
            } else {
                accounts.get(name).withdraw(sum);
            }
        }
    }

    public void ViewAccount(String name){
        if (!accounts.containsKey(name)){
            error.throwErrorstring("AccountDoesNotExist", name);
        } else {
            accounts.get(name).view();
        }
    }

    public void ActivateAccount(String name){
        if (!accounts.containsKey(name)){
            error.throwErrorstring("AccountDoesNotExist", name);
        } else {
            if (accounts.get(name).isActive()){
                error.throwErrorstring("AccountIsAlreadyActivated", name);
            } else {
                accounts.get(name).activate();
            }
        }
    }
    public void DeactivateAccount(String name){
        if (!accounts.containsKey(name)){
            error.throwErrorstring("AccountDoesNotExist", name);
        } else {
            if (!accounts.get(name).isActive()){
                error.throwErrorstring("AccountIsAlreadyDeactivated", name);
            } else {
                accounts.get(name).deactivate();
            }
        }
    }

}

//TODO strategy pattern - behavioral

abstract class BankAccountVariables {
    double transactionFee = 0.0;
    double sum;
    String name;
    String type = "Null";
    boolean active = true;
    List<String> operations = new ArrayList<>();
}

class BankAccount extends BankAccountVariables {

    public BankAccount(){};

    public BankAccount(double sum, String name) {
        this.sum = sum;
        this.name = name;
        this.type = getClass().getName();
        type = type.substring(0, type.length()-"Account".length());
        String message = String.format("A new %s account created for %s with an initial balance of $%.3f.", type, name, sum);
        System.out.println(message);
        operations.add(String.format("Initial Deposit $%.3f", sum));
    }

    public boolean isActive(){
        return active;
    }


    public void withdraw(double amount) {
        sum -= amount;
        String message = String.format("%s successfully withdrew $%.3f. ", name, amount - transactionFee * amount/100);
        message += String.format("New Balance: $%.3f. ", sum);
        message += String.format("Transaction Fee: $%.3f (%.1f%%) in the system. ", transactionFee * amount/100, transactionFee);
        System.out.println(message);
        operations.add(String.format("Withdrawal $%.3f", amount));
    }

    public void deposit(double amount){
        sum += amount;
        String message = String.format("%s successfully deposited $%.3f.", name, amount);
        message += String.format(" New Balance: $%.3f.", sum);
        System.out.println(message);
        operations.add(String.format("Deposit $%.3f", amount));
    }

    public void transfer(double amount, BankAccount targeAccount) {
        sum -= amount;
        targeAccount.sum += (amount - transactionFee * amount/100);
        String message = String.format("%s successfully transferred $%.3f to %s.", name, amount - transactionFee * amount/100, targeAccount.name);
        message += String.format(" New Balance: $%.3f. ", sum);
		message += String.format("Transaction Fee: $%.3f (%.1f%%) in the system. ", transactionFee * amount/100, transactionFee);
        System.out.println(message);
        operations.add(String.format("Transfer $%.3f", amount));
    }

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
    
    public void deactivate() {
        active = false;
        String message = String.format("%s's account is now deactivated.", name);
        System.out.println(message);
    }

    public void activate(){
        active = true;
        String message = String.format("%s's account is now activated.", name);
        System.out.println(message);
    }

}

class SavingsAccount extends BankAccount{

    public SavingsAccount(){
        transactionFee = 1.5;
    }
    
    
    public SavingsAccount(double sum, String name) {
        super(sum, name);
        transactionFee = 1.5;
        type = "Savings";
    }
}

class CheckingAccount extends BankAccount{

    public CheckingAccount(){
        transactionFee = 2;
    }
    public CheckingAccount(double sum, String name) {
        super(sum, name);
        transactionFee = 2;
        type = "Checking";
    }
}

class BusinessAccount extends BankAccount{

    public BusinessAccount(){
        transactionFee = 2.5;
    }
    public BusinessAccount(double sum, String name) {
        super(sum, name);
        transactionFee = 2.5;
        type = "Business";
    }
}


//TODO Implement Errors such as one of pattern Facade - Structural

class ThrowError{

    public void throwErrorstring (String error, String name){
        switch (error) {
            case "AccountDoesNotExist":
                ErrorAccountDoesNotExist errorAccountDoesNotExist = new ErrorAccountDoesNotExist();
                errorAccountDoesNotExist.error(name);
                break;
            case "AccountInActive":
                ErrorAccountInActive errorAccountInActive = new ErrorAccountInActive();
                errorAccountInActive.error(name);
                break;
            case "AccoutInsuffisientFounds":
                ErrorAccoutInsuffisientFounds errorAccoutInsuffisientFounds = new ErrorAccoutInsuffisientFounds();
                errorAccoutInsuffisientFounds.error(name);
                break;
            case "AccountIsAlreadyActivated":
                ErrorAccountIsAlreadyActivated errorAccountIsAlreadyActivated = new ErrorAccountIsAlreadyActivated();
                errorAccountIsAlreadyActivated.error(name);
                break;
            case "AccountIsAlreadyDeactivated":
                ErrorAccountIsAlreadyDeactivated errorAccountIsAlreadyDeactivated = new ErrorAccountIsAlreadyDeactivated();
                errorAccountIsAlreadyDeactivated.error(name);
                break;
            default:
                break;
        }
    }
}

class ErrorAccountDoesNotExist {
    public void error(String name) {
        System.out.println("Error: Account " + name + " does not exist.");
    }
}

class ErrorAccountInActive {
    public void error(String name) {
        System.out.println("Error: Account " + name + " is inactive.");
    }
}

class ErrorAccoutInsuffisientFounds {
    public void error(String name) {
        System.out.println("Error: Insufficient funds for " + name + ".");
    }
}

class ErrorAccountIsAlreadyActivated {
    public void error(String name) {
        System.out.println("Error: Account " + name + " is already activated.");
    }
}

class ErrorAccountIsAlreadyDeactivated {
    public void error(String name) {
        System.out.println("Error: Account " + name + " is already deactivated.");
    }
}
