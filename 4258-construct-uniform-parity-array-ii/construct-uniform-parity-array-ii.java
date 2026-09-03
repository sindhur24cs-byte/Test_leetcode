class Solution {
    public boolean uniformArray(int[] nums1) {
        int min = nums1[0];
        boolean hasOdd = false;
        boolean hasEven = false;

        for (int x : nums1) {
            min = Math.min(min, x);

            if (x % 2 == 0)
                hasEven = true;
            else
                hasOdd = true;
        }

        // All elements already have the same parity
        if (!hasOdd || !hasEven)
            return true;

        // Mixed parity: possible only if minimum is odd
        return min % 2 == 1;
    }
}