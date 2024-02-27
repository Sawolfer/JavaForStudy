import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main  {

    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);

        int n = sc.nextInt();
        int[][] arr = new int[n][2]; 
        for (int i = 0; i < n; ){
            int a, b;
            a = sc.nextInt();
            b = sc.nextInt();
            arr[i][0] = a;
            arr[i][1] = b;
        }
        int sortingIndex = 1;
        ArrayList<int[]> answer = new ArrayList<>();
        answer  = SavvaPonomarev_bucket_srt(arr, sortingIndex);
        sortingIndex--;
        answer = SavvaPonomarev_bucket_srt(answer, sortingIndex);
        for (int i = 0; i < arr.length; i++){
            System.out.println(arr[i][0] + " " + arr[i][1]);
        }
    }

    public static <T> ArrayList<T> SavvaPonomarev_bucket_srt(T[][] arr, int sortingIndex){
        ArrayList<T> answer = new ArrayLisrt<>();

        if (arr == null || arr.length == 0){
            return null;
        }

        int numberOfBuckets = arr.length;
        ArrayList<ArrayList<T>> buckets = new ArrayList<>(numberOfBuckets);

        for (int i = 0; i < numberOfBuckets; i++){
            ArrayList<T> tmp = new ArrayList<>();
            buckets.add(tmp);
        }

        for (int i = 0; i < numberOfBuckets; i++){
            T item = arr[i][sortingIndex];
            int index = (int)( item.hashCode() % numberOfBuckets );
            buckets.get(index).add(item);
        }

        for (ArrayList<> bucket : buckets){
            bucket = SavvaPonomarev_count_sort(bucket);
        }
        
        
        for (int i = 0; i < numberOfBuckets; i++){
            for (T item : buckets.get(i)){
                answer.add(item);
            }
        }
        return answer;
    }

    public static <T extends Comparable<T>> ArrayList<T> SavvaPonomarev_count_sort(T[][] arr, int sortingIndex){
        int[] tmp = new int[arr.length];
        for (int i =0; i< arr.length; i++){
            tmp[i] = arr[i][sortingIndex];
        }
        int maxi = (int) maxT(tmp);
        int[] count = new int[ maxi + 1];

        ArrayList<T> answer = new ArrayList<>(arr.length);

        for (int i = 0; i < arr.length; i++){
            T item = arr[i][sortingIndex];
            count[(item.hashCode())%(count.length) + 1]++;
        }
        for (int i = 0; i < maxi; i++){
            count[i] += count[i-1];
        }

        for (int i = arr.length - 1; i >= 0; i--){
            T item = arr[i][sortingIndex];
            int index =count[item.hashCode() % count.length];
            answer.set(index - 1, item);
            count[index]--;
        }
        return answer;
    }
    private static <T extends Comparable<T>> T maxT(T[] arr){

        T answer = arr[0];
        for (T item : arr){
            if (item.compareTo(answer) >= 1){
                answer = item;
            }
        }
        return answer;
    }

}





