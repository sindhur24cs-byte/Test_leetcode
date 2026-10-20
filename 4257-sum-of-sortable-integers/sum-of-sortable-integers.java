import java.util.Arrays;

public class Solution {
    public int sortableIntegers(int[] nums) {
        int n = nums.length;
        int[] sortedNums = nums.clone();
        Arrays.sort(sortedNums);
        
        int totalSum = 0;
        
        // Check every divisor k of n
        for (int k = 1; k <= n; k++) {
            if (n % k != 0) continue;
            
            boolean isSortable = true;
            // Check each block of length k
            for (int i = 0; i < n; i += k) {
                if (!canRotateToMatch(nums, i, sortedNums, i, k)) {
                    isSortable = false;
                    break;
                }
            }
            
            if (isSortable) {
                totalSum += k;
            }
        }
        
        return totalSum;
    }
    
    // Checks if the block in nums starting at sStart can be rotated to match the block in sortedNums starting at tStart
    private boolean canRotateToMatch(int[] nums, int sStart, int[] sortedNums, int tStart, int k) {
        int drops = 0;
        int dropIdx = -1;
        
        // Count drops in the circular block
        for (int j = 0; j < k; j++) {
            int curr = nums[sStart + j];
            int next = nums[sStart + (j + 1) % k];
            if (curr > next) {
                drops++;
                dropIdx = j;
            }
        }
        
        // A cyclic shift of a sorted array can have at most 1 drop
        if (drops > 1) {
            return false;
        }
        
        // If drops == 0, the block is already non-decreasing.
        // If drops == 1, the rotation should start at (dropIdx + 1) % k.
        int startShift = (drops == 0) ? 0 : (dropIdx + 1) % k;
        
        for (int m = 0; m < k; m++) {
            if (nums[sStart + (startShift + m) % k] != sortedNums[tStart + m]) {
                return false;
            }
        }
        
        return true;
    }
}