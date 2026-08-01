0 <= n <= 2^31 - 1

Given an integer n, return the number of 1 bits in its binary representation (also known as the Hamming Weight).

Input: n = 11 → Output: 3
Explanation: 11 in binary is 1011, which has three 1 bits

Input: n = 128 → Output: 1
Explanation: 128 in binary is 10000000, which has one 1 bit

Input: n = 2147483645 → Output: 30




/*
 * Problem: Number of 1 Bits (Hamming Weight)
 * Pattern: Bit Manipulation — n & (n-1) trick
 * Constraints: 0 <= n <= 2^31 - 1
 * Time: O(k) where k = number of 1 bits (best case, elegant version) or
 *   O(32) fixed (simple loop version). Space: O(1)
 *
 * mock-tested: original attempt had two bugs — Math.log-based bit-count
 *   (floating point precision risk near exact powers of 2) and
 *   int-as-boolean compile error in the check condition
 *
 * SIMPLE VERSION (always checks all 32 bits, avoids floating point risk):
 */
static int numberOf1Simple(int n){
    int count = 0;
    for(int i = 0; i < 32; i++){
        if((n & (1 << i)) != 0) count++;
    }
    return count;
}

/*
 * ELEGANT VERSION (only loops once per actual 1-bit present):
 * Key insight: n & (n-1) always clears exactly the LOWEST set bit,
 *   leaving everything else untouched. Repeating this until n becomes 0
 *   counts exactly how many 1-bits were originally present.
 *
 * WHY n & (n-1) clears the lowest set bit (derived via binary subtraction
 * borrowing, same logic as decimal borrowing e.g. 300-1=299):
 *   Subtracting 1 finds the rightmost 1 bit, flips it to 0, and flips
 *   every 0 bit below it (to the right) to 1 — everything above stays
 *   unchanged. ANDing n with this result: positions above the rightmost
 *   1 bit agree (unchanged in both) so they survive; the rightmost 1
 *   bit itself disagrees (1 in n, 0 in n-1) so it clears; positions
 *   below were already 0 in n, so ANDing gives 0 there regardless.
 *
 * This trick was NOT independently derivable from first principles in
 * one step — it's a well-known OBSERVED pattern (discovered via bit
 * manipulation exploration, then provable via the borrowing mechanism
 * above). Treat it as a memorized tool backed by understood reasoning,
 * not something to re-derive live under interview pressure.
 */
static int numberOf1Elegant(int n){
    int count = 0;
    while(n != 0){
        n = n & (n - 1);
        count++;
    }
    return count;
}
