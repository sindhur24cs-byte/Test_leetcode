class Solution {
    public long maximumOr(int[] nums, int k) {
        int n = nums.length;
        long[] prefix = new long[n];
        long[] suffix = new long[n];

        prefix[0] = 0;
        for (int i = 1; i < n; i++) {
            prefix[i] = prefix[i - 1] | nums[i - 1];
        }

        suffix[n - 1] = nums[n - 1];
        for (int i = n - 2; i >= 0; i--) {
            suffix[i] = suffix[i + 1] | nums[i];
        }

        long ans = 0;
        for (int i = 0; i < n; i++) {
            long left = prefix[i];
            long right = 0;

            if (i < n - 1) {
                right = suffix[i + 1];
            }

            long current = left | right | ((long) nums[i] << k);
            ans = Math.max(ans, current);
        }

        return ans;
    }
}