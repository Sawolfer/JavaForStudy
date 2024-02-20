import java.lang.ref.SoftReference;
import java.util.Scanner;

class RestoringSpaces{

    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);
        int countWords = sc.nextInt();
        int countLetters = sc.nextInt();
        int[] dp = new int[countLetters+1];

        String[] inputWords = new String[countWords];
        for( int i = 0; i < countWords; i++){
            inputWords[i] = sc.next();
        }
        String inputString = sc.next();

        for (int i = 0; i < countLetters+1; i++) {
            dp[i] = 0;
        }
        int lastPos = 0;
        for(int i = 0; i < countLetters+1; i++){

            for (String word : inputWords){
                if((i - word.length() - lastPos) >= 0 && inputString.substring((i - word.length()), i).equals(word)){
                    dp[i] = 1;
                    lastPos = i;
                }
            }
        }
        for (int i = 0; i < inputString.length(); i++){
            if (dp[i] == 0){
                System.out.print(inputString.charAt(i));
            } else {
                System.out.print(" " + inputString.charAt(i));
            }

        }

    }
}

