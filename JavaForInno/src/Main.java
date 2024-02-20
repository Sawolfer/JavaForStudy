//Ponomarev Savva
import java.util.ArrayList;
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
        dp[0]=1;
        for(int i = 0; i < countLetters+1; i++){

            for (String word : inputWords){
                if((i + word.length()) <= countLetters && dp[i] == 1 && inputString.substring(i, i + word.length()).equals(word)){
                    dp[i+word.length()] = 1;
//                    lastPos = i;
                }
            }
        }
        ArrayList<String> answer = new ArrayList<>();
        int end = dp.length-1;
        for (int i = dp.length-1; i >= 0; i--){
            for(String word : inputWords){
                if ( dp[i] == 1 && dp[end] == 1 && inputString.substring(i, end).equals(word)){
                    answer.add(word);
                    end = i;
                }
            }
        }

        for(int i = answer.size()-1; i >= 0; i--){
            System.out.print(answer.get(i) + " ");
        }

    }
}

