
import java.util.*;

class Solution {
    public int maxSum(List<Integer> nums, int k) {
        int mod = 1000000007;
        int[] bits = new int[32];

        for (int i = 0; i < nums.size(); i++) {
            int num = nums.get(i);

            for (int b = 0; b < 32; b++) {
                if ((num & (1 << b)) != 0) {
                    bits[b]++;
                }
            }
        }

        long ans = 0;

        for (int i = 0; i < k; i++) {
            long num = 0;

            for (int b = 0; b < 32; b++) {
                if (bits[b] > 0) {
                    num += (1L << b);
                    bits[b]--;
                }
            }

            ans = (ans + num * num) % mod;
        }

        return (int) ans;
    }
}
