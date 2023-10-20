package task1;

import java.sql.Statement;

public class Calculator {
    public static float calculator(int a, String operand, int b){

        switch (operand){
            case "+":
                return a+b;
            case "-":
                return  a-b;
            case "*":
                return a*b;
            case "/":
                if ( b==0){
                    return -1;
                }
                return a/b;
            default:
                return 0;
        }
    }
}
