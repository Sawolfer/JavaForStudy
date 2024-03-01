import java.util.*;

public class A {

    static Scanner sc;
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

        if (outputCounts[0] > 0 && !alphabet.isEmpty()) {
            L1.add("_");
            for (int i = 2; i < (outputCounts[0] * 2); i+=2) {
                if (L1.size() == outputCounts[0]) break;
                List<String> tmp = generate(i);
                for (String word : tmp) {
                    if (L1.size() == outputCounts[0]) break;
                    if (word.length() % 2 == 0 && firstCheck(word)) {
                        L1.add(word);
                    }
                }
            }
        }
        if (outputCounts[1] > 1 && !alphabet.isEmpty()){
            for ( int i = alphabet.size(); i < (outputCounts[1] + 1); i++){
                if (L2.size() == outputCounts[1]) break;
                List<String> tmp = generate(i);
                for (String word : tmp){
                    if (L2.size() == outputCounts[1]) break;
                    if (secondCheck(word)){
                        L2.add(word);
                    }
                }
            }
        }
        if (alphabet.size() <= 1 ) {
            for (int i = 0; i < outputCounts[2]; i++){
                L3.add("_");
            }
        }
        if (outputCounts[2] > 0 && alphabet.size()>1){
            for ( int i = (alphabet.size()-1); i < (outputCounts[2] + 1); i++){
                if (L3.size() == outputCounts[2]) break;
                List<String> tmp = generate(i);
                for (String word : tmp){
                    if (L3.size() == outputCounts[2]) break;
                    if (thirdCheck(word)){
                        L3.add(word);
                    }
                }
            }
        }
        if (alphabet.isEmpty()) {
            for (int i = 0; i < outputCounts[1]; i++){
                L2.add("_");
            }
            for (int i = 0; i < outputCounts[0]; i++){
                L1.add("_");
            }
        }

        for (String words : L1){
            System.out.print(words + " ");
        }
        System.out.print("\n");
        for (String words : L2){
            System.out.print(words + " ");
        }
        System.out.print("\n");
        for (String words : L3){
            System.out.print(words + " ");
        }
    }

    public static boolean firstCheck(String word){
        int[] letters = new int[alphabet.size()];
        for (int i = 0; i < word.length(); i++){
            String letter = Character.toString(word.charAt(i));
            letters[alphabet.indexOf(letter)] += 1;
        }
        for (int item : letters){
            if (item%2!=0){
                return false;
            }
        }
        return true;
    }

    public static boolean secondCheck(String word){
        int[] letters = new int[alphabet.size()];
        for (int i = 0; i < word.length(); i++){
            String letter = Character.toString(word.charAt(i));
            letters[alphabet.indexOf(letter)] += 1;
        }
        for (int item : letters){
            if (item == 0){
                return false;
            }
        }
        return true;
    }
    public static boolean thirdCheck(String word){
        int[] letters = new int[alphabet.size()];
        for (int i = 0; i < word.length(); i++){
            String letter = Character.toString(word.charAt(i));
            letters[alphabet.indexOf(letter)] += 1;
        }
        int count = 0;
        for (int item : letters){
            if (item == 0){
                count +=1;
            }
        }
        if (count == 1){
            return true;
        }
        return false;
    }

    public static ArrayList<String> generate(int length) {
        ArrayList<String> tmp = new ArrayList<>();
        wordGenerator(length, "", tmp);
        return tmp;
    }

    public static void wordGenerator(int length, String word, List<String> tmp) {

        if (word.length() == length){
            tmp.add(word);
            return;
        }

        for (int i = 0; i < alphabet.size(); i++) {
            String letter = alphabet.get(i);
            wordGenerator(length, word + letter, tmp);
        }

    }



}