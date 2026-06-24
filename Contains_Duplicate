Given an integer array nums, return true if any value appears at least twice in the array, and false if every element is distinct.

Example:
      Input: nums = [1, 2, 3, 1]
      Output: true
      
      Input: nums = [1, 2, 3, 4]
      Output: false

/*
 * Problem: Contains Duplicate
 * Pattern: HashSet
 * Time: O(n), Space: O(n)
 * Key insight: HashSet not HashMap — you only need presence, not key-value mapping
 * Gotcha: HashSet.add() returns false if element already exists — can use as one-liner check
 */



```java
public boolean isDuplicate(int[] nums){
          
          if(nums.length<=1) return false;

           HashSet<Integer> set = new HashSet<>();         

          for(int i=0;i<nums.length;i++){
            if(set.contains(nums[i])){
                return true;
            }
            else set.add(nums[i]);
          }

           return false;

        }
```
