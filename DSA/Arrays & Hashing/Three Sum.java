Given an integer array nums, return all unique triplets [a, b, c] such that they add up to zero.
      
  
  Example:
      Input: nums = [-1, 0, 1, 2, -1, -4]
      Output: [[-1, -1, 2], [-1, 0, 1]]
      
      Input: nums = [0, 0, 0]
      Output: [[0, 0, 0]]


/*
 * Problem: Three Sum
 * Pattern: Sorting + Two Pointers
 * Time: O(n²), Space: O(n)
 * Key insight: fix i, run Two Sum II on rest with target = -nums[i]
 * Gotcha: skip duplicate i AFTER inner loop, with i++ at end of outer loop
 * Gotcha: skip duplicate j and k ONLY after finding valid triplet, THEN j++ k--
 * Gotcha: bounds check order matters — j<k must come before array access in while conditions
 * Gotcha: break early if nums[i] > 0 — sorted array means no triplet can sum to 0
 */


static ArrayList<int[]> triplets(int[] num){
        int i=0,j=i+1,k=num.length-1;
        
        if(k<2) return null;
        
        Arrays.sort(num);
        
        ArrayList<int[]> result = new ArrayList<>();
        
        while(i < num.length-2 && num[i] <= 0){
            
            int target = 0 - num[i];
            
            while(j<k){
                if((num[j] + num[k]) < target) j++;
                else if((num[j] + num[k]) > target) k--;
                else{
                    result.add(new int[]{num[i], num[j], num[k]});
                    while(j<k && num[j]==num[j+1]) j++;
                    while(j<k && num[k]==num[k-1]) k--;
                    j++;
                    k--;
                }
            }
            while(i<num.length-2 && num[i]==num[i+1]) i++;
            i++;
            j=i+1; k=num.length-1;
            
        }
        
        return result;
        
    }
