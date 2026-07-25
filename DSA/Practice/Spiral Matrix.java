Given an m x n matrix, return all elements of the matrix in spiral order.

      Input:
      [[1,2,3],
       [4,5,6],
       [7,8,9]]
      Output: [1,2,3,6,9,8,7,4,5]
      
      Input:
      [[1,2,3,4],
       [5,6,7,8],
       [9,10,11,12]]
      Output: [1,2,3,4,8,12,11,10,9,5,6,7]

/*
 * Problem: Spiral Matrix
 * Pattern: Four-boundary shrinking traversal
 * Time: O(m*n), Space: O(1) extra (excluding output array)
 * mock-tested: pass, one round (only a return-type fix needed — logic clean
 *   on first attempt, verified correct via full trace on 4-column example)
 * Key insight: track top/bottom/left/right boundaries, traverse in a fixed
 *   4-direction cycle (right→down→left→up), shrinking the relevant boundary
 *   after each pass
 * Gotcha: guard each pass with count!=total — needed for non-square matrices
 *   where a boundary can "close" mid-cycle (e.g. more columns than rows)
 */



import java.util.*;

public class Practice{

   static int[] spiral(int[][] mat){

        int m = mat.length;
        int n = mat[0].length;

        int count = 0;
        int total = m*n;

        int top = 0, bottom = m-1, left = 0, right = n-1;

        int[] arr = new int[total];

        while(count!= total){
            
            if(count != total){
                for(int j=left; j<=right; j++){
                    arr[count] = mat[top][j];
                    count++;
                }
            }
            top = top + 1;

            if(count != total){
                for(int i=top; i<=bottom;i++){
                    arr[count] = mat[i][right];
                    count++;
                }
            }
            right = right - 1;

            if(count!= total){
                for(int j = right; j>=left; j--){
                    arr[count] = mat[bottom][j];
                    count++;
                }
            }
            bottom = bottom - 1;

            if(count!= total){
                for(int i = bottom; i>=top; i--){
                    arr[count] = mat[i][left];
                    count++;
                }
            }
            left = left + 1;

        }

        return arr;

    }

}
