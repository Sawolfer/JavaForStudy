
import java.util.Scanner;
import java.lang.StringBuilder;

public class Main {
    static Scanner sc = new Scanner(System.in);
    public static void Print(String message){
        System.out.printf(message + "\n");
    }

    public static void PrintMenu(){
        Print("[0] – exit");
        Print("[1] – print current string");
        Print("[2] – append the string with a string that the user will input");
        Print("[3] – insert a string that the user will input to the current string at a certain index");
        Print("[4] – reverse current string");
        Print("[5] – given indexes l and r delete substring in the range l to r from the current string");
        Print("[6] – given indexes l and r replace substring in the range l to r from the current string with a string that will be given in the input");
    }

    public static void main(String[] args){
        PrintMenu();

        StringBuilder currentString = new StringBuilder("Test");
        boolean working = true;
        while (working){
            String option = sc.nextLine();


            switch (option){
                case ("0"):
                    working = false;
                    return;
                case ("1"):
                    Print(currentString.toString());
                    PrintMenu();
                    break;
                case("2"):
                    String newStringTMP = sc.nextLine();
                    currentString.append(newStringTMP);
                    Print(currentString.toString());
                    PrintMenu();
                    break;
                case ("3"):
                    int location = sc.nextInt();;
                    String newPiece1 = sc.nextLine();

                    currentString.insert(location, newPiece1);
                    Print(currentString.toString());
                    PrintMenu();
                    break;
                case ("4"):
                    currentString.reverse();
                    Print(currentString.toString());
                    PrintMenu();
                case ("5"):
                    int l = sc.nextInt();
                    int r = sc.nextInt();

                    currentString.delete(l, r);
                    Print(currentString.toString());
                    PrintMenu();
                    break;
                case ("6"):
                    int l1 = sc.nextInt();
                    int r1 = sc.nextInt();
                    String newPiece = sc.nextLine();

                    currentString.replace(l1, r1, newPiece);
                    Print(currentString.toString());
                    PrintMenu();
                    break;
                default:
                    Print("Incorrect input. Try another values");
                    PrintMenu();
                    break;
            }


        }
    }


}