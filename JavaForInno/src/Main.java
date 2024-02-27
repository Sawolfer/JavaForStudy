import java.awt.image.AreaAveragingScaleFilter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

public class Main  {

    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);
        for (int i = 0; i< 13; i++){
            int n = sc.nextInt();
            System.out.print((n*n - 2*n + 7)%13 + " ");
        }

    }

    public static <T> ArrayList<T> SavvaPonomarev_bucket_srt(T[] arr){

        if (arr == null || arr.length == 0){
            return;
        }

        int numberOfBuckets = arr.length;
        ArrayList<ArrayList<T>> buckets = new ArrayList<>(numberOfBuckets);

        for (int i = 0; i < numberOfBuckets; i++){
            ArrayList<T> tmp = new ArrayList<>();
            buckets.add(tmp);
        }

        for (T item : arr){
            int index = (int)( item.hashCode() % numberOfBuckets );
            buckets.get(index).add(item);
        }



    }

    public static <T extends Comparable<T>> ArrayList<T> SavvaPonomarev_count_sort(T[] arr){
        int maxi = (int) maxT(arr);
        int[] count = new int[ maxi + 1];
        T[] answer = Arrays.copyOf(arr, arr.length);

        for (T item : arr){
            count[(item.hashCode())%(count.length) + 1]++;
        }
        for (int i = 0; i < maxi; i++){
            count[i] += count[i-1];
        }

        for (int i = 0; i < arr.length; i++){
            
        }

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





