//Ponomarev Savva

import java.util.*;

public class Main {

    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);

        int n = Integer.parseInt(sc.nextLine());
        HashMap<String, String> phoneBook = new HashMap<>(n);

        for (int i = 0; i < n; i++){
            String currString = sc.nextLine();
            String[] input = currString.split(" ");
            String command = input[0];
            String name = "";
            String phoneNumber = "";
            if (input[2].contains(",")){
                String[] contactInfo = input[2].split(",");
                name = input[1] + " " + contactInfo[0];
                phoneNumber = contactInfo[1];
            } else {
                name = input[1] + " " + input[2];
            }
            
            
            switch (command) {
                case "ADD":
                    phoneBook.put(name, phoneNumber);
                    break;
                case "FIND":
                    if (phoneBook.get(name) != null && phoneBook.get(name).size()!=0) {
                            System.out.print("Found " + phoneBook.count(name) + " phone numbers for " + name + ": ");
                            for (int j = 0; j < phoneBook.get(name).size(); j++){
                                System.out.print(phoneBook.get(name).get(j).value + " ");
                            }
                            System.out.print("\n");
                    } else {
                        System.out.println("No contact info found for " + name);
                    }
                    break;
                case "DELETE":
                    if (phoneNumber.equals("")){
                        phoneBook.remove(name);
                    } else {
                        phoneBook.remove(name, phoneNumber);
                    }
                    break;
                default:
                    break;
            }


        }
    }

}
interface Map<K , V>{
    List<KeyValuePair<K, V>> get(K key);
    void put(K key, V value);
    int size();
    int count(K key);
    void remove(K key, V value);
    void remove(K key);
    List<KeyValuePair<K, V>> getEntry(K key);
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
    public List<KeyValuePair<K, V>> getEntry(K key) {
        int hash = Math.abs(key.hashCode()) % capacity;
        int index = hash;
        while (hashTable[index] != null && !hashTable[index].isEmpty() && !hashTable[index].get(0).key.equals(key)){
            index = (index + 1) % capacity;
        }
        return hashTable[index];    
    }


    @Override
    public List<KeyValuePair<K, V>> get(K key) {
        List<KeyValuePair<K, V>> value = null;
        value = getEntry(key);
        return value;
    }

    @Override
    public void put(K key, V value) {
        int index = Math.abs(key.hashCode())%capacity;
        while (hashTable[index].size() != 0){
            if (!hashTable[index].get(0).key.equals(key)){
                index+=1;
            } else {
                break;
            }
            if (index == capacity){
                index = 0;
            }   
        }
        for (int i = 0; i < hashTable[index].size(); i++){
            if (hashTable[index].get(i).value.equals(value)){
                return;
            }
        }
        hashTable[index].add(new KeyValuePair<>(key, value));
        size++;

    }

    @Override
    public int size() {
        return this.size;
    }

    @Override
    public int count(K key) {
        return getEntry(key).size();
    }

    @Override
    public void remove(K key, V value) {
        List<KeyValuePair<K, V>> pairs = getEntry(key);
        if (pairs != null){
            for (int i = 0; i < pairs.size(); i++){
                if (pairs.get(i).value.equals(value)){
                    pairs.remove(i);
                    size--;
                    break;
                }
            }
        }
    }

    @Override
    public void remove(K key) {
        
        if (getEntry(key) != null){
            List<KeyValuePair<K, V>> pairs = getEntry(key);
            while (pairs.size() != 0){
                pairs.remove(0);
                size--;
            }
        }

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