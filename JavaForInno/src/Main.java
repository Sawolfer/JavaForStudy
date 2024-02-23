import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc;
    static int index = 0;
    static ArrayList<String> alphabet = new ArrayList<>();
    public static void main(String[] args) {


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

        for (int i = 0; i <= 4; i++){
            String tmp = generateCombinations();
            L1.add(tmp);
        }

        for (String words : L1){
            System.out.println(words);
        }
    }
    public static String generateCombinations() {

        StringBuilder word = new StringBuilder();
        int tmp = index;

        while (tmp > 0){
            word.append(alphabet.get(tmp % alphabet.size()));
            tmp = tmp / alphabet.size();
        }
        index +=1;
        return word.toString();
    }


}