package JavaForInno;
import java.util.Scanner;

public class Solution {

    static Scanner sc;

    public String mergeAlternately(String word1, String word2) {
        String answer = "";

        for (int i = 0; i < word1.length() ; i++) {
            if (i <= word2.length() - 1) {
                answer += word1.charAt(i);
                answer += word2.charAt(i);
            } else {
                answer += word1.charAt(i);
            }
        }
        if (word2.length() > word1.length()) {
            answer += word2.substring(word1.length());
        }
        return answer;
    }

    public static void main(String[] args) {
        sc = new Scanner(System.in);
        String input = sc.nextLine();
        String input2 = sc.nextLine();
        Solution solution = new Solution();
        System.out.println(solution.mergeAlternately(input, input2));
    }
}