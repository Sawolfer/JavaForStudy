import com.sun.javafx.logging.JFRInputEvent;

import java.beans.XMLEncoder;
import java.security.Key;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Main {

    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);
        int n =  Integer.parseInt(sc.nextLine());
        HashMap<Integer, String> students = new HashMap(n);
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++){
            String currentString = sc.nextLine();
            String[] words = currentString.split(" ");
            students.put(Integer.parseInt(words[0]), words[1] + " " + words[2]);
            int tmp = Integer.parseInt(words[0]);
            numbers[i] = tmp;
        }

        int median = findMedian(numbers);

        System.out.printf(students.get(median));

    }

    public static int findMedian(int[] arr){

        if (arr.length ==1){
            return arr[0];
        }

        int numberGroups = (int) Math.ceil((double) arr.length/5);
        int[] medians = new int[numberGroups];

        for (int i = 0; i < numberGroups; i++) {
            int groupSize = Math.min(5, arr.length - i * 5);
            int[] group = new int[groupSize];
            System.arraycopy(arr, i * 5, group, 0, groupSize);
            group = sort(group);
            medians[i] = group[groupSize / 2];
        }

        int medianOfMedians = findMedian(medians);

        int[] smaller = new int[arr.length];
        int[] greater = new int[arr.length];
        int smallerCount = 0;
        int greaterCount = 0;

        for (int num : arr) {
            if (num < medianOfMedians) {
                smaller[smallerCount++] = num;
            } else if (num > medianOfMedians) {
                greater[greaterCount++] = num;
            }
        }

        if (smallerCount == arr.length / 2) {
            return medianOfMedians;
        } else if (smallerCount > arr.length / 2) {
            return findMedian(Arrays.copyOf(smaller, smallerCount));
        } else {
            return findMedian(Arrays.copyOf(greater, greaterCount));
        }

    }
    private static int[] sort(int nums[]){

        int tmp;
        boolean t = true;
        int n = 0;
        while (t){
            t = false;
            for (int i = 0; i< nums.length - 1 - n; i++){
                int j = i+1;
                if (nums[i] > nums[j]){
                    tmp = nums[i];
                    nums[i] = nums[j];
                    nums[j] = tmp;
                    t = true;
                }
            }
            n++;
        }

        return nums;
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