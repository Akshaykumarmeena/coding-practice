Given an array of integers nums, move all 0s to the end of the array while maintaining the relative order of the non-zero elements. Do this in-place without making a copy of the array.

Input: nums = [0,1,0,3,12] → Output: [1,3,12,0,0]
Input: nums = [0] → Output: [0]


/*
 * Problem: Move Zeroes
 * Pattern: Two Pointers
 * Time: O(n), Space: O(1)
 * mock-tested: fail→pass (bug was a double-increment: manually incrementing
 *   i/j inside the loop body AND relying on the for-loop's own increment,
 *   causing indices to be skipped and silently miscounted on inputs where
 *   zeros/non-zeros weren't conveniently aligned)
 * Key insight: find first zero (i), find first non-zero after it (j), swap,
 *   then advance both pointers and repeat — relative order of non-zeros is
 *   preserved because swaps only ever move a zero rightward
 * Verified against: mixed array, all-zero single element, and (implicitly)
 *   no-zero array — all edge cases hold
 */



import java.util.*;

public class Practice{
    
    static void swap(int[] nums, int i, int j){
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    } 

    static void replaceZeroes(int[] nums){

        int n = nums.length;

        int i=0, j=0;

        for(i=0;i<n;i++){
            if(nums[i]==0) break;
        }
        
        for(j=i+1; j<n;j++){
            if(nums[j]!=0) break;
        }

        while(j < n){
            swap(nums, i, j);
            i++;
            while( j<n && nums[j]==0) j++;
        }

        return;
    }

    public static void main(String[] args){

      //  int[] nums = {1,3,0,0,5,0,0,2,1};
      //  int[] nums = {0,1,0,3,12};
            int[] nums = {0};
        replaceZeroes(nums);
        
        for(int x: nums) System.out.print(x + " ");
    }

}
