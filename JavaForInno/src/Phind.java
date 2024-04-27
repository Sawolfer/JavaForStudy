// import java.util.*;

// class FSA {
//     enum Type {
//         DETERMINISTIC, NON_DETERMINISTIC
//     }

//     Type type;
//     Set<String> states;
//     Set<String> alphabet;
//     String initialState;
//     Set<String> acceptingStates;
//     Map<String, Map<String, Set<String>>> transitions;

//     public FSA(Type type, Set<String> states, Set<String> alphabet, String initialState, Set<String> acceptingStates, Map<String, Map<String, Set<String>>> transitions) {
//         this.type = type;
//         this.states = states;
//         this.alphabet = alphabet;
//         this.initialState = initialState;
//         this.acceptingStates = acceptingStates;
//         this.transitions = transitions;
//     }

//     // Simplified method to convert FSA to regex
//     public String toRegex() {
//         StringBuilder regex = new StringBuilder();
//         for (String symbol : alphabet) {
//             Set<String> nextStates = transitions.get(initialState).get(symbol);
//             if (nextStates.contains(initialState)) {
//                 regex.append("(").append(symbol).append(")*");
//             } else {
//                 regex.append(symbol);
//             }
//         }
//         return regex.toString();
//     }

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);

//         System.out.println("Enter FSA type (DETERMINISTIC/NON_DETERMINISTIC):");
//         String typeInput = scanner.nextLine();
//         Type type = Type.valueOf(typeInput.toUpperCase());

//         System.out.println("Enter states (comma-separated):");
//         Set<String> states = new HashSet<>(Arrays.asList(scanner.nextLine().split(",")));

//         System.out.println("Enter alphabet (comma-separated):");
//         Set<String> alphabet = new HashSet<>(Arrays.asList(scanner.nextLine().split(",")));

//         System.out.println("Enter initial state:");
//         String initialState = scanner.nextLine();

//         System.out.println("Enter accepting states (comma-separated):");
//         Set<String> acceptingStates = new HashSet<>(Arrays.asList(scanner.nextLine().split(",")));

//         System.out.println("Enter transitions (format: state>symbol>state, separated by commas):");
//         Map<String, Map<String, Set<String>>> transitions = new HashMap<>();
//         String[] transitionInputs = scanner.nextLine().split(",");
//         for (String transitionInput : transitionInputs) {
//             String[] parts = transitionInput.split(">");
//             String state = parts[0];
//             String symbol = parts[1];
//             String nextState = parts[2];
//             transitions.computeIfAbsent(state, k -> new HashMap<>()).computeIfAbsent(symbol, k -> new HashSet<>()).add(nextState);
//         }

//         FSA fsa = new FSA(type, states, alphabet, initialState, acceptingStates, transitions);
//         System.out.println(fsa.toRegex());

//         scanner.close();
//     }
// }
