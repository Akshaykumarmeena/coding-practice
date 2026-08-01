Each element in the array appears twice, except for one element which appears only once.

Given a non-empty array of integers nums, every element appears twice except for one. Find that single one.

Your algorithm should have a linear runtime complexity (O(n)). Could you implement it without using extra memory (O(1) space)?

Input: nums = [2,2,1] → Output: 1
Input: nums = [4,1,2,1,2] → Output: 4
Input: nums = [1] → Output: 1

1 <= nums.length <= 3 * 10^4
-3 * 10^4 <= nums[i] <= 3 * 10^4

/*
 * Problem: Single Number
 * Pattern: Bit Manipulation — XOR
 * Constraints: 1 <= nums.length <= 3*10^4, -3*10^4 <= nums[i] <= 3*10^4,
 *   every element appears exactly twice except one (appears once)
 * Time: O(n), Space: O(1)
 * mock-tested: pass, first attempt, clean
 * Key insight: XOR properties — a^a=0 (a number XORed with itself cancels
 *   to zero), a^0=a (XOR with zero is identity). XORing the entire array
 *   together: every number appearing TWICE cancels itself out to 0, and
 *   0 XORed with the single unique number just leaves that number behind.
 *   Order doesn't matter (XOR is commutative and associative).
 */

  ```java
static int getUnique(int[] nums){

        int n = nums.length;
        int result = 0;
        
        for(int i=0; i < n; i++){
            result = result^nums[i];
        }

        return result;

    }
```
