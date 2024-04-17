package tink;
import java.util.Scanner;


public class A {
    
    static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        int count = sc.nextInt();
        if (count < 7){
            System.out.println("-1");
            return;
        }

        int[] grades = new int[count];

        int countFive =0;
        for (int i = 0; i < count; i++) {
            int grade = Integer.parseInt(sc.next());
            grades[i] = grade;
        }
        int maxCount = -1;
        for (int i = 0; i < count - 6; i++) {
            countFive = 0;
            for (int j = i; j < i + 7; j++) {
                if (grades[j] == 5){
                    countFive++;
                }
                if (grades[j] == 3 || grades[j] == 2){
                    countFive = -1;
                    break;
                }
            }
            maxCount = Math.max(maxCount, countFive);
        }
        System.out.println(maxCount);

    }

}

