import java.util.Scanner;

public class Main {

    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);
        for (int i = 0; i< 13; i++){
            int n = sc.nextInt();
            System.out.print((n*n - 2*n + 7)%13 + " ");
        }

    }
}