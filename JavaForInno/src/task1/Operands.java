package task1;

import java.util.Scanner;


public class Operands {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        int a = s.nextInt();
        String operand = s.next();
        int b = s.nextInt();
        float answer = Calculator.calculator(a, operand, b);


        System.out.print(answer);
//        System.out.printf(operand);
//        System.out.print(b);

    }
}
