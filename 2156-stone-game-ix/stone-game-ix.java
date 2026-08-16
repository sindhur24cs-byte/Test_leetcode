class Solution {
    public boolean stoneGameIX(int[] stones) {
        int[] count = new int[3];

        for (int x : stones) {
            count[x % 3]++;
        }

        // Even number of stones divisible by 3
        if (count[0] % 2 == 0) {
            return count[1] > 0 && count[2] > 0;
        }

        // Odd number of stones divisible by 3
        return Math.abs(count[1] - count[2]) > 2;
    }
}