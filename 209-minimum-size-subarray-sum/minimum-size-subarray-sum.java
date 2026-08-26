class Solution {
    public int minSubArrayLen(int target, int[] nums) {
        int left = 0;
        int sum = 0;
        int min = nums.length + 1;

        for (int right = 0; right < nums.length; right++) {
            sum += nums[right];

            while (sum >= target) {
                int len = right - left + 1;

                if (len < min) {
                    min = len;
                }

                sum -= nums[left++];
            }
        }

        return min == nums.length + 1 ? 0 : min;
    }
}