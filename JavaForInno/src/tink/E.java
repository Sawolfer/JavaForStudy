package tink;

import java.util.Arrays;
import java.util.Scanner;

public class E {
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int n;
        n = sc.nextInt();
        String[][] forest = new String[n][3];
        for (int i = 0; i < n; i++) {
            String[] temp = sc.next().split("");
            for (int j = 0; j < 3; j++) {
                forest[i][j] = temp[j];
            }
        }
        int[] value = new int[3];
        value[0] = walk(forest, 0, 0, 0);
        value[1] = walk(forest, 0, 1, 0);
        value[2] = walk(forest, 0, 2, 0);
        System.out.println(Arrays.stream(value).max().getAsInt());

    }

    public static int walk(String[][] forest, int i, int j, int count) {
        int [] value = new int[3];
        if (forest[i][j].equals("W")){
            return count;
        }
        if (forest[i][j].equals("C")){
            count++;
        }
        if (i == forest.length-1){
            return count;
        }
        
        if (j == 0){
            value[0] = walk(forest, i+1, j, count);
            value[1] = walk(forest, i+1, j+1, count);
        }
        if (j == 1){
            value[0] = walk(forest, i+1, j-1, count);
            value[1] = walk(forest, i+1, j, count);
            value[2] = walk(forest, i+1, j+1, count);
        }
        if (j == 2){
            value[0] = walk(forest, i+1, j-1, count);
            value[1] = walk(forest, i+1, j, count);
        }
        return Arrays.stream(value).max().getAsInt();
    }
}
