class Solution {
    public int longestSubarray(int[] nums) {
        int len = 2;
        int j = 0;

        for (int i = 2; i < nums.length; i++) {
            if (nums[i] == nums[i - 1] + nums[i - 2]) {
                len = Math.max(len, i - j + 1);
            } else {
                j = i - 1;
            }
        }

        return len;
    }
}