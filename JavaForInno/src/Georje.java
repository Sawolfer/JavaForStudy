// //Made by Selivanov George

// import java.util.*;

// class Main {
//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         int n = scanner.nextInt();
//         int[][] adjacencyMatrix = new int[n][n];

//         for (int i = 0; i < n; i++) {
//             for (int j = 0; j < n; j++) {
//                 adjacencyMatrix[i][j] = scanner.nextInt();
//             }
//         }
//         Graph<Integer, Integer> graph = new Graph<>();
//         for (int i = 0; i < n; i++) {
//             graph.addVertex(i);
//             for (int j = 0; j < n; j++) {
//                 if (adjacencyMatrix[i][j] == 1) {
//                     graph.addEdge(i, j);
//                 }
//             }
//         }
//         boolean isConnected = true;
//         for (int i = 0; i < n; i++) {
//             for (int j = i + 1; j < n; j++) {
//                 if (!graph.isConnected(i, j)) {
//                     isConnected = false;
//                     break;
//                 }
//             }
//         }
//         if (isConnected) {
//             System.out.println("YES");
//         } else {
//             System.out.println("NO");
//         }
//     }
// }
// class Graph<V, E> {
//     private Map<V, List<V>> adjacencyList;

//     public Graph() {
//         adjacencyList = new HashMap<>();
//     }
//     public void addVertex(V vertex) {
//         adjacencyList.put(vertex, new LinkedList<>());
//     }
//     public void addEdge(V source, V destination) {
//         if (!adjacencyList.containsKey(source)) {
//             addVertex(source);
//         }
//         if (!adjacencyList.containsKey(destination)) {
//             addVertex(destination);
//         }
//         adjacencyList.get(source).add(destination);
//         adjacencyList.get(destination).add(source); // Assuming the graph is undirected
//     }
//     public boolean isConnected(V source, V destination) {
//         if (!adjacencyList.containsKey(source) || !adjacencyList.containsKey(destination)) {
//             return false;
//         }
//         Set<V> visited = new HashSet<>();
//         Queue<V> queue = new LinkedList<>();
//         queue.add(source);
//         visited.add(source);

//         while (!queue.isEmpty()) {
//             V current = queue.poll();
//             if (current.equals(destination)) {
//                 return true;
//             }
//             for (V neighbor : adjacencyList.get(current)) {
//                 if (!visited.contains(neighbor)) {
//                     queue.add(neighbor);
//                     visited.add(neighbor);
//                 }
//             }
//         }
//         return false;
//     }
// }