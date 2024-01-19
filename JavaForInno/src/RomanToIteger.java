import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;
import java.util.Map;

public class RomanToIteger {

    public static int answer;
    public static Scanner sc;

    public static Map<String, Integer> symbols;
    public static void main(String args[]){
        symbols = new HashMap<String, Integer>(){{
            put("II", 2);
            put("I0", 1);
            put("IV", 4);
            put("V0", 5);
            put("VI", 6);
            put("IX", 9);
            put("X0", 10);
            put("XI", 11);
            put("XL", 40);
            put("LX", 60);
            put("L0", 50);
            put("XC", 90);
            put("CX", 110);
            put("C0", 100);
            put("CD", 400);
            put("DC", 600);
            put("D0", 500);
            put("CM", 900);
            put("MC", 1100);
            put("M0", 1000);

        }};
        sc = new Scanner(System.in);
        String currentString = sc.nextLine();
        if (currentString.length() % 2 != 0) {
            currentString += "0";
        }
        System.out.print(romanToInt(currentString));

    }

    public static int romanToInt(String s) {
        int answer = 0;
        for (int i = 0; i<s.length(); i+=2){
            String key = s.substring(i, i+2);
            answer += symbols.get(key);
        }
        return answer;
    }
}
