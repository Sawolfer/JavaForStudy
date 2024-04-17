package tink;

import java.util.Scanner;

public class D {
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int n;
        n = sc.nextInt();
        String direction = sc.next();
        int[][] Img = new int[n][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                int tempVal = sc.nextInt();
                Img[i][j] = tempVal;
            }
        }
        if (n==1){
            System.out.println(0);
            return;
        }
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                
            }
        }

    }
}
