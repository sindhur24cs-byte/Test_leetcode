
import java.util.*;

class Solution {
    TreeMap<Integer, Integer> left = new TreeMap<>();
    TreeMap<Integer, Integer> right = new TreeMap<>();

    long sumLeft = 0;
    long sumRight = 0;
    int leftSize = 0;
    int rightSize = 0;

    void add(TreeMap<Integer, Integer> map, int val) {
        map.put(val, map.getOrDefault(val, 0) + 1);
    }

    void remove(TreeMap<Integer, Integer> map, int val) {
        int count = map.get(val);
        if (count == 1) {
            map.remove(val);
        } else {
            map.put(val, count - 1);
        }
    }

    void insert(int val) {
        if (leftSize == 0 || val <= left.lastKey()) {
            add(left, val);
            sumLeft += val;
            leftSize++;
        } else {
            add(right, val);
            sumRight += val;
            rightSize++;
        }
        balance();
    }

    void erase(int val) {
        if (left.containsKey(val)) {
            remove(left, val);
            sumLeft -= val;
            leftSize--;
        } else {
            remove(right, val);
            sumRight -= val;
            rightSize--;
        }
        balance();
    }

    void balance() {
        while (leftSize > rightSize + 1) {
            int val = left.lastKey();
            remove(left, val);
            add(right, val);
            sumLeft -= val;
            sumRight += val;
            leftSize--;
            rightSize++;
        }

        while (leftSize < rightSize) {
            int val = right.firstKey();
            remove(right, val);
            add(left, val);
            sumRight -= val;
            sumLeft += val;
            rightSize--;
            leftSize++;
        }
    }

    long getCost() {
        long median = left.lastKey();
        return median * leftSize - sumLeft
             + sumRight - median * rightSize;
    }

    public long minOperations(int[] nums, int x, int k) {
        int n = nums.length;
        long[] cost = new long[n - x + 1];

        for (int i = 0; i < x; i++) {
            insert(nums[i]);
        }

        cost[0] = getCost();

        for (int i = x; i < n; i++) {
            erase(nums[i - x]);
            insert(nums[i]);
            cost[i - x + 1] = getCost();
        }

        long INF = Long.MAX_VALUE / 4;
        long[][] dp = new long[n + 1][k + 1];

        for (int i = 0; i <= n; i++) {
            Arrays.fill(dp[i], INF);
        }

        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }

        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= k; j++) {
                dp[i][j] = dp[i - 1][j];

                if (i >= x && dp[i - x][j - 1] != INF) {
                    dp[i][j] = Math.min(
                        dp[i][j],
                        dp[i - x][j - 1] + cost[i - x]
                    );
                }
            }
        }

        return dp[n][k];
    }
}
