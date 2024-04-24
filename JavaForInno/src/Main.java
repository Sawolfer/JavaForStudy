import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

enum type{
    DETERMINISTIC ("deterministic"),
    NON_DETERMINISTIC ("non-deterministic");

    public final String typeString;

    type(String typeString){
        this.typeString  = typeString;
    }
}

class FSA{

    private type type;
    private ArrayList<String> states;
    private ArrayList<String> alphabet;
    private String initial;
    private ArrayList<String> accepting;
    private HashMap<String, ArrayList<TransiotionStatePair<String, String>>> transitions;

    public FSA(){}

    public FSA(type type, ArrayList<String> states, ArrayList<String> alphabet, String initial, ArrayList<String> accepting, HashMap<String, ArrayList<TransiotionStatePair<String, String>>> transitions){
        this.type = type;
        this.states = states;
        this.alphabet = alphabet;
        this.initial = initial;
        this.accepting = accepting;
        this.transitions = transitions;
    }
    


}

class TransiotionStatePair<T, S>{

    T transition;
    S state;

    public TransiotionStatePair(T transition, S state){
        this.transition = transition;
        this.state = state;
    }
}

public class Main {

    static Scanner sc;
    static type type;
    static ArrayList<String> states;
    static ArrayList<String> alphabet;
    static String initial;
    static ArrayList<String> accepting;
    static HashMap<String, ArrayList<TransiotionStatePair<String, String>>> transitions;

    static FSA fsa;

    public static void main(String[] args) {
        sc = new Scanner(System.in);

        for (int i = 0; i < 6; i++) {
            String inputLine = sc.nextLine();
            String[] input = inputLine.split(" ");
            input[1] = input[1].substring(input[1].indexOf("[") + 1, input[1].indexOf("]"));
            switch (input[0]) {
                case "type":
                    type = type.valueOf(input[1]);
                    break;
                case "states":
                    String[] strStates = input[1].split(",");
                    states = new ArrayList<>();
                    for (int j = 0; j < strStates.length; j++) {
                        states.add(strStates[j]);
                    }
                    break;
                case "alphabet":
                    String[] strAlphabet = input[1].split(",");
                    alphabet = new ArrayList<>();
                    for (int j = 0; j < strAlphabet.length; j++) {
                        alphabet.add(strAlphabet[j]);
                    }
                    break;
                case "initial":
                    String initial = input[1];
                    break;
                case "accepting":
                    String accepting = input[1];
                    break;
                case "transitions":
                    String[] strTransitions = input[1].split(",");
                    transitions = new HashMap<>();
                    for (int j = 0; j < strTransitions.length; j++) {
                        String[] strTransition = strTransitions[j].split(">");
                        if (transitions.containsKey(strTransition[0])) {
                            TransiotionStatePair<String, String> transitionStatePair = new TransiotionStatePair<>(strTransition[1], strTransition[2]);
                            transitions.get(strTransition[0]).add(transitionStatePair);
                        } else {
                            ArrayList<TransiotionStatePair<String, String>> transitionStatePairs = new ArrayList<>();
                            TransiotionStatePair<String, String> transitionStatePair = new TransiotionStatePair<>(strTransition[1], strTransition[2]);
                            transitionStatePairs.add(transitionStatePair);
                            transitions.put(strTransition[0], transitionStatePairs);
                        }
                    }
                    break;
                }
            }

        fsa = new FSA(type, states, alphabet, initial, accepting, transitions);

    }

}
