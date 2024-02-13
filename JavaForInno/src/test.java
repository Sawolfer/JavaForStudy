import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

import static java.lang.Math.ceil;

public class test {


    static Scanner sc;
    public static void main(String[] args) {

        sc = new Scanner(System.in);
        int n =  Integer.parseInt(sc.nextLine());
        ArrayList<Integer> numbers = new ArrayList<>();
        for (int i = 0; i < n; i++){
            String currentString = sc.nextLine();
            String[] words = currentString.split(" ");
            int tmp = Integer.parseInt(words[0]);
            numbers.add(tmp);
        }


        System.out.println(findMedian(numbers, numbers.size()/2));

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

//        if (smallerCount == arr.length/2){
//            return median;
//        }
        if (smallArray.size() > searchIndex){
            return findMedian(smallArray, searchIndex);
        } else {
            return findMedian(bigArray, searchIndex - smallArray.size());
        }

    }

}
