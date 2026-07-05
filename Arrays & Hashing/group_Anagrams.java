Given an array of strings strs, group the anagrams together. You can return the answer in any order.
    
  Example:
    Input: strs = ["eat","tea","tan","ate","nat","bat"]
    Output: [["eat","tea","ate"],["tan","nat"],["bat"]]


/*
 * Problem: Group Anagrams
 * Pattern: HashMap with sorted string as key
 * Time: O(n * k log k), Space: O(n * k)
 * Key insight: sorted version of anagrams is always identical — use as HashMap key
 * Gotcha: char[] as key fails — Java compares object identity not contents
 * Gotcha: List is an interface — instantiate with new ArrayList<>()
 * Gotcha: use computeIfAbsent() for cleaner grouping logic
 */

    

static List<List<String>> groupAnagram(List<String> input){
        
         HashMap<String,List<String>> map = new HashMap<>();
         
         for(int i=0;i<input.size();i++){
             char[] chars = input.get(i).toCharArray();
             Arrays.sort(chars);
             
             String sorted = new String(chars);
             
             if(!map.containsKey(sorted)){
                 map.put(sorted, new ArrayList<String>());
             }
                 map.get(sorted).add(input.get(i));
         }
         
         List<List<String>> output = new ArrayList<>(map.values());
         
         return output;
        
        
    }


i took a lot of hints of functions of hashmap and lists but not for the logics and flow
