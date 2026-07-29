You are given a 0-indexed integer array gas and an integer array cost, both of length n. There are n gas stations along a circular route, where gas[i] is the amount of gas at station i, and cost[i] is the gas needed to travel from station i to station i+1. You begin the journey with an empty tank at one of the gas stations.

Return the starting gas station's index if you can travel around the circuit once in the clockwise direction, otherwise return -1. If a solution exists, it is guaranteed to be unique.

      Input: gas = [1,2,3,4,5], cost = [3,4,5,1,2]
      Output: 3
      Explanation: Start at station 3: tank=4-1=3. Drive to 4: tank=3+5-2=6.
                   Drive to 0: tank=6+1-3=4. Drive to 1: tank=4+2-4=2.
                   Drive to 2: tank=2+3-5=0. Made it back to 3, total gas
                   consumed equals total gas gained. Valid.
      
      Input: gas = [2,3,4], cost = [3,4,3]
      Output: -1
      Explanation: No starting station allows completing the circuit.



  /*
 * Problem: Gas Station
 * Pattern: Brute force simulation (greedy O(n) alternative exists)
 * Time: O(n²) worst case, Space: O(1)
 * mock-tested: fail→pass (1 round — tank not reset between different
 *   starting-point attempts, causing corrupted state to carry over)
 * Key insight (brute force): for each candidate start, simulate the full
 *   loop, tracking tank; if it ever goes negative, that start fails
 * Follow-up (greedy O(n)): if sum(gas) < sum(cost), no solution exists.
 *   Otherwise, track a running total; whenever it goes negative, the
 *   candidate start must be AFTER the current position (reset start to
 *   i+1, running total to 0) — a valid unique start is guaranteed to exist
 */

  ```java
import java.util.*;

public class Practice{
    
    static int gasStation(int[] gas, int[] cost){

        int n = gas.length;

        int start = 0;

        for(int i=0; i<n; i++){

            int p = 0;
            int tank = 0;
            int j = i;
            while(p<n){

                if(j==n) j=0;

                tank+= gas[j]-cost[j];

                if(tank < 0) break;

                p++;
                j++;
            }

            if(p==n){ start = i; break; }
            else start = -1;
        }

       return start;
    }

    public static void main(String[] args){

    //    int[] gas = {1,2,3,4,5};
    //    int[] cost = {3,4,5,1,2};
       int[] gas = {2,3,4};
       int[] cost = {3,4,3};
        
       System.out.println(gasStation(gas, cost));
    }

}

```




/*
 * Problem: Gas Station
 * Pattern: Greedy — single-pass with reset-on-failure
 * Time: O(n), Space: O(1)
 * mock-tested: brute force O(n²) pass→pass; optimal O(n) version:
 *   fail→fail→pass across several rounds (infinite loop from a backward
 *   "jump to j" when wraparound made j < i; then a missing tank-reset bug
 *   that silently produced a wrong start index despite passing the overall
 *   feasibility check)
 *
 * Key insight: track TWO separate running sums in one pass —
 *   `total` = sum over the WHOLE array of (gas[i]-cost[i]) — tells you if
 *     ANY valid start exists at all (if total < 0, answer is -1, full stop)
 *   `tank`  = running sum from your CURRENT CANDIDATE start only
 *
 * Whenever tank goes negative: the candidate start (and every station up to
 * and including the current index) is proven impossible as a starting point.
 * Reset candidate start to i+1, and reset tank to 0 — a clean slate, never
 * carry over the deficit into the next candidate's calculation.
 *
 * At the end: if total < 0 → -1 (infeasible overall).
 * Otherwise the final candidate start is GUARANTEED correct — no need to
 * verify it separately, and no wraparound/re-simulation needed at all.
 *
 * Recognize this pattern when: "find a starting point such that a running
 * quantity never goes negative" + brute force would be O(n²) restart-from-
 * scratch. Ask: can one failure rule out an entire range of candidates,
 * not just the one being tested right now?
 */
static int gasStation(int[] gas, int[] cost){
    int n = gas.length;
    int start = 0;
    int total = 0;
    int tank = 0;

    for(int i = 0; i < n; i++){
        total += gas[i] - cost[i];
        tank += gas[i] - cost[i];

        if(tank < 0){
            start = i + 1;
            tank = 0;
        }
    }

    if(total < 0) return -1;
    return start;
}
