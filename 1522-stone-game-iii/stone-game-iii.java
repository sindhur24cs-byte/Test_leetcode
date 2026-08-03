class Solution {
    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] dp = new int[n + 1]; // dp[i] = max score difference starting at index i
        
        // Build DP table from right to left
        for (int i = n - 1; i >= 0; i--) {
            dp[i] = Integer.MIN_VALUE;
            int currentTake = 0;
            
            // Try taking 1, 2, or 3 stones
            for (int k = 1; k <= 3 && i + k <= n; k++) {
                currentTake += stoneValue[i + k - 1];
                dp[i] = Math.max(dp[i], currentTake - dp[i + k]);
            }
        }
        
        // Evaluate Alice's advantage from index 0
        if (dp[0] > 0) {
            return "Alice";
        } else if (dp[0] < 0) {
            return "Bob";
        } else {
            return "Tie";
        }
    }
}