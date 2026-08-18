class Solution {
    public int largestInteger(int[] nums, int k) {
        int[] count = new int[51];

        int n = nums.length;

        // Check every subarray of size k
        for (int start = 0; start <= n - k; start++) {

            // Track which numbers appear in this subarray
            boolean[] present = new boolean[51];

            for (int j = start; j < start + k; j++) {
                present[nums[j]] = true;
            }

            // Count each number only once for this subarray
            for (int x = 0; x <= 50; x++) {
                if (present[x]) {
                    count[x]++;
                }
            }
        }

        // Find the largest number appearing in exactly one subarray
        for (int x = 50; x >= 0; x--) {
            if (count[x] == 1) {
                return x;
            }
        }

        return -1;
    }
}