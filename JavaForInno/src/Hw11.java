//Ponomarev Savva


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
        int collection[] = new int[size];
        graph.SavvaPonomarev_bfs(graph.getVertex(0), collection);
        int answer = 1;
        for (int i = 0; i < size; i++){
            answer *= collection[i];
        }
        if (answer == 1){
            System.out.println("YES");
        }
        else{
            System.out.println("NO");
        }
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

    public void SavvaPonomarev_bfs(Vertex<V> vertex, int[] collection){
        
        if (vertex.edges.size() == 1){
            collection[(Integer)vertex.vertex] = 1;
            return;
        }
        collection[(Integer)vertex.vertex] = 1;

        for (Edge<V> edge : vertex.edges){
            if (collection[(Integer)edge.to.vertex] == 0){
                SavvaPonomarev_bfs(edge.to, collection);
            }
        }
    }
}
