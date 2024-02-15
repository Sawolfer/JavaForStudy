/* Made by Selivanov George*/
import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        int N = scanner.nextInt();
        scanner.nextLine();
        HashMap<String, Integer> scores = new HashMap<>(N);
        for (int i = 0; i < N; i++) {
            int score = scanner.nextInt();
            String name = scanner.nextLine().trim();
            scores.put(name, score);
        }
        List<Pair<String, Integer>> listScores = scores.entrySet();
        N = listScores.size();
        Pair<String, Integer> normalStudent = nthSmallest(listScores, N / 2);
        System.out.println(normalStudent.getKey());
    }

    private static Pair<String, Integer> nthSmallest(List<Pair<String, Integer>> listScores, int n) {
        return listScores.get(select(listScores, 0, listScores.size() - 1, n));
    }

    private static Integer select(List<Pair<String, Integer>> listScores, int left, int right, int n) {
        if (left == right) {
            return left;
        }

        int pivotIndex = pivot(listScores, left, right);
        pivotIndex = partition(listScores, left, right, pivotIndex, n);
        if (n == pivotIndex) {
            return n;
        } else if (n < pivotIndex) {
            return select(listScores, left, pivotIndex - 1, n);
        } else {
            return select(listScores, pivotIndex + 1, right, n);
        }
    }

    private static int pivot(List<Pair<String, Integer>> listScores, int left, int right) {
        if (right - left < 5) {
            return partition5(listScores, left, right);
        }
        for (int i = left; i <= right; i += 5) {
            int subRight = i + 4;
            if (subRight > right) {
                subRight = right;
            }
            int median5 = partition5(listScores, i, subRight);
            swap(listScores, median5, left + (i - left) / 5);
        }
        int mid = (right - left) / 10 + left + 1;
        return select(listScores, left, left + (right - left) / 5, mid);
    }

    private static int partition5(List<Pair<String, Integer>> listScores, int left, int right) {
        int i = left;
        while(i < right){
            int j = i;
            while ((j > left) && (listScores.get(j).getValue() > listScores.get(j+1).getValue())){
                swap(listScores, j, j+1);
                j--;
            }
            i++;
        }
        return left + (right - left) / 2;
    }

    private static int partition(List<Pair<String, Integer>> listScores, int left, int right, int pivotIndex, int n) {
        int pivotValue = listScores.get(pivotIndex).getValue();
        swap(listScores, pivotIndex, right);
        int storeIndex = left;
        for (int i = left; i < (right-1); i++) {
            if (listScores.get(i).getValue() <= pivotValue) {
                swap(listScores, storeIndex, i);
                storeIndex++;
            }
        }
        int storeIndexEq = storeIndex;
        for (int i = storeIndex; i < right; i++){
            if (listScores.get(i).getValue() == pivotValue){
                swap(listScores, storeIndex, i);
                storeIndex++;
            }
        }
        swap(listScores, right, storeIndexEq);
        if (n < storeIndex){
            return storeIndex;
        }
        if (n <= storeIndexEq) {
            return n;
        }
        return storeIndexEq;
    }


    private static void swap(List<Pair<String, Integer>> listScores, int i, int j) {
        Pair<String, Integer> temp = listScores.get(i);
        listScores.set(i, listScores.get(j));
        listScores.set(j, temp);
    }
}

interface Map<K, V> {
    V get(K key);
    void put(K key, V value);
    int size();
    void remove(K key);
    Pair<K, V> getEntry(K key);
    List<Pair<K, V>> entrySet();
}

@SuppressWarnings("unchecked")
class Pair<K, V> {
    private K key;
    private V value;

    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }

    public void setValue(V value) {
        this.value = value;
    }
    public K getKey() {
        return key;
    }

    public V getValue() {
        return value;
    }
}

@SuppressWarnings("unchecked")
class HashMap<K, V> implements Map<K, V> {
    private final int capacity;
    private final List<Pair<K, V>>[] hashTable;

    public HashMap(int capacity) {
        this.capacity = capacity;
        this.hashTable = new List[capacity];
        initializeHashTable();
    }

    public List<Pair<K, V>>[] getHashMap() {
        return this.hashTable;
    }

    private void initializeHashTable() {
        for (int i = 0; i < capacity; i++) {
            hashTable[i] = new LinkedList<>();
        }
    }

    private int getHashIndex(K key) {
        int hashCode = Math.abs(key.hashCode());
        return hashCode % capacity;
    }

    @Override
    public V get(K key) {
        int index = getHashIndex(key);
        List<Pair<K, V>> bucket = hashTable[index];
        for (Pair<K, V> pair : bucket) {
            if (pair.getKey().equals(key)) {
                return pair.getValue();
            }
        }
        return null;
    }

    @Override
    public void put(K key, V value) {
        int index = getHashIndex(key);
        List<Pair<K, V>> bucket = hashTable[index];
        bucket.add(new Pair<>(key, value));
    }

    @Override
    public int size() {
        int size = 0;
        for (List<Pair<K, V>> bucket : hashTable) {
            size += bucket.size();
        }
        return size;
    }

    @Override
    public void remove(K key) {
        int index = getHashIndex(key);
        List<Pair<K, V>> bucket = hashTable[index];
        for (Pair<K, V> pair : bucket) {
            if (pair.getKey() == key) {
                bucket.remove(pair);
                return;
            }
        }
    }

    @Override
    public Pair<K, V> getEntry(K key) {
        int index = getHashIndex(key);
        List<Pair<K, V>> bucket = hashTable[index];
        for (Pair<K, V> pair : bucket) {
            if (pair.getKey().equals(key)) {
                return pair;
            }
        }
        return null;
    }

    @Override
    public List<Pair<K, V>> entrySet() {
        List<Pair<K, V>> entries = new ArrayList<>();
        for (List<Pair<K, V>> bucket : hashTable) {
            entries.addAll(bucket);
        }
        return entries;
    }
}