package tink;

import java.util.Scanner;

public class B {
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int n, m;
        n = sc.nextInt();
        m = sc.nextInt();
        int[][] Img = new int[n][m];
        int[][] newImg = new int[m][n];

        for (int i = 0; i < n; i++) {
            for (int j = 0; j < m; j++) {
                int tempVal = sc.nextInt();
                Img[i][j] = tempVal;
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                newImg[i][j] = Img[j][i];
            }
        }
        for (int i = 0; i < m; i++) {
            for (int j = n-1; j >= 0; j--) {
                System.out.print(newImg[i][j] + " ");
            }
            System.out.println();
        }

    }
}
