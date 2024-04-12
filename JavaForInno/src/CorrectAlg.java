import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Scanner;

class PrimsAlgorithm {
    static Scanner sc;

    public static void main(String[] args) {

        sc = new Scanner(System.in);

        int vertices = sc.nextInt();
        int edges = sc.nextInt();

        List<Edge> edgesList = new ArrayList<>();

        for (int i = 0; i < edges; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            int weight = sc.nextInt();

            edgesList.add(new Edge(from, to, weight));
        }
        MinimumSpanningForest MSF = new MinimumSpanningForest();
        List<List<Edge>> forest = MSF.minimumSpanningForest(vertices, edgesList);
        List<Integer> vertList = MSF.getVertList();

        System.out.println(forest.size());
        int i =0;
        for (List<Edge> tree : forest) {
            if (tree.size()==0){
                System.out.println(vertList.get(i));
            } else {
                System.out.println(vertList.get(i) + " " + tree.get(0).from);
            }
            for (Edge edge : tree) {
                System.out.println(edge.from + " " + edge.to + " " + edge.weight);
            }
            i++;
        }

    }
}


class Edge implements Comparable<Edge> {

    int from, to, weight;
        
    public Edge(int from, int to, int weight) {
        this.from = from;
        this.to = to;
        this.weight = weight;
    }

    @Override
    public int compareTo(Edge other) {
        return Integer.compare(this.weight, other.weight);
    }
}

class MinimumSpanningForest {

    List<Integer> vertList;

    public List<List<Edge>> minimumSpanningForest(int N, List<Edge> edges) {
        List<List<Edge>> forest = new ArrayList<>();
        vertList = new ArrayList<>();
        PriorityQueue<Edge> pq = new PriorityQueue<>();

        List<List<Edge>> adjacencyList = new ArrayList<>();
        for (int i = 0; i <= N; i++) {
            adjacencyList.add(new ArrayList<>());
        }

        for (Edge edge : edges) {
            adjacencyList.get(edge.from).add(edge);
            adjacencyList.get(edge.to).add(new Edge(edge.to, edge.from, edge.weight));
        }
        
        boolean[] visited = new boolean[N + 1];

        for (int i = 1; i <= N; i++) {
            
            if (!visited[i]) {
                int count = 1;
                List<Edge> treeEdges = new ArrayList<>();
                pq.add(new Edge(0, i, 0));

                while (!pq.isEmpty()) {
                    Edge minEdge = pq.poll();

                    if (visited[minEdge.to]){
                        continue;
                    }

                    visited[minEdge.to] = true;
                    if (minEdge.from != 0) {
                        treeEdges.add(minEdge);
                        count++;
                    }

                    for (Edge neighbor : adjacencyList.get(minEdge.to)) {
                        if (!visited[neighbor.to]) {
                            pq.add(neighbor);
                        }
                    }
                }
                vertList.add(count);
                forest.add(treeEdges);
            }

        }
        
        return forest;
    }

    public List<Integer> getVertList() {
        return vertList;
    }
}
