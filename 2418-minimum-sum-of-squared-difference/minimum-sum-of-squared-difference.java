class Solution {
    public long minSumSquareDiff(int[] nums1, int[] nums2, int k1, int k2) {
        int n = nums1.length;
        long k = (long) k1 + k2;
        int[] diff = new int[n];

        int max = 0;

        for (int i = 0; i < n; i++) {
            diff[i] = Math.abs(nums1[i] - nums2[i]);
            max = Math.max(max, diff[i]);
        }

        if (k >= max * 1L * n) {
            return 0;
        }

        int low = 0;
        int high = max;

        while (low < high) {
            int mid = (low + high) / 2;
            long need = 0;

            for (int i = 0; i < n; i++) {
                if (diff[i] > mid) {
                    need += diff[i] - mid;
                }
            }

            if (need <= k) {
                high = mid;
            } else {
                low = mid + 1;
            }
        }

        long ans = 0;
        long used = 0;

        for (int i = 0; i < n; i++) {
            if (diff[i] > low) {
                used += diff[i] - low;
                diff[i] = low;
            }
            ans += (long) diff[i] * diff[i];
        }

        long remaining = k - used;

        for (int i = 0; i < n && remaining > 0; i++) {
            if (diff[i] == low && low > 0) {
                ans -= (long) low * low;
                ans += (long) (low - 1) * (low - 1);
                remaining--;
            }
        }

        return ans;
    }
}