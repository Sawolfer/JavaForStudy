// Savva Ponomarev

import java.util.Scanner;

public class NearlySortedSequence {

    private static Scanner sc;
    public static void main(String args[]){
        sc = new Scanner(System.in);
        int l = sc.nextInt();

        int nums[] = new int[l];
        for (int i = 0; i < l; i++) {
            nums[i] = sc.nextInt();
        }
        nums = SortingAlg(nums);

        for (int num : nums){
            System.out.print(num);
            System.out.print(" ");
        }

    }

    private static int[] SortingAlg(int nums[]){

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