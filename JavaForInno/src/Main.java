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

        System.out.println(students.get(median));


    }

    public static int recheck (int[] arr, int median){
        int smaller = 0;
        int greater = 0;
        int[] smallerNums = new int[arr.length];
        int[] greaterNums = new int[arr.length];
        for (int item : arr){
            if (item > median){
                greaterNums[greater] = item;
                greater++;
            } else {
                smallerNums[smaller] = item;
                smaller++;
            }
        }

        if (smaller-1 == arr.length/2 || greater-1 == arr.length/2){
            return median;
        } else if (smaller > arr.length/2){
            return (findMedian(Arrays.copyOf(smallerNums, smaller)));
        } else {
            return (findMedian(Arrays.copyOf(greaterNums, greater)));
        }
    }

    public static int findMedian(int[] arr){

        if (arr.length == 1){
            return arr[0];
        }
        if (arr.length <=5){
            arr = sort(arr);
            return arr[arr.length/2];
        }

        int numberOfArrays = (int) Math.ceil((double) arr.length/5);
        int[] medians = new int[numberOfArrays];

        for (int i = 0; i < numberOfArrays; i++){
            int size = Math.min(5, arr.length - i * 5);
            int[] tmpArr = Arrays.copyOfRange(arr, i*5, i*5 + size);
            int newMedian = findMedian(tmpArr);
            medians[i] = newMedian;
        }

        int median = findMedian(medians);

        int[] smallArray = new int[arr.length];
        int[] bigArray = new int[arr.length];
        int smallerCount = 0;
        int biggerCount = 0;

        for (int num : arr){
            if (num > median){
                bigArray[biggerCount] = num;
                biggerCount++;
            } else if ( num < median){
                smallArray[smallerCount] = num;
                smallerCount++;
            }
        }
        bigArray[biggerCount] = median;
        biggerCount++;
        smallArray[smallerCount] = median;
        smallerCount++;

        if (smallerCount == arr.length/2){
            return median;
        } else if (smallerCount > arr.length/2){
            return findMedian(Arrays.copyOf(smallArray, smallerCount));
        } else {
            return findMedian(Arrays.copyOf(bigArray, biggerCount));
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