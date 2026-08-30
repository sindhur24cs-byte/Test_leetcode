class Solution {
    public int minimumDeletions(int[] nums) {
        int n = nums.length;

        int minIndex = 0;
        int maxIndex = 0;

        // Find minimum and maximum indices
        for (int i = 1; i < n; i++) {
            if (nums[i] < nums[minIndex]) {
                minIndex = i;
            }

            if (nums[i] > nums[maxIndex]) {
                maxIndex = i;
            }
        }

        // Make minIndex the smaller index
        if (minIndex > maxIndex) {
            int temp = minIndex;
            minIndex = maxIndex;
            maxIndex = temp;
        }

        // Option 1: Remove both from the front
        int front = maxIndex + 1;

        // Option 2: Remove both from the back
        int back = n - minIndex;

        // Option 3: Remove smaller index from front
        // and larger index from back
        int both = (minIndex + 1) + (n - maxIndex);

        return Math.min(front, Math.min(back, both));
    }
}