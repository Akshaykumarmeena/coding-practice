You are given an array of non-negative integers nums. You are initially at index 0. Each element nums[i] represents the maximum jump length from that position. Return true if you can reach the last index, false otherwise.

Input: nums = [2,3,1,1,4]  → Output: true
Explanation: jump 1 step to index 1, then 3 steps to the last index.

Input: nums = [3,2,1,0,4]  → Output: false
Explanation: you'll always arrive at index 3 with a jump length of 0, stuck.


/*
 * Problem: Jump Game
 * Pattern: Greedy (also solvable via DP, O(n²) — see alt version)
 * Time: O(n), Space: O(1)
 * mock-tested: DP version fail→pass (2 rounds: brace/syntax + off-by-one <=)
 *              Greedy version pass (1 round, self-derived after prompt)
 * Key insight: track maxReach = farthest index reachable so far, left to right
 * If i > maxReach at any point → stuck, return false
 * Else maxReach = Math.max(maxReach, i + nums[i])
 * Gotcha: reachable jump range from index i is [i+1, i+nums[i]] inclusive — use <=, not 
 */



  
  O(n*n)
  
  static boolean jump(int[] nums){

        int n = nums.length;
        boolean[] arr = new boolean[n];
        arr[n-1] = true;

        for(int i=n-2; i>=0; i--){
            for(int j=i+1; j<n && j<= i+nums[i]; j++){
                if(arr[j]){
                    arr[i] = true; 
                    break;
                } 
            }
        }
        
        return arr[0];
}


// Optimised method :Greedy problem

static boolean jump(int[] nums){

        int n = nums.length;
        
        int maxReach = nums[0];

        for(int i = 1; i < n; i++){

            if(i > maxReach) return false;
            maxReach = Math.max(maxReach, i + nums[i]);
        }
        
        return true;
}



