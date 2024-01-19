import java.util.Scanner;

public class Palindrom {
    public static Scanner sc;
    public static void main(String[] args){
        sc = new Scanner(System.in);
        int currentNumber = sc.nextInt();
        System.out.println(isPalindrome(currentNumber));
    }


    public static boolean isPalindrome(int x) {
        String strNumber = Integer.toString(x);
        String revString = new StringBuilder(strNumber).reverse().toString();
        if(strNumber.compareTo(revString) == 0){
            return true;
        }
        return false;
    }
}
