Design a data structure that supports the following operations, all in average O(1) time:

    insert(val) — inserts an item if not already present, returns true/false
    remove(val) — removes an item if present, returns true/false
    getRandom() — returns a random element from the current set of elements, each element having equal probability of being returned
    Example:
    RandomizedSet rs = new RandomizedSet();
    rs.insert(1);   // true
    rs.insert(2);   // true
    rs.remove(1);   // true
    rs.insert(2);   // false (already present)
    rs.getRandom(); // returns 2 (only element left)


/*
 * Problem: Insert Delete GetRandom O(1)
 * Pattern: HashMap + ArrayList combo (index-tracking swap-remove)
 * Time: O(1) average for all three ops. Space: O(n)
 * mock-tested: fail→fail→pass (3 rounds: off-by-one index/count desync +
 *   missing map.remove + wrong return types, then leftover `index` field
 *   reference + missing duplicate-check on insert)
 * Key insight: HashSet alone can't do O(1) random access (no indexed access
 *   into hash buckets) — ArrayList CAN, via list.get(randomIndex)
 * Key insight: removing from ArrayList is O(n) normally (shift), UNLESS you
 *   swap-with-last then pop-from-end — both O(1)
 * Key insight: map stores value→index (not index→value) so you can locate
 *   ANY value's position in O(1) before swapping
 * Gotcha: after swap, must update the SWAPPED element's new index in the map
 *   — forgetting this corrupts future lookups for that value
 */


import java.util.*;

public class Practice{

    private HashMap<Integer, Integer> map;
    private ArrayList<Integer> list;

    Practice(){
        this.map = new HashMap<>();
        this.list = new ArrayList<>();
    }

     boolean insert(int x){

        if(this.map.containsKey(x)) return false;
        
        this.list.add(x);
        this.map.put(x, list.size()-1);

        return true;
    }

    boolean remove(int x){

        int i;

        if(map.containsKey(x)){
            i = this.map.get(x);
        } else{
            System.out.println("Element doesn't exist");
            return false;
        }

        int tmp = this.list.get(list.size() - 1);
        this.list.set(i, tmp);
        
        this.map.compute(tmp, (key, value) -> i);

        this.list.remove(list.size()-1);
        map.remove(x);

        return true;

    }

    int getRandom(){

        Random random = new Random();
        int randomNum = random.nextInt(list.size());

        return this.list.get(randomNum);
    }

    public static void main(String[] args) { 

        Practice rs = new Practice();

        rs.insert(1);   // true
        rs.insert(2);   // true
        rs.remove(1);   // true
        rs.insert(2);   // false (already present)
        rs.getRandom(); // returns 2 (only element left)
    }

}
