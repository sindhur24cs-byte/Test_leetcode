import java.util.*;

class Solution {
    public int twoCitySchedCost(int[][] costs) {
        int ans = 0;
        int[] diff = new int[costs.length];

        for (int i = 0; i < costs.length; i++) {
            ans += costs[i][0];
            diff[i] = costs[i][1] - costs[i][0];
        }

        Arrays.sort(diff);

        for (int i = 0; i < costs.length / 2; i++) {
            ans += diff[i];
        }

        return ans;
    }
}