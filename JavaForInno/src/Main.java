import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Scanner;


public class Main {

    static Scanner sc;
    static Type type;
    static ArrayList<String> states;
    static ArrayList<String> alphabet;
    static String initial;
    static ArrayList<String> accepting;
    static String[][][] transitions;
    static boolean isNonDet = false;

    static FSA fsa;

    static File file = new File("input.txt");

    public static void main(String[] args) {
        int lines = 0;
        try (BufferedReader reader = new BufferedReader(new FileReader("input.txt"))) {
            while (reader.readLine() != null) lines++;
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (lines != 6){
            System.out.println("E1: Input file is malformed");
            return;
        }

        try (FileReader fr = new FileReader(file)) {
            int counter = 0;
            sc = new Scanner(fr);
            String inputLine;
            while (sc.hasNextLine()) {
                inputLine = sc.nextLine();
                String[] input = inputLine.split("=");
                // System.out.println(input[1]);
                if (input[1].substring(input[1].length()-2, input[1].length()).equals(",]")){
                    System.out.println("E1: Input file is malformed");
                    return;
                }
                input[1] = input[1].substring(input[1].indexOf("[") + 1, input[1].indexOf("]"));
                switch (input[0]) {
                    case "type":
                        if(counter!=0){
                            System.out.println("E1: Input file is malformed");
                            return;
                        }
                        type =  Type.DETERMINISTIC;
                        if (input[1].equals("non-deterministic")){
                            type = type.NON_DETERMINISTIC;
                        }
                        counter++;
                        break;
                    case "states":
                        if(counter!=1){
                            System.out.println("E1: Input file is malformed");
                            return;
                        }
                        if (input[1].equals("")){
                            System.out.println("E1: Input file is malformed");
                            return;
                        }
                        String[] strStates = input[1].split(",");
                        states = new ArrayList<>();
                        for (int j = 0; j < strStates.length; j++) {
                            if (states.contains(strStates[j])){continue;}
                            states.add(strStates[j]);
                        }
                        // Collections.sort(states);
                        counter++;
                        break;
                    case "alphabet":
                        if(counter!=2){
                            System.out.println("E1: Input file is malformed");
                            return;
                        }
                        if (input[1].equals("")){
                            System.out.println("E1: Input file is malformed");
                            return;
                        }
                        String[] strAlphabet = input[1].split(",");
                        alphabet = new ArrayList<>();
                        for (int j = 0; j < strAlphabet.length; j++) {
                            if (alphabet.contains(strAlphabet[j])){continue;}
                            alphabet.add(strAlphabet[j]);
                        }
                        counter++;
                        break;
                    case "initial":
                        if(counter!=3){
                            System.out.println("E1: Input file is malformed");
                            return;
                        }    
                        initial = input[1];
                        if (initial.length() == 0){
                            System.out.println("E2: Initial state is not defined");
                            return;
                        }
                        if (!states.contains(initial)){
                            System.out.println("E4: A state '" + initial + "' is not in the set of states");
                            return;
                        }
                        counter++;
                        break;
                    case "accepting":
                        if(counter!=4){
                            System.out.println("E1: Input file is malformed");
                            return;
                        }
                        String[] strAccepting = input[1].split(",");
                        accepting = new ArrayList<>();
                        if (input[1].length() == 0){
                            System.out.println("E3: Set of accepting states is empty");
                            return;
                        }
                        for (int j = 0; j < strAccepting.length; j++) {
                            if (accepting.contains(strAccepting[j])){continue;}
                            accepting.add(strAccepting[j]);
                            if (!states.contains(strAccepting[j])){
                                System.out.println("E4: A state '" + strAccepting[j] + "' is not in the set of states");
                                return;
                            }
                        }
                        Collections.sort(accepting, Comparator.comparing(item -> states.indexOf(item)));
                        counter++;
                        break;
                    case "transitions":
                        if(counter!=5){
                            System.out.println("E1: Input file is malformed");
                            return;
                        }
                        String[] strTransitions = input[1].split(",");
                        transitions = new String[states.size()][states.size()][0];
                        for (int j = 0; j < strTransitions.length; j++) {
                            String[] strTransition = strTransitions[j].split(">");

                            if (strTransition.length != 3){
                                System.out.println("E1: Input file is malformed");
                                return;
                            }
                            
                            if (!states.contains(strTransition[0])){
                                System.out.println("E4: A state '" + strTransition[0] + "' is not in the set of states");
                                return;
                            }

                            if (!states.contains(strTransition[2])){
                                System.out.println("E4: A state '" + strTransition[2] + "' is not in the set of states");
                                return;
                            }
                            if (strTransition[1].equals("")){
                                System.out.println("E1: Input file is malformed");
                                return;
                            }
                            if(!alphabet.contains(strTransition[1])){
                                System.out.println("E5: A transition '" + strTransition[1] + "' is not represented in the alphabet");
                                return;
                            }

                            int indFrom = states.indexOf(strTransition[0]);
                            int indTo = states.indexOf(strTransition[2]);
                            boolean isTransitionExists = false;
                            for (int k = 0; k < transitions[indFrom][indTo].length; k++){
                                if (transitions[indFrom][indTo][k].equals(strTransition[1])){
                                    isTransitionExists = true;
                                    System.out.println("E1: Input file is malformed");
                                    return;
                                }
                            }
                            if (type == Type.DETERMINISTIC){
                                for (int k = 0; k < transitions[indFrom].length; k++){
                                    for (int l = 0; l < transitions[indFrom][k].length; l++){
                                        if (transitions[indFrom][k][l].equals(strTransition[1])){
                                            isNonDet = true;
                                            break;
                                        }
                                    }
                                }
                            }

                            transitions[indFrom][indTo] = Arrays.copyOf(transitions[indFrom][indTo], transitions[indFrom][indTo].length + 1);
                            transitions[indFrom][indTo][transitions[indFrom][indTo].length - 1] = strTransition[1];
                            
                            
                        }
                        break;
                    default:
                        System.out.println("E1: Input file is malformed");
                        return;
                    }
                }
            } catch (Exception e) {
                System.out.println("E1: Input file is malformed");
                return;
            }

            
            
            boolean[] visited = new boolean[states.size()];
            for (int j = 0; j < visited.length; j++){
                visited[j] = false;
            }
            if (!DFS(states.indexOf(initial), visited)){
                System.out.println("E6: Some states are disjoint");
                return;
            }
            

            if (type == Type.DETERMINISTIC && isNonDet){
                System.out.println("E7: FSA is non-deterministic");
                return;
            }

            fsa = new FSA(states, alphabet, initial, accepting, transitions);
            fsa.toRegExp(fsa.toAnswer(fsa.toR(), 1));

    }

    public static boolean DFS(int i, boolean[] visited){
        visited[i] = true;
        for (int k = 0; k < transitions[i].length; k++){
            if (transitions[i][k].length != 0){
                if (!visited[k]){
                    DFS(k, visited);
                }
            } else {continue;}
        }

        for (int j = 0; j < visited.length; j++){
            if (!visited[j]){;
                return false;
            }
        }
        return true;
    }

}



enum Type{
    DETERMINISTIC ("deterministic"),
    NON_DETERMINISTIC ("non-deterministic");

    public final String typeString;

    Type(final String typeString){
        this.typeString  = typeString;
        
    }
}

class FSA{
    private ArrayList<String> states;
    private ArrayList<String> alphabet;
    private String initial;
    private ArrayList<String> accepting;
    private String[][][] transitions;

    public FSA(){}

    public FSA(ArrayList<String> states, ArrayList<String> alphabet, String initial, ArrayList<String> accepting, String[][][] transitions){
        this.states = states;
        this.alphabet = alphabet;
        this.initial = initial;
        this.accepting = accepting;
        this.transitions = transitions;
    }

    public String[][] toR(){
        String [][]R = new String[states.size()][states.size()];
        for (int i = 0; i < transitions.length; i++){
            for (int j = 0; j < transitions.length; j++){
                if (i!=j){
                    if (transitions[i][j].length == 0){
                        R[i][j] = "({})";
                    } else {
                        R[i][j] = "(" + transitions[i][j][0];
                        for (int k = 1; k < transitions[i][j].length; k++){
                            R[i][j] = R[i][j] + "|" + transitions[i][j][k];
                        }
                        R[i][j] = R[i][j] + ")";
                    }
                } else if( i==j ){
                    if (transitions[i][j].length == 0){
                        R[i][j] = "(eps)";
                    } else {
                        R[i][j] = "(" + transitions[i][j][0];
                        for (int k = 1; k < transitions[i][j].length; k++){
                            R[i][j] = R[i][j] + "|" + transitions[i][j][k];
                        }
                        R[i][j] = R[i][j] + "|eps)";
                    }
                }
            }
        }
        return R;
    }

    public String[][] toAnswer(String[][] R, int k){
        String [][] answer = new String[states.size()][states.size()];
        for (int i = 0; i < R.length; i++){
            for (int j = 0; j < R.length; j++){
                // System.out.println(R[i][j]);
                answer[i][j] = "(" + R[i][k-1] +  R[k-1][k-1] + "*" + R[k-1][j] + "|" + R[i][j] + ")";
            }
        }

        if (k == states.size()){
            return answer;
        } else {
            return toAnswer(answer, k + 1);
        }
    }

    public String toRegExp(String[][] answer){
        
        StringBuilder sb = new StringBuilder();
        int indexInit = states.indexOf(initial);
        for(int  i = 0; i < accepting.size(); i++){
            int indexAccept = states.indexOf(accepting.get(i));
            sb.append(answer[indexInit][indexAccept]);
            if (i == accepting.size() - 1){
                break;
            } else if (accepting.size() > 1){
                sb.append("|");
            }
        }
        
        System.out.println(sb.toString());
        return sb.toString();
    }


}


