//Savva Ponomarev

import java.util.*;

public class FrequencyAnalysis {

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
        List<KeyValuePair<String, Integer>> answers = wordsStorage.entrySet();
        Collections.sort(answers, new Comparator<KeyValuePair<String, Integer>>() {
            @Override
            public int compare(KeyValuePair<String, Integer> o1, KeyValuePair<String, Integer> o2) {
                int result = o2.value.compareTo(o1.value);
                if (result == 0) {
                    result = o1.key.compareTo(o2.key);
                }
                return result;
            }
        });


        for (KeyValuePair<String, Integer> item : answers){
            System.out.println(item.key + " " + item.value);
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
        this.hashTable = new List[1000];
        for (int i = 0; i < capacity; i++){
            this.hashTable[i] = new LinkedList<>();
        }
    }

    @Override
    public KeyValuePair<K, V> getEntry(K key) {
        int hash = Math.abs(key.hashCode()) % capacity;
        for (int i = 0; i < this.hashTable[hash].size(); i++){
            KeyValuePair tmpPair = hashTable[hash].get(i);
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
            value = (V)(getEntry(key).value);
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
        for ( int i = 0; i < hashTable.length; i++){
            if (hashTable[i] != null){
                for (int j = 0; j < hashTable[i].size(); j++){
                    returnable.add(hashTable[i].get(j));
                }
            }

        }

        return returnable;
    }
}