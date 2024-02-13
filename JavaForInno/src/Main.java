//Ponomarev Savva

import java.util.*;

public class Main {

    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);
        int n =  Integer.parseInt(sc.nextLine());
        HashMap<Integer, String> students = new HashMap(n);
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++){
            String currentString = sc.nextLine();
            String[] words = currentString.split(" ");
            students.put(Integer.parseInt(words[0]), words[1] + " " + words[2]);
            int tmp = Integer.parseInt(words[0]);
            numbers.add(tmp);
        }

        int median = findMedian(numbers, numbers.size()/2);

        System.out.println(students.get(median));


    }

    private static ArrayList<Integer> sort(ArrayList<Integer> nums){

        int tmp;
        boolean t = true;
        int n = 0;
        while (t){
            t = false;
            for (int i = 0; i< nums.size() - 1 - n; i++){
                int j = i+1;
                if (nums.get(i) > nums.get(j)){
                    tmp = nums.get(i);
                    nums.set(i, nums.get(j));
                    nums.set(j, tmp);
                    t = true;
                }
            }
            n++;
        }

        return nums;
    }

    public static int findMedian(ArrayList<Integer> arr, int searchIndex){

        if (arr.size() == 1){
            return arr.get(0);
        }
        if (arr.size() == 2){
            return sort(arr).get(searchIndex);
        }

        int numberOfArrays = (int) Math.ceil((double)(arr.size())/5);
        ArrayList<Integer> medians = new ArrayList<>();

        for (int i = 0; i < numberOfArrays; i++){
            int size = Math.min(5, arr.size() - i * 5);
            ArrayList<Integer> tmpArr = new ArrayList<>();
            tmpArr.addAll(arr.subList(i*5, i*5 + size));
            int newMedian = sort(tmpArr).get(tmpArr.size()/2);
            medians.add(newMedian);
        }

        int median;
        if (medians.size()%2==1){
            median = sort(medians).get(medians.size()/2);
        } else {
            median = sort(medians).get((medians.size())/2 - 1);
        }

        ArrayList<Integer> smallArray = new ArrayList<>();
        ArrayList<Integer> bigArray = new ArrayList<>();

        for (int num : arr){
            if (num > median){
                bigArray.add(num);
            }
            if ( num <= median){
                smallArray.add(num);
            }
        }

        if (smallArray.size() > searchIndex){
            return findMedian(smallArray, searchIndex);
        } else {
            return findMedian(bigArray, searchIndex - smallArray.size());
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
        int index = Math.abs(key.hashCode())%capacity;
        hashTable[index].add(new KeyValuePair<>(key, value));
        size++;

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