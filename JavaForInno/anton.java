
// import java.util.*;

// class Main {

//     public static void main(String[] args) {
//         Scanner scanner = new Scanner(System.in);
//         Graph<Integer> graph = new Graph<>();
//         int n = scanner.nextInt();
//         scanner.nextLine();
//         for (int i = 0; i < n; i ++){
//             graph.addVertex(i);
//         }
//         int[][] data = new int[n][n];
//         for (int i = 0; i < n; i ++){
//             for (int j = 0; j < n; j ++){
//                 int connection = scanner.nextInt();
//                 if (connection == 1){
//                     graph.addEdge(graph.vertices.get(i), graph.vertices.get(j));

//                 }
//             }
//             scanner.nextLine();
//         }
//         String result = graph.AntonKorotkov_bfs();
//         System.out.println(result);
//     }
// }

// class Graph<T>{
//     class Vertex<T>{
//         private T value;
//         LinkedList<Edge> adjacentEdges;
//         public Vertex(T value){
//             this.value = value;
//             this.adjacentEdges = new LinkedList<>();
//         }
//         public T getValue() {
//             return value;
//         }
//         int visit_status = 0; //0 - not visited, 1 - visited;
//     }
//     class Edge<V>{
//         private V label;
//         private Vertex vertex1;
//         private Vertex vertex2;
//         public Vertex getVertex1(){
//             return this.vertex1;
//         }
//         public Vertex getVertex2() {
//             return this.vertex2;
//         }
//         public Edge(Vertex vertex1, Vertex vertex2){
//             this.vertex1 = vertex1;
//             this.vertex2 = vertex2;
//         }
//     }
//     LinkedList<Vertex> vertices;

//     public Graph(){
//         this.vertices = new LinkedList<>();
//     }
//     public void addVertex(T value){
//         vertices.add(new Vertex(value));
//     }
//     public void addEdge(Vertex v1, Vertex v2){
//         for (Object edge1 : v1.adjacentEdges){
//             Edge edge = (Edge) edge1; //Следующая строчка описывает проверку: имеется ли вводимое ребро или нет
//             //Это нужно для того, чтобы не вводить ребро два раза, так как на вход поступает матрица
//             //типа если у ребра вершина1 = в1(которую ввели), а вершина2 = в2(которую ввели) либо наоборот(потому что порядок может меняться 
//             //из-за того, что граф неориентированный.
//             if (edge.vertex1 == v1 & edge.vertex2 == v2 || edge.vertex1 == v2 & edge.vertex2 == v1){
//                 return;
//             }
//         }
//         Edge newEdge = new Edge(v1, v2);
//         v1.adjacentEdges.add(newEdge);
//         v2.adjacentEdges.add(newEdge);
//     }
//     public void recursive_bfs(Vertex start){
//         start.visit_status = 1;
//         if (start.adjacentEdges.size() == 0){
//             return;
//         }
//         for (int i = 0; i < start.adjacentEdges.size(); i ++){
//             Edge edge = (Edge) start.adjacentEdges.get(i);
//             //Здесь важно выбрать вершину to -- ту, которая соседствует с заданной. 
//             //Здесь присутствует проверка на то, что to не является заданной, так как у ребра неупорядоченные вершины(
//             //вершина1 и вершина2). Соотвественно, нужно выбрать противоположенную от рассматриваемой.
//             Vertex to = (edge.vertex1 == start)? edge.vertex2 : edge.vertex1;
//             if (to.visit_status == 0){
//                 recursive_bfs(to);
//             }
//         }
//     }
//     public String AntonKorotkov_bfs(){
//         Vertex start = vertices.get(0);
//         recursive_bfs(start);
//         for (Vertex vertex : vertices){
//             if (vertex.visit_status == 0){
//                 return "NO";
//             }
//         }
//         return "YES";
//     }
// }