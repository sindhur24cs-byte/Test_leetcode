class Solution {
    public int stoneGameII(int[] piles) {
        int n = piles.length;

        // Suffix sum: sum[i] = stones from i to n-1
        int[] sum = new int[n + 1];

        for (int i = n - 1; i >= 0; i--) {
            sum[i] = sum[i + 1] + piles[i];
        }

        // dp[i][m] = maximum stones current player can get
        // starting from index i with current M = m
        int[][] dp = new int[n][n + 1];

        for (int i = n - 1; i >= 0; i--) {
            for (int m = 1; m <= n; m++) {

                // Can take all remaining piles
                if (i + 2 * m >= n) {
                    dp[i][m] = sum[i];
                    continue;
                }

                int best = 0;

                // Try taking x piles
                for (int x = 1; x <= 2 * m && i + x <= n; x++) {

                    // Opponent gets the best possible result
                    int opponent = dp[i + x][Math.max(m, x)];

                    // Current player gets total remaining
                    // minus opponent's maximum
                    int current = sum[i] - opponent;

                    best = Math.max(best, current);
                }

                dp[i][m] = best;
            }
        }

        return dp[0][1];
    }
}