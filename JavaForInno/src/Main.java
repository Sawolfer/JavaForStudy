//Savva Ponomarev
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc;
    public static void main(String[] args) {
        sc = new Scanner(System.in);
        int n = sc.nextInt();
        long startTime = System.currentTimeMillis();
        ArrayList<Node<Integer>> nodes = new ArrayList<>();
        Node<Integer> root = null;
        for (int i = 0; i < n; i++){
            int tmp = sc.nextInt();
            if (root == null) {
                root = new Node<>(i, tmp);
                nodes.add(root);
            }
            else root.insert(root, i, tmp, nodes);
        }
        root.print(root, nodes);
        long endTime = System.currentTimeMillis();
        long totalTime = endTime - startTime;
        System.out.println("Execution time in milliseconds: " + totalTime);
    }
}

class Node<T extends Comparable<T>>{
    int key;
    T value;
    int height = 0;
    Node left = null;
    Node right = null;

    public Node(int key, T value)
    {
        this.key = key;
        this.value = value;
    }

    void insert(Node node, int key, T value, ArrayList<Node<T>> nodes){
        if (value.compareTo((T)node.value)==-1){
            if (node.left == null) {
                node.left = new Node(key, value);
                nodes.add(node.left);
            }
            else insert(node.left, key, value, nodes);
        }
        if (value.compareTo((T)node.value)>=0){
            if (node.right == null) {
                node.right = new Node(key, value);
                nodes.add(node.right);
            }
            else insert(node.right, key, value, nodes);
        }
        updateHeight(node);
        balance(node);
    }
    public void updateHeight(Node node){
        node.height = Math.max(getHeight(node.left), getHeight(node.right))+1 ;
    }
    public int getHeight(Node node) {
        if (node == null) return -1;
        return node.height;
    }
    public int getBalance(Node node){
        if (node == null){
            return 0;
        }
        return (getHeight(node.right) - getHeight(node.left));
    }

    void swap (Node a, Node b){
        int tmpKey = a.key;
        T tmpValue = (T) a.value;
        a.key = b.key;
        b.key = tmpKey;
        a.value = b.value;
        b.value = tmpValue;
    }
    void rightRotate(Node node){
        swap(node, node.left);
        Node tmp = node.right;
        node.right = node.left;
        node.left = node.right.left;
        node.right.left = node.right.right;
        node.right.right = tmp;

        updateHeight(node.right);
        updateHeight(node);
    }
    void leftRotate(Node node){
        swap(node, node.right);
        Node tmp = node.left;
        node.left = node.right;
        node.right = node.left.right;
        node.left.right = node.left.left;
        node.left.left = tmp;

        updateHeight(node.left);
        updateHeight(node );
    }

    void balance(Node node){
         int balance = getBalance(node);
         if (balance > -2 && balance < 2) return;
         if (balance == -2){
             if (getBalance(node.left) == 1) leftRotate(node.left);
             rightRotate(node );
         } else if (balance == 2){
              if (getBalance(node.right) == -1) rightRotate(node.right);
              leftRotate(node);
         }
    }
//    void print(Node tree, ArrayList<Node<T>> nodes){
//        if (tree == null){
//            System.out.println("0");
//            return;
//        }
//        System.out.println(nodes.size());
//        for (Node node: nodes){
//            int leftIndex = (node.left !=null) ? nodes.indexOf(node.left) + 1 : -1;
//            int rightIndex = (node.right !=null) ? nodes.indexOf(node.right) + 1 : -1;
//            System.out.println(node.value + " " + leftIndex + " " + rightIndex);
//        }
//        System.out.println(nodes.indexOf(tree) + 1);
//    }
    void print(Node<T> tree, ArrayList<Node<T>> nodes) {
        if (tree == null) {
            System.out.println("0");
            return;
        }

        StringBuilder sb = new StringBuilder();
        sb.append(nodes.size()).append("\n");

        for (Node<T> node : nodes) {
            int leftIndex = node.left != null ? nodes.indexOf(node.left) + 1 : -1;
            int rightIndex = node.right != null ? nodes.indexOf(node.right) + 1 : -1;
            sb.append(node.value).append(" ").append(leftIndex).append(" ").append(rightIndex).append("\n");
        }

        sb.append(nodes.indexOf(tree) + 1).append("\n");

        System.out.print(sb.toString());
    }
}
