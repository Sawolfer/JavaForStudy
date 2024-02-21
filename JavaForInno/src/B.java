//Ponomarev Savva
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class Knapsack {

    static Scanner sc;

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        int n = sc.nextInt();
        int w = sc.nextInt();
        int[] weights = new int[n];
        int[] costs = new int[n];


        for (int i = 0; i < n; i++) {
            int tmp = sc.nextInt();
            weights[i] = tmp;
        }

        for (int i = 0; i < n; i++) {
            int tmp = sc.nextInt();
            costs[i] = tmp;
        }

        int[][] dp = new int[n + 1][w + 1];
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= w; j++) {
                if (weights[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weights[i - 1]] + costs[i - 1]);
                }
            }
        }

        int weight = w;
        List<Integer> items = new ArrayList<>();
        for (int i = n; i > 0; i--) {
            if (dp[i][weight] != dp[i - 1][weight]) {
                items.add(i);
                weight -= weights[i - 1];
            }
        }
        System.out.println(items.size());
        for (int i = items.size()-1; i >= 0; i--) {
            System.out.print(items.get(i)+ " ");
        }
/**
 * the algorithm was taken from wiki https://ru.wikipedia.org/wiki/Задача_о_рюкзаке#Динамическое_программирование_над_ценностями_предметов
 */
    }
}