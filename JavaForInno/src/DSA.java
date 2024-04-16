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

    static boolean FloydWarshall(List<List<Edge>> graph, int N) {
        List<List<Integer>> distance = new ArrayList<>(N);
        List<List<Integer>> next = new ArrayList<>(N);
        
        for (int i = 0; i < N; i++) {
            distance.add(new ArrayList<>(Collections.nCopies(N, 100000)));
            next.add(new ArrayList<>(Collections.nCopies(N, -1)));
            for (Edge edge : graph.get(i)) {
                distance.get(i).set(edge.dest, edge.weight);
                next.get(i).set(edge.dest, edge.dest);
            }
        }

        for (int k = 0; k < N; k++) {
            for (int i = 0; i < N; i++) {
                for (int j = 0; j < N; j++) {
                    if (distance.get(i).get(k) != 100000 && distance.get(k).get(j) != 100000 &&
                            distance.get(i).get(k) + distance.get(k).get(j) < distance.get(i).get(j)) {
                        distance.get(i).set(j, distance.get(i).get(k) + distance.get(k).get(j));
                        next.get(i).set(j, next.get(i).get(k));
                    }
                }
            }
        }


        for (int i = 0; i < N; i++) {
            if (distance.get(i).get(i) < 0) {
                printCycle(next, i);
                return true;
            }
        }
        return false;
    }

    static void printCycle(List<List<Integer>> next, int start) {
        List<Integer> cycle = new ArrayList<>();
        boolean[] visited = new boolean[next.size()];
        int current = start;
        while (!visited[current]) {
            cycle.add(current + 1); 
            visited[current] = true;
            current = next.get(current).get(start);
        }
        
        System.out.println("YES");
        System.out.println(cycle.size());
        for (int vertex : cycle) {
            System.out.print(vertex + " ");
        }
        System.out.println();
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

        FloydWarshall(graph, vertices);
    }
}
