//Savva Ponomarev

import java.util.*;

public class  SpellChecker {

    static Scanner sc;
    public static void main(String[] args){
        sc = new Scanner(System.in);
        int n =  Integer.parseInt(sc.nextLine());
        String inputString = sc.nextLine();
        String[] words = inputString.split(" ");
        HashMap<String, Integer> wordsStorage = new HashMap<>(n);

        for (String word : words){
            if (wordsStorage.get(word) != null){
                int tmp = wordsStorage.get(word);
                wordsStorage.put(word, tmp+1);
            } else {
                wordsStorage.put(word, 1);
            }
        }

        n = Integer.parseInt(sc.nextLine());
        HashMap<String, Integer> wordsStorage2 = new HashMap<>(n);
        ArrayList<String> answer = new ArrayList<>();
        inputString = sc.nextLine();
        String[] words2 = inputString.split(" ");
        for(String word : words2) {
            if (wordsStorage.get(word) == null) {
                wordsStorage2.put(word, 1);
            }
        }
        List<KeyValuePair<String, Integer>> answer2 = wordsStorage2.entrySet();
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