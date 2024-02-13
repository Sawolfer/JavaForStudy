import java.util.Arrays;
import java.util.Scanner;

public class test {


    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);
        int n =  Integer.parseInt(sc.nextLine());
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++){
            String currentString = sc.nextLine();
            String[] words = currentString.split(" ");
            int tmp = Integer.parseInt(words[0]);
            numbers[i] = tmp;
        }


        System.out.println(findMedian(numbers));

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

    public static int findMedian(int[] arr){

        if (arr.length == 1){
            return arr[0];
        }
        if (arr.length <=3){
            arr = sort(arr);
            return arr[arr.length/2];
        }

        int numberOfArrays = (int) Math.ceil((double) arr.length/3);
        int[] medians = new int[numberOfArrays];

        for (int i = 0; i < numberOfArrays; i++){
            int size = Math.min(3, arr.length - i * 3);
            int[] tmpArr = Arrays.copyOfRange(arr, i*3, i*3 + size);
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

}
