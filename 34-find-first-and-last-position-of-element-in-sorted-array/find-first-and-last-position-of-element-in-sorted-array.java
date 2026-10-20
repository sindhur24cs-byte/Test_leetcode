class Solution {
    public int[] searchRange(int[] nums, int target) {
        int first = find(nums, target, true);
        int last = find(nums, target, false);
        return new int[]{first, last};
    }

    public int find(int[] nums, int target, boolean left) {
        int l = 0, r = nums.length - 1, ans = -1;

        while (l <= r) {
            int m = l + (r - l) / 2;

            if (nums[m] == target) {
                ans = m;
                if (left) r = m - 1;
                else l = m + 1;
            } else if (nums[m] < target) l = m + 1;
            else r = m - 1;
        }
        return ans;
    }
}