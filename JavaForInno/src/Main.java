import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc;
    public static void main(String[] args) {

        ArrayList<String> alphabet = new ArrayList<>();
        int[] outputCounts = new int[3];
        sc = new Scanner(System.in);
        int M = Integer.parseInt(sc.nextLine());
        for (int i = 0; i < M; i++) {
            alphabet.add(sc.next());
        }
        for (int i = 0; i < 3; i++){
            int tmp = Integer.parseInt(sc.next());
            outputCounts[i] = tmp;
        }
        ArrayList<String> L1 = new ArrayList<>();
        ArrayList<String> L2 = new ArrayList<>();
        ArrayList<String> L3 = new ArrayList<>();

        L1.add("_");

        for (int i = 2; i < outputCounts[0]; i++){
            StringBuilder word = new StringBuilder();

            for (String letter : alphabet){
                
            }
        }

        for (String words : L1){
            System.out.println(words);
        }
    }


}