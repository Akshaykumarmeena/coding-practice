Given an array prices where prices[i] is the price of a stock on day i, return the maximum profit you can achieve by buying on one day and selling on a later day. If no profit is possible, return 0.
      

Example:
      Input: prices = [7, 1, 5, 3, 6, 4]
      Output: 5
      Explanation: Buy on day 2 (price=1), sell on day 5 (price=6), profit = 5
      
      Input: prices = [7, 6, 4, 3, 1]
      Output: 0
      Explanation: prices only decrease, no profit possible


/*
 * Problem: Best Time to Buy and Sell Stock
 * Pattern: Sliding Window (implicit)
 * Time: O(n), Space: O(1)
 * Key insight: track running minimum, at each day calculate profit if sold today
 * Gotcha: check profit BEFORE updating min — ensures buy day is always before sell day
 */



// Brute Force

static int profit(int[] prices){
        
        int profit=0,min=0,max=0;
        int n = prices.length;
        if(n<2) return 0;
        
        for(int i=0;i<n-1;i++){
            min = prices[i];
            max = prices[i];
            for(int j=i+1;j<n;j++){
                if(prices[j]>max) max=prices[j];
            }
            if((max-min) > profit) profit = max-min;
        }
        
        return profit;
    }

TIme complexity O(n*n)
Space Complexity O(1)





// Method 2

static int profit(int[] prices){
        
        int profit=0,min;
        int n = prices.length;
        if(n<2) return 0;
        
        min = prices[0];
        for(int i=1;i<n;i++){
            if((prices[i]-min) > profit) profit = prices[i]-min;
            if(prices[i]<min) min=prices[i];
        }
        
        
        return profit;
    }


