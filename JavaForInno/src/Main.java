import java.util.ArrayList;
import java.util.List;
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
        StringBuilder tmp = new StringBuilder();
        generateCombinations(alphabet, tmp, L1, 0, 4);

        for (String words : L1){
            System.out.println(words);
        }
    }
    public static void generateCombinations(ArrayList<String> inputArray, StringBuilder currentCombination, List <String> output, int start, int k) {
        if (k == 0) {
            output.add(currentCombination.toString());
            return;
        }

        for (int i = start; i < inputArray.size(); i++) {
            currentCombination.append(inputArray.get(i));
            generateCombinations(inputArray, currentCombination, output, i, k - 1);
            currentCombination.deleteCharAt(currentCombination.length() - 1);
        }
    }


}