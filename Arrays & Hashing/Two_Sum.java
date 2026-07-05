Given an array of integers nums and an integer target, return the indices of the two numbers that add up to target. You may assume exactly one valid answer exists, and you can't use the same element twice.

  Example:
    Input: nums = [2, 7, 11, 15], target = 9
    Output: [0, 1]
    Explanation: nums[0] + nums[1] = 2 + 7 = 9


/*
 * Problem: Two Sum
 * Pattern: HashMap
 * Time: O(n), Space: O(n)
 * Key insight: for each element, check if (target - nums[i]) already exists in map
 * Gotcha: check BEFORE inserting to avoid using same element twice
 * Gotcha: uninitialized int[] is [0,0] not empty — use [-1,-1] as sentinel
 */

  
  public int[] twoSum(int[] nums, int target){

          HashMap<Integer,Integer> map = new HashMap<>();

          int[] result = new int[2];

          Arrays.fill(result,-1);

          for(int i=0;i<nums.length;i++){
            if(map.containsKey(target-nums[i])){
                result[0]=map.get(target-nums[i]);
                result[1]=i;
                return result;
            }
            else map.put(nums[i],i);
          }

           return result;

        }
