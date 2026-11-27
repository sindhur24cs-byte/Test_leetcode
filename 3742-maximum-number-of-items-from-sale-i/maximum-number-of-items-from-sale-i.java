
import java.util.*;

class Solution {
    public int maximumSaleItems(int[][] items, int budget) {

        int n = items.length;
        int[][] arr = new int[n][3];

        for (int i = 0; i < n; i++) {
            int count = 1;

            for (int j = 0; j < n; j++) {
                if (i != j && items[j][0] % items[i][0] == 0) {
                    count++;
                }
            }

            arr[i][0] = items[i][0];
            arr[i][1] = items[i][1];
            arr[i][2] = count;
        }

        Arrays.sort(arr, (a, b) -> a[1] - b[1]);

        int INF = -1000000000;
        int[] dp = new int[budget + 1];

        Arrays.fill(dp, INF);
        dp[0] = 0;

        int ans = 0;
        int index = n - 1;

        while (index >= 0) {

            int price = arr[index][1];

            int[] cur = new int[budget + 1];
            Arrays.fill(cur, INF);

            int start = index;

            while (index >= 0 && arr[index][1] == price) {
                index--;
            }

            for (int k = start; k > index; k--) {

                int value = arr[k][2];

                for (int c = budget; c >= price; c--) {

                    if (cur[c - price] != INF) {
                        cur[c] = Math.max(cur[c],
                                cur[c - price] + value);
                    }

                    if (dp[c - price] != INF) {
                        cur[c] = Math.max(cur[c],
                                dp[c - price] + value);
                    }
                }
            }

            for (int c = 0; c <= budget; c++) {
                if (cur[c] != INF) {
                    int total = cur[c] + (budget - c) / price;
                    ans = Math.max(ans, total);
                }
            }

            for (int c = 0; c <= budget; c++) {
                dp[c] = Math.max(dp[c], cur[c]);
            }
        }

        return ans;
    }
}
