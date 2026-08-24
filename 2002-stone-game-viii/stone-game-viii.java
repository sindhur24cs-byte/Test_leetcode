class Solution {
    public int stoneGameVIII(int[] stones) {

        int n = stones.length;

        long[] prefix = new long[n];

        prefix[0] = stones[0];

        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] + stones[i];
        }

        long[] dp = new long[n];

        // dp[i] = best score difference starting from prefix[i]
        dp[n - 1] = prefix[n - 1];

        for (int i = n - 2; i >= 1; i--) {
            dp[i] = Math.max(
                prefix[i] - dp[i + 1],
                dp[i + 1]
            );
        }

        return (int) dp[1];
    }
}