import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Scanner;


public class Main  {

    static Scanner sc;
    public static void main(String[] args) throws IOException{

        FileReader input = new FileReader("src/input.txt");
        FileWriter output = new FileWriter("src/output.txt");

        sc = new Scanner(System.in);
        for (int i = 0; i< 13; i++){
            int n = sc.nextInt();
            System.out.print((n*n - 2*n + 7)%13 + " ");
        }

    }
}


//TODO strategy pattern - behavioral

class BankAccount{

    double transactionFee;

    public BankAccount(){};



}

class SavingsAccount extends BankAccount{

    public SavingsAccount(){
        transactionFee = 1.5;
    };
}

class CheckingAccount extends BankAccount{

    public CheckingAccount(){
        transactionFee = 2;
    };
}

class BusinessAccount extends BankAccount{

    public BusinessAccount(){
        transactionFee = 2.5;
    };
}


//TODO Implement Errors such as one of pattern Facade - Structural

class ThrowError{

    
    public void throwErrorstring (String error, String name){
        switch (error) {
            case "AccountDoesNotExist":
                ErrorAccountDoesNotExist errorAccountDoesNotExist = new ErrorAccountDoesNotExist();
                try{ errorAccountDoesNotExist.error(name);
                } catch (IOException e){}
                break;
            case "AccountInActive":
                ErrorAccountInActive errorAccountInActive = new ErrorAccountInActive();
                try{ errorAccountInActive.error(name);
                } catch (IOException e){}
                break;
            case "AccoutInsuffisienFounds":
                ErrorAccoutInsuffisienFounds errorAccoutInsuffisienFounds = new ErrorAccoutInsuffisienFounds();
                try{ errorAccoutInsuffisienFounds.error(name);
                } catch (IOException e){}
                break;
            case "AccountIsAlreadyActivated":
                ErrorAccountIsAlreadyActivated errorAccountIsAlreadyActivated = new ErrorAccountIsAlreadyActivated();
                try{ errorAccountIsAlreadyActivated.error(name);
                } catch (IOException e){}
                break;
            case "AccountIsAlreadyDeactivated":
                ErrorAccountIsAlreadyDeactivated errorAccountIsAlreadyDeactivated = new ErrorAccountIsAlreadyDeactivated();
                try{ errorAccountIsAlreadyDeactivated.error(name);
                } catch (IOException e){}
                break;
            default:
                break;
        }
    }
}

class ErrorAccountDoesNotExist extends IOException{
    public void error(String name) throws IOException{
        FileWriter output = new FileWriter("src/output.txt");
        output.write("Error: Account " + name + " does not exist");
        output.close();
    }
}

class ErrorAccountInActive extends IOException{
    public void error(String name) throws IOException{
        FileWriter output = new FileWriter("src/output.txt");
        output.write("Error: Account " + name + " is inactive");
        output.close();
    }
}

class ErrorAccoutInsuffisienFounds extends IOException{
    public void error(String name) throws IOException{
        FileWriter output = new FileWriter("src/output.txt");
        output.write("Error: Insufficient funds for " + name);
        output.close();
    }
}

class ErrorAccountIsAlreadyActivated extends IOException{
    public void error(String name) throws IOException{
        FileWriter output = new FileWriter("src/output.txt");
        output.write("Error: Account " + name + " is already activated");
        output.close();
    }
}

class ErrorAccountIsAlreadyDeactivated extends IOException{
    public void error(String name) throws IOException{
        FileWriter output = new FileWriter("src/output.txt");
        output.write("Error: Account " + name + " is already deactivated");
        output.close();
    }
}
