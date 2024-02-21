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
        


    }



}
