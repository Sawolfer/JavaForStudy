import java.security.Key;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);
        int n = sc.nextInt();
        HashMap<Integer, String> students = new HashMap(n);
        int[] number = new int[n];
        for (int i = 0; i < n; i++){
            String currentString = sc.next();
            String[] words = currentString.split(" ");
            students.put(Integer.parseInt(words[0]), words[1] + " " + words[2]);
            int tmp = Integer.parseInt(words[0]);
            number[i] = tmp;
        }



    }

    private int[] sort(int[] arr){

        for (int i = 0; i < arr.length; i++){
            boolean flag = arr[i]>arr[i+1];
            while (flag){
                flag = false;
                int tmp = arr[i+1];
                arr[i+1] = arr[i];
                arr[i] = tmp;
                flag = arr[i]>arr[i+1];
            }

        }
        return arr;
    }

}
interface Map<K , V>{
    V get(K key);
    void put(K key, V value);
    int size();
    void remove(K key);
    KeyValuePair<K, V> getEntry(K key);
    List<KeyValuePair<K, V>> entrySet();
}

class KeyValuePair<K, V>{
    K key;
    V value;
    public KeyValuePair(K key, V value){
        this.key = key;
        this.value = value;
    }

}

class HashMap<K, V> implements Map<K, V> {
    int size;
    int capacity;
    List<KeyValuePair<K, V>>[] hashTable;

    public HashMap(int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.hashTable = new List[capacity];
        for (int i = 0; i < capacity; i++){
            this.hashTable[i] = new LinkedList<>();
        }
    }

    @Override
    public KeyValuePair<K, V> getEntry(K key) {
        int hash = Math.abs(key.hashCode()) % capacity;
        for (int i = 0; i < this.hashTable[hash].size(); i++){
            KeyValuePair<K, V> tmpPair = hashTable[hash].get(i);
            if (tmpPair.key.equals(key)){
                return tmpPair;
            }
        }
        return null;
    }

    @Override
    public V get(K key) {
        V value = null;
        if (getEntry(key) != null){
            value = (getEntry(key).value);
        }
        return value;
    }

    @Override
    public void put(K key, V value) {
        if (getEntry( key ) != null){
            KeyValuePair<K, V> newPair = getEntry(key);
            newPair.value = value;
            size++;
        } else {
            int index = Math.abs(key.hashCode())%capacity;
            hashTable[index].add(new KeyValuePair<>(key, value));
            size++;
        }
    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public void remove(K key) {
        getEntry(key).value = null;
        size--;
    }

    @Override
    public List<KeyValuePair<K, V>> entrySet(){
        List<KeyValuePair<K, V>> returnable = new LinkedList<>();
        for (List<KeyValuePair<K, V>> pairs : hashTable) {
            if (pairs != null) {
                returnable.addAll(pairs);
            }
        }
        return returnable;
    }
}