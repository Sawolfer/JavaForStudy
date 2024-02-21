import java.util.Scanner;

class Knapsack {

    Scanner sc;

    public void main(String[] args){
        sc = new Scanner(System.in);

        int n = sc.nextInt();
        int w = sc.nextInt();
        int[] weights = new int[n];
        int[] costs = new int[n];
        

        for (int i = 0; i<n; i++){
            int tmp = sc.nextInt();
            weights[i] = tmp;
        }

        for (int i = 0; i<n; i++){
            int tmp = sc.nextInt();
            costs[i] = tmp;
        }
    
        int[][] K = new int[n+1][n+1];
        K[0][0]= 0;
        for (int i = 1; i <=n; i++){
            for (int j = 1; j <= n; j++){
                if (weights[i-1] <= j){
                    K[i][j] = Math.max(costs[i-1] + K[i-1][j - weights[i - 1]], K[i-1][j]);
                }
                else {
                    K[i][j] = K[i-1][j];
                }
            }
        }


    }



}
