import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class B {
    static Scanner sc;
    static HashMap<String, ArrayList<States>> FSA;
    static int Q, S, F, N;
    static String startState;
    public static void main(String args[]){

        sc = new Scanner(System.in);
        FSA = new HashMap<>();
        Q = sc.nextInt();
        S = sc.nextInt();
        F = sc.nextInt();
        N = sc.nextInt();
        String[] states = new String[Q];
        String[] alphabet = new String[S];
        String[] finalStates = new String[F];
        String[] words = new String[N];
        for (int i = 0; i < Q; i++){
            String tmp = sc.next();
            states[i] = tmp;
        }
        for (int i = 0; i < S; i++){
            String tmp = sc.next();
            alphabet[i] = tmp;
        }
        for (int i = 0; i < (Q*S); i++){
            String[] tmpWords = new String[3];
            for (int j = 0; j < 3; j++){
                String tmp = sc.next();
                tmpWords[j] = tmp;
            }
            States tmp = new States(tmpWords[0], tmpWords[2]);
            if (!FSA.containsKey(tmpWords[1])){
                ArrayList<States> tmpArr = new ArrayList<>();
                FSA.put(tmpWords[1], tmpArr);
            }
            FSA.get(tmpWords[1]).add(tmp);

        }
        startState = sc.next();
        for (int i = 0; i < F; i++){
            String tmp = sc.next();
            finalStates[i] = tmp;
        }
        for (int i = 0; i < N; i++){
            String tmp = sc.next();
            words[i] = tmp;
        }

        for (String word : words){
            if (check(word, finalStates, startState)){
                System.out.print("A ");
            } else {
                System.out.print("R ");
            }
        }

    }

    public static boolean check(String word, String[] finalStates, String currentState){

        if (word.equals("_")){
            for (String state : finalStates){
                if (state.equals(startState)){
                    return true;
                }
            }
            return false;
        }
        if (word.length()==1){
            for(States letter : FSA.get(word)){
                if (letter.getCurrentState().equals(currentState)){
                    for (String state : finalStates){
                        if (letter.getNewState().equals(state)) return true;
                    }
                }
            }
            return false;
        }
        for (States letter : FSA.get(Character.toString(word.charAt(0)))){
            if (letter.getCurrentState().equals(currentState)){
                if (check(word.substring(1, word.length()), finalStates, letter.getNewState())){
                    return true;
                }
            }
        }
        return false;

    }


}
class States{
    String currentState, newState;

    public States(String state, String newState){
        this.currentState = state;
        this.newState = newState;
    }
    public String getCurrentState(){
        return currentState;
    }
    public String getNewState(){
        return newState;
    }
}
