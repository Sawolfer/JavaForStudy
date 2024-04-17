// import java.util.*;


// public class DSA {
//     static class Edge {
//         int src, dest, weight;

//         public Edge(int source, int destination, int weight) {
//             this.src = source;
//             this.dest = destination;
//             this.weight = weight;
//         }
//     }

//     static boolean BellmanFordAlgorithm(List<List<Edge>> graph, int vertices) {
//         List<Integer> dist = new ArrayList<>();
//         List<Integer> parent = new ArrayList<>();
//         // List<Boolean> visited = new ArrayList<>();
//         int negativeVertex = -1;
        
//         for (int i = 0; i < vertices; i++) {
//             dist.add(Integer.MAX_VALUE);
//             parent.add(-1);
//             // visited.add(false);
//         }


//         dist.set(0, 0);

//         for (int i = 0; i < vertices - 1; i++) {
//             for (int j = 0; j < vertices; j++) {
//                 for (Edge edge : graph.get(j)) {
//                     int v = edge.dest;
//                     if (dist.get(j) + edge.weight < dist.get(v)) {
//                         dist.set(v, dist.get(j) + edge.weight);
//                         parent.set(v, j);
//                     }
//                 }
//             }
//         }

//         for (int i = 0; i < vertices; i++) {
//             for (Edge edge : graph.get(i)) {
//                 int v = edge.dest;
//                 if (dist.get(i) + edge.weight < dist.get(v)) {
//                     negativeVertex = v;
//                     break;
//                 }
//             }
//         }

//         if (negativeVertex == -1) {
//             System.out.println("NO");
//             return false;
//         }
//         else {
//             List<Integer> negativeCycle = new ArrayList<>();
//             int current = negativeVertex;
//             List<Integer> visited = new ArrayList<>(); 
            
//             do {
//                 if (visited.contains(current)) {
//                     break;
//                 }
//                 negativeCycle.add(current);
//                 visited.add(current);
//                 current = parent.indexOf(current);
//             } while (current != negativeVertex && current != -1); 
            


//             System.out.println("YES");
//             System.out.println(negativeCycle.size());
//             for (int v : negativeCycle) {
//                 System.out.print((v + 1) + " ");                
//             }
//             System.out.println();
            
//             return true;
//         }
//         // } else {
//         //     List<Integer> negativeCycle = new ArrayList<>();
//         //     int current = negativeVertex;

//         //     do {
//         //         negativeCycle.add(current);
//         //         current = parent.indexOf(current);
//         //     } while (current != negativeVertex);

//         //     System.out.println("YES");
//         //     System.out.println(negativeCycle.size());
//         //     for (int v : negativeCycle) {
//         //         System.out.print((v + 1) + " "); 
//         //     }
//         //     System.out.println();
//         //     return true;
//         // }
//     }

//     public static void main(String[] args) {
//         Scanner sc = new Scanner(System.in);
//         int vertices = sc.nextInt();
//         List<List<Edge>> graph = new ArrayList<>();

//         for (int i = 0; i < vertices; i++) {
//             graph.add(new ArrayList<>());
//             for (int j = 0; j < vertices; j++) {
//                 int weight = sc.nextInt();

//                 graph.get(i).add(new Edge(i, j, weight));
                
//             }
//         }

//         BellmanFordAlgorithm(graph, vertices);
//     }
// }
