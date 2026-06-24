Given a sorted (ascending order) array of integers numbers and a target integer target, return the indices of the two numbers that add up to target. Return them as [index1, index2] where index1 < index2. Indices are 1-based (first element is at index 1, not 0).
You must use O(1) extra space — meaning the HashMap approach from Two Sum I is off the table here.
    
  
  Example:
        Input: numbers = [2, 7, 11, 15], target = 9
        Output: [1, 2]
        Explanation: numbers[0] + numbers[1] = 2 + 7 = 9

  
  
  static int[] result(int[] nums, int target){
        
        int n = nums.length;
        
        int[] output={-1,-1};
        
        if(n<=1){
            return output;
        }
            
        int i=0,j=n-1;
        
        while(i<j){
            if(nums[i]+nums[j] < target) i++;
            else if(nums[i]+nums[j] > target) j--;
            else{
                output[0]=i+1;
                output[1]=j+1;
                return output;
            }
        }
        
        return output;
    }

Time Complexity O(n)
Space Complexity O(1)
