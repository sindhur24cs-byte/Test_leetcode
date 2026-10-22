class Solution {
    public int firstMissingPositive(int[] nums) {
        int n = nums.length;
        
        // Step 1: Replace numbers <= 0 or > n with n + 1
        for (int i = 0; i < n; i++) {
            if (nums[i] <= 0 || nums[i] > n) {
                nums[i] = n + 1;
            }
        }
        
        // Step 2: Mark presence by negating the value at the corresponding index
        for (int i = 0; i < n; i++) {
            int val = Math.abs(nums[i]);
            if (val <= n) {
                // Since indices are 0-based, number `val` maps to index `val - 1`
                int idx = val - 1;
                if (nums[idx] > 0) {
                    nums[idx] = -nums[idx];
                }
            }
        }
        
        // Step 3: Find the first index with a positive value
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                return i + 1;
            }
        }
        
        // If all numbers from 1 to n are present, the answer is n + 1
        return n + 1;
    }
}