//Ponomarev Savva

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

class Main {

    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);
        int size = sc.nextInt();
        Graph<Integer> graph = new Graph<>(size);
        for (int i = 0; i < size; i++){
            graph.addVertex(i);
        }
        for (int i = 0; i < size; i++){
            graph.addVertex(i);
            for (int j = 0; j < size; j++){
                int status = sc.nextInt();
                if (status == 1){
                    Vertex<Integer> to =  graph.getVertex(i);
                    Vertex<Integer> from =  graph.getVertex(j);
                    graph.vertices.get(i).edges.add(new Edge(to, from));
                }
            }
        }

        graph.solve(graph.getVertex(0));
    }
}

class Edge<V>{
    Vertex<V> from;
    Vertex<V> to;

    public Edge(Vertex<V> from, Vertex<V> to){
        this.from = from;
        this.to = to;
    }
}

class Vertex<V>{
    boolean visited = false;
    V vertex;
    LinkedList<Edge<V>> edges;

    public Vertex(V node){
        this.vertex = node;
        edges = new LinkedList<>();
    }
}

class Graph<V>{

    int size;
    LinkedList<Vertex<V>> vertices;

    public Graph(int size){
        this.size = size;
        vertices = new LinkedList<>();
    }


    public Vertex<V> getVertex(int index){
        return vertices.get(index);
    }

    public void addVertex(V vertex){
        vertices.add(new Vertex(vertex));
    }

    public void addEdge(V i, V j){
        Vertex<Integer> to = new Vertex(i);
        Vertex<Integer> from = new Vertex(j);
        vertices.get((Integer)j).edges.add(new Edge(from, to));
    }
    public void addEdge(Vertex<V> i, Vertex<V> j){
        Vertex<V> to = i;
        Vertex<V> from = j;
        vertices.get((Integer)j.vertex).edges.add(new Edge(from, to));
    }

    ArrayList<Vertex<V>> queue = new ArrayList<>();

    public void SavvaPonomarev_bfs(Vertex<V> vertex){
        
        if (vertex.edges.size() == 1){
            vertex.visited=true;
            queue.add(vertex);
            return;
        }
        vertex.visited = true;
        queue.add(vertex);

        for (Edge<V> edge : vertex.edges){
            if (edge.to.visited == false){
                SavvaPonomarev_bfs(edge.to);
            }
        }
    }

    public void solve(Vertex<V> vertex){
        SavvaPonomarev_bfs(vertex);

        if (queue.size() == size){
            System.out.println("YES");
        }
        else {
            System.out.println("NO");
        }
    }
}
