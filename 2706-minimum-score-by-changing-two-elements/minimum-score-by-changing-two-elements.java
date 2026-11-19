
import java.util.*;

class Solution {
    public int minimizeSum(int[] nums) {
        Arrays.sort(nums);

        int n = nums.length;

        int ans = Integer.MAX_VALUE;

        for (int i = 0; i < 3; i++) {
            ans = Math.min(ans, nums[n - 1 - (2 - i)] - nums[i]);
        }

        return ans;
    }
}
