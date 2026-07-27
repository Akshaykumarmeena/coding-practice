Given an array of integers nums sorted in non-decreasing order, find the starting and ending position of a given target. Return [-1,-1] if not found. Must run in O(log n).

    Input: nums = [5,7,7,8,8,10], target = 8 → Output: [3, 4]
    Input: nums = [5,7,7,8,8,10], target = 6 → Output: [-1, -1]

/*
 * Problem: Find First and Last Position of Element in Sorted Array
 * Pattern: Binary Search (find leftmost/rightmost via repeated narrowing)
 * Time: O(log n) — each of the left/right searches is itself O(log n),
 *   and they run a bounded number of times proportional to log n as well
 * Space: O(1)
 * mock-tested: fail→pass (bug was NOT in the binary search or the
 *   leftmost/rightmost algorithm itself — both were correct — the bug was
 *   a caller-side mistake: func's own left/right params were passed
 *   incorrectly from main, capping the search range short of the array's
 *   actual bounds)
 * Key insight: find any one match via standard binary search, then binary-
 *   search the sub-range to its left (for leftmost) and to its right (for
 *   rightmost), repeating until each direction fails to find further matches
 * Lesson: always double check the values actually being PASSED at the call
 *   site, not just the logic inside the function — a perfectly correct
 *   function can still fail from bad inputs at the call site
 */

import java.util.*;

public class Practice{    

    static int binarySearch(int[] nums, int target, int left, int right){


        while(left <= right){

            int mid = (left+right)/2;

            if(nums[mid] == target) return mid;

            else if(nums[mid] > target){
                right = mid-1;
            }
            else{
                left = mid+1;
            }
        }
        return -1;

    }

    static int[] func(int[] nums, int target, int left, int right){

        int mid = binarySearch(nums, target, 0, nums.length-1);

        int leftIndex = mid, rightIndex = mid;

        int result[]={-1,-1};
        
        if(mid!=-1){

            int tmp1 = mid, tmp2 = mid;

            while(tmp1!=-1){
                leftIndex = tmp1;
                tmp1 = binarySearch(nums, target, left, tmp1-1);
            }

            while(tmp2!=-1){
                rightIndex = tmp2;
                tmp2 = binarySearch(nums, target, tmp2+1, right);
            }
 
            result[0] = leftIndex;
            result[1] = rightIndex;
        }

        return result;

    }

    

    public static void main(String[] args){

        int[] nums = {1,2,3,4,5,6,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,7,8,9,10,11,12,13,14};
        
        int[] result = func(nums, 7, 0, nums.length-1);
        
        System.out.println(result[0]+ " "+ result[1]);
    }

}
