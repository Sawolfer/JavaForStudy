//Savva Ponomarev

import java.sql.PreparedStatement;
import java.util.*;

public class  SpellChecker {

    static Scanner sc;
    public static void main(String[] args){
        sc = new Scanner(System.in);
        int n =  Integer.parseInt(sc.nextLine());
        String inputString = sc.nextLine();
        String[] words = inputString.split(" ");
        CustHashMap<String, Integer> wordsStorage = new CustHashMap<>(n);

        for (String word : words){
            if (wordsStorage.get(word) != null){
                int tmp = wordsStorage.get(word);
                wordsStorage.put(word, tmp+1);
            } else {
                wordsStorage.put(word, 1);
            }
        }

        n = Integer.parseInt(sc.nextLine());
        CustHashMap<String, Integer> wordsStorage2 = new CustHashMap<>(n);
        ArrayList<String> answer = new ArrayList<>();
        inputString = sc.nextLine();
        String[] words2 = inputString.split(" ");
        for(String word : words2) {
            if (wordsStorage.get(word) == null) {
                wordsStorage2.put(word, 1);
            }
        }
        List<Pair<String, Integer>> answer2 = wordsStorage2.entrySet();
        for(String word : words2){
            if (wordsStorage2.get(word)!= null){
                answer.add(word);
                wordsStorage2.remove(word);
            }
        }

        System.out.println(answer.size());
        for (String item : answer) {
            System.out.println(item);
        }
    }
}

interface CustMap<K , V>{
    V get(K key);
    void put(K key, V value);
    int size();
    void remove(K key);
    Pair<K, V> getEntry(K key);
    List<Pair<K, V>> entrySet();
}

class Pair<K, V>{
    K key;
    V value;
    public Pair(K key, V value){
        this.key = key;
        this.value = value;
    }

}

class CustHashMap<K, V> implements CustMap<K, V>{
    int size;
    int capacity;
    List<Pair<K, V>>[] hashTable;

    public CustHashMap(int capacity){
        this.size = 0;
        this.capacity = capacity;
        this.hashTable = new List[capacity];
        for (int i = 0; i < capacity; i++){
            this.hashTable[i] = new LinkedList<>();
        }
    }

    @Override
    public Pair<K, V> getEntry(K key) {
        int hash = Math.abs(key.hashCode()) % capacity;
        for (int i = 0; i < this.hashTable[hash].size(); i++){
            Pair<K, V> tmpPair = hashTable[hash].get(i);
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
            Pair<K, V> newPair = getEntry(key);
            newPair.value = value;
            size++;
        } else {
            int index = Math.abs(key.hashCode())%capacity;
            hashTable[index].add(new Pair<>(key, value));
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
    public List<Pair<K, V>> entrySet(){
        List<Pair<K, V>> returnable = new LinkedList<>();
        for (List<Pair<K, V>> pairs : hashTable) {
            if (pairs != null) {
                returnable.addAll(pairs);
            }
        }
        return returnable;
    }
}
