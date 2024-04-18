import java.util.*;


public class DSA {
    static class Edge {
        int src, dest, weight;

        public Edge(int source, int destination, int weight) {
            this.src = source;
            this.dest = destination;
            this.weight = weight;
        }
    }

    static boolean BellmanFordAlgorithm(List<List<Edge>> graph, int vertices) {
        List<Integer> dist = new ArrayList<>();
        List<Integer> parent = new ArrayList<>();

        int negativeVertex = Integer.MIN_VALUE;
        
        for (int i = 0; i < vertices; i++) {
            dist.add(Integer.MAX_VALUE);
            parent.add(-1);
        }


        dist.set(0, 0);
        for (int i = 0; i < vertices - 1; i++) {
            for (Edge edge : graph.get(i)) {
                int u = edge.src;
                int v = edge.dest;
                int weight = edge.weight;
                if (dist.get(u)!= Integer.MAX_VALUE && (dist.get(u) + weight) < dist.get(v)) {
                    negativeVertex = v;
                    dist.set(v, dist.get(u) + weight);
                    parent.set(v, u);
                }
            }
        }

        if (negativeVertex == Integer.MIN_VALUE) {
            System.out.println("NO");
            return false;
        }
        // else {
        //     List<Integer> negativeCycle = new ArrayList<>();
        //     int current = negativeVertex;
        //     List<Integer> visited = new ArrayList<>(); 
            
        //     do {
        //         if (visited.contains(current)) {
        //             break;
        //         }
        //         negativeCycle.add(current);
        //         visited.add(current);
        //         current = parent.indexOf(current);
        //     } while (current != negativeVertex && current != -1); 
            


        //     System.out.println("YES");
        //     System.out.println(negativeCycle.size());
        //     for (int v : negativeCycle) {
        //         System.out.print((v) + " ");                
        //     }
        //     System.out.println();
            
        //     return true;
        // }

        else {
            System.out.println("YES");
            for (int i = 0; i < vertices; ++i)
                negativeVertex = parent.get(parent.indexOf(negativeVertex));

            ArrayList<Integer> cycle = new ArrayList<>();

            for (int v = negativeVertex;; v = parent.get(v)) {
                cycle.add(v);
                if (v == negativeVertex && cycle.size() > 1)
                    break;
            }

            for (int v : cycle)
                System.out.print(v + " ");
            System.out.println();
            return true;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int vertices = sc.nextInt();
        List<List<Edge>> graph = new ArrayList<>();

        for (int i = 0; i < vertices; i++) {
            graph.add(new ArrayList<>());
            for (int j = 0; j < vertices; j++) {
                int weight = sc.nextInt();

                graph.get(i).add(new Edge(i, j, weight));
                
            }
        }

        BellmanFordAlgorithm(graph, vertices);
    }
}
