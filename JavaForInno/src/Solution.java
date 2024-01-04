import java.util.Scanner;

class Solution {
    static Scanner sc = new Scanner(System.in);
    static int maxAns = 0;
    public static void main(String args[]){
        String str = sc.nextLine();

        for (int i=1; i<(str.length()-1); i++){
            String tmpString = str.substring(i);
            int currentLength = lengthOfLongestSubstring(tmpString);
            maxAns = Math.max(maxAns, currentLength);

        }

        System.out.println(maxAns);
    }


    public static int lengthOfLongestSubstring(String s) {
        int answer =0;
        for (int i = 0; i < (s.length()-1); i++ ){
            String next = s.substring(i, i+1);
            if (s.substring(0, i).contains(next)){
                answer = i;
                break;
            }
            else {
                answer++;
            }
        }

        return answer;
    }
}