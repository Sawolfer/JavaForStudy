//Savva Ponomarevs
import java.util.*;

public class Main  {

    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);

        int n = sc.nextInt();
        Item<Integer>[] arr = new Item[n];
        for (int i = 0; i < n; i++){
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            Item tmp = new Item(a, b, i+1);
            arr[i] = tmp;
        }

        arr = SavvaPonomarev_count_sort(arr, 1);

        arr = SavvaPonomarev_bucket_srt(arr, 0);
        // for (int i = 0; i < (arr.length/2); i++){
        //     Item tmp = arr[i];
        //     arr[i] = arr[arr.length-i - 1];
        //     arr[arr.length-i - 1] = tmp;
        // }

        for (Item item : arr){
            System.out.print(item.getIndex() + " ");
        }
    }


    public static Item[]  SavvaPonomarev_bucket_srt(Item[] arr, int indexToCompare){
        Item[] answer = new Item[arr.length];

        if (arr == null || arr.length == 0){
            return arr;
        }
        
        Integer[] toMax = new Integer[arr.length];
        for ( int i = 0; i < arr.length; i++){
            String tmp = String.valueOf(arr[i].getIndex(indexToCompare));
            toMax[i] = Integer.parseInt(tmp);
        }

        int numberOfBuckets = maxT(toMax);
        ArrayList<ArrayList<Item>> buckets = new ArrayList<>(numberOfBuckets + 1);

        for (int i = 0; i < numberOfBuckets+1; i++){
            ArrayList<Item> tmp = new ArrayList<>();
            buckets.add(tmp);
        }

        for (int i = 0; i < arr.length; i++){
            Item item = arr[i];
            int index = ((int) (item.getIndex(indexToCompare)));
            buckets.get(index).add(item);
        }

        for (List<Item> bucket : buckets){
            sort(bucket);
        }

        int j =0;
        for (int i = numberOfBuckets; i > 0; i--){
            for (Item item : buckets.get(i)){
                if (item != null){
                    answer[j] = item;
                    j++;
                }
            }
        }
        return answer;
    }

    public static void sort(List<Item> arr) {
        for (int i = 1; i < arr.size(); i++) {
            Item key = arr.get(i);
            int j = i - 1;
            while ((j >= 0) && (arr.get(j).getSecond().compareTo(key.getSecond()) > 0)) {
                arr.set(j+1, arr.get(j));
                j--;
            }
            arr.set(j+1, key);
        }
    }

    public static Item[] SavvaPonomarev_count_sort(Item[] arr, int sortingIndex){
        Integer[] tmp = new Integer[arr.length];

        if (arr == null || arr.length == 0){
            return arr;
        }

        for (int i =0; i< arr.length; i++){
            tmp[i] = (int) arr[i].getIndex(sortingIndex);
        }
        int maxi = maxT(tmp);
        int[] count = new int[maxi + 1];

        Item[] answer = new Item[arr.length];

        for (int i = 0; i < arr.length; i++){
            Item item = arr[i];
            count[(int)(item.getIndex(sortingIndex))]++;
        }
        for (int i = 1; i < count.length; i++){
            count[i] += count[i-1];
        }

        for (int i = 0; i < arr.length; i++){
            Item item = arr[i];
            int index = count[(Integer) item.getIndex(sortingIndex)]-1;
            answer[index] = item;
            count[(Integer) item.getIndex(sortingIndex)]-=1;
        }
        return answer;
    }
    private static int maxT(Integer[] arr){
        
        int answer = arr[0];
        for (int item : arr){
            if (item >= answer){
                answer = item;
            }
        }
        return answer;
    }

}
 class Item<F extends Comparable<F>>{
    F first;
    F second;
    F index;
    Item(F first, F second, F index){
        this.first = first;
        this.second = second;
        this.index = index;
    }

    public F getFirst() {
        return first;
    }

    public F getSecond() {
        return second;
    }
    public F getIndex(){
        return index;
    }

    public F getIndex(int i){
        if (i==0) return first;
        else return second;
    }
}



