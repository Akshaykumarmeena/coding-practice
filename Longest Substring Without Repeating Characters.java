Given a string s, find the length of the longest substring that contains no repeating characters.
     
  
  Example:
      Input: s = "abcabcbb"
      Output: 3
      Explanation: "abc" is the longest substring without repeating characters
      
      Input: s = "bbbbb"
      Output: 1
      Explanation: "b" is the longest
      
      Input: s = "pwwkew"
      Output: 3
      Explanation: "wke"


  // Brute Force approach

  1st one - two nested loop
1st starting with the 1st character and moving forward, keep putting the element in a hashset if we found any element already exist we just calculate the size of hasset till now or we can do just j-i


time complexity O(n*n)
space Complexity O(n)


  // Optimised Method

  static int maxLen(String input){
        int i=0,j=1,maxLen=1;
        int n=input.length();
        
        if(n<2) return n;
        
        HashSet<Character> set = new HashSet<>();
        set.add(input.charAt(0));
        
        while(i!=n && j!=n){
            if(!set.contains(input.charAt(j))) {
                set.add(input.charAt(j));
                if((j-i+1) > maxLen) maxLen = j-i+1;
                j++;
                }
            else {
                while(set.contains(input.charAt(j))){
                        set.remove(input.charAt(i));
                        i++;
                    }
            }
        }
        
        return maxLen;
        
        
    }
