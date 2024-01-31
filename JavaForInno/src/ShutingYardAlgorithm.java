//Ponomarev Savva

import java.util.*;

public class ShutingYardAlgorithm {

    private static final int SIZE = 200000;
    private static Scanner sc;
    private static CustomStack<String> elements;
    private static CustomStack<String> output;

    public static void main(String args[]) {
        sc = new Scanner(System.in);
        String currentString = sc.nextLine();

        elements = new CustomStack<>(SIZE);
        output = new CustomStack<>(SIZE);

        String[] items = currentString.split(" ");
        for (String item : items) {
            try {
                Integer.parseInt(item);
                output.push(item);
            } catch (NumberFormatException nfe) {
                if (item.equals("max") || item.equals("min")){
                    elements.push(item);
                } else if (item.equals(",")) {
                    while (elements.size()>0 && !(((String)elements.peek()).equals("("))) {
                        output.push(elements.peek());
                        elements.pop();
                    }
                } else if (isOperator(item)){
                    while (elements.size()>0 && isOperator((String) elements.peek()) && ((CompareOperators( ((String)elements.peek()))) >= (CompareOperators(item)))){
                        output.push(elements.peek());
                        elements.pop();
                    }

                    elements.push(item);
                } else if (item.equals("(")){
                    elements.push(item);
                } else if (item.equals(")")){
                    while (elements.size()>0 && !((String)elements.peek()).equals("(")){
                        output.push(elements.peek());
                        elements.pop();
                    }
                    elements.pop();
                    if (elements.size()>0 && (elements.peek().equals("max") || elements.peek().equals("min"))){
                        output.push(elements.peek());
                        elements.pop();
                    }
                }

            }

        }
        if (elements.size() != 0){
            while (elements.size() > 0){
                output.push(elements.peek());
                elements.pop();
            }
        }
        ReversePolishNotation();
//        output.printStack();

    }

    public static boolean isOperator(String a){
        return ( a.equals("+") || a.equals("-") || a.equals("*") || a.equals("/"));
    }
    public static int CompareOperators(String a) {
        if (a.equals("+") || a.equals("-")) return 1;
        else return 2;
    }

    public static void ReversePolishNotation(){
        CustomStack<Integer> digits = new CustomStack<>(SIZE);

        for ( int i = 1; i< output.size()+ 1; i++){
            String item = (String) output.elementAt(i);
            if (!isOperator(item) && !item.equals("min") && !item.equals("max")){
                digits.push(Integer.parseInt(item));
            } else if (item.equals("+")){
                int a =  (int)digits.peek();
                digits.pop();
                int b = (int)digits.peek();
                digits.set(digits.size(), a+b);
            } else if (item.equals("-")){
                int a = (int)digits.peek();
                digits.pop();
                int b = (int)digits.peek();
                digits.set(digits.size(), b-a);
            } else if (item.equals("*")){
                int a = (int)digits.peek();
                digits.pop();
                int b = (int)digits.peek();
                digits.set(digits.size(), a*b);
            } else if (item.equals("/")){
                int a = (int)digits.peek();
                digits.pop();
                int b = (int)digits.peek();
                digits.set(digits.size(), b/a);
            } else if (item.equals("min")){
                int a = (int)digits.peek();
                digits.pop();
                int b = (int)digits.peek();
                digits.set(digits.size(), Math.min(a, b));
            } else if (item.equals("max")){
                int a = (int)digits.peek();
                digits.pop();
                int b = (int)digits.peek();
                digits.set(digits.size(), Math.max(a, b));
            }
        }
        digits.printStack();

    }
}
interface StackOperations <T>{

    Object elementAt(int index);
    int size();
//    boolean isEmpty();
    void push(Object item);
    void pop();
    Object peek();
    void printStack();
    void set(int index, T item);
    Boolean isFull();
}

class CustomStack<T> implements StackOperations<T>{

    private Object[] arr;
    private int top;
    private int capacity;

    public CustomStack(int capacity){
        this.capacity = capacity;
        arr = new Object[capacity];
        top = 0;
    }
    public Object elementAt(int index){
        return arr[index];
    }
    public int size(){
        return top;
    }

    public void set(int index, T item){
        arr[index] = item;
    }
    public void push(Object item){
        arr[++top] = item;
    }
    public void pop(){
        arr[top--] = null;
//        top--;
    }
    public Object peek(){
        return arr[top];
    }
    public Boolean isFull() {
        return top == capacity - 1;
    }

    public void printStack(){
        for(Object item : arr){
            if (item != null && !item.equals(")") && !item.equals("(") ){
                System.out.print(item);
                System.out.print(" ");
            }

        }
    }
/**
 * the shutting yard algorithm was taken from https://ru.wikipedia.org/wiki/Алгоритм_сортировочной_станции
 */
}