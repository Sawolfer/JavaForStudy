//Savva Ponomarev
import java.util.ArrayList;
import java.util.Scanner;

public class Main {

    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);
        int n = sc.nextInt();
        ArrayList<Node> nodes = new ArrayList<>();
        for (int i = 0; i < n; i++){
            int tmp = sc.nextInt();
            Node<Integer> node = new Node<>(i, tmp);
            nodes.add(node);
        }
        Node<Integer> root = new Node(1, nodes.get(0).value);
        Node<Integer> tree = root;
        for (int i = 1; i < n; i++) {
            tree.insert(root, i, (Integer) nodes.get(i).value);
        }
        tree.print(root);
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

    void insert(Node node, int key, T value){
        if (value.compareTo((T)node.value)==-1){
            if (node.left == null) node.left = new Node(key, value);
            else insert(node.left, key, value);
        }
        if (value.compareTo((T)node.value)>=0){
            if (node.right == null) node.right = new Node(key, value);
            else insert(node.right, key, value);
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

    ArrayList<Node<T>> toArray(Node node){
        if (node == null) return null;
        ArrayList<Node<T>> nodes = new ArrayList<>();
        if (node.left!=null) nodes.addAll(toArray(node.left));
        if (node.right !=null) nodes.addAll(toArray(node.right));
        nodes.add(node);
        return nodes;
    }
    void print(Node tree){
        if (tree == null){
            System.out.println("0");
            return;
        }
        ArrayList<Node<T>> arr = new ArrayList<>();
        arr = toArray(tree);
        System.out.println(arr.size());
        for (Node node: arr){
            int leftIndex = (node.left !=null) ? arr.indexOf(node.left) + 1 : -1;
            int rightIndex = (node.right !=null) ? arr.indexOf(node.right) + 1 : -1;
            System.out.println(node.value + " " + leftIndex + " " + rightIndex);
        }
        System.out.println(arr.indexOf(tree) + 1);
    }

}
