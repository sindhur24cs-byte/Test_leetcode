import java.util.*;

class Solution {
    public int maxNumberOfFamilies(int n, int[][] reservedSeats) {

        // Store reserved seats for only the rows that have reservations
        Map<Integer, Integer> map = new HashMap<>();

        for (int[] seat : reservedSeats) {
            int row = seat[0];
            int col = seat[1];

            // Set the bit corresponding to this seat
            map.put(row, map.getOrDefault(row, 0) | (1 << col));
        }

        int result = 0;

        // Rows without any reserved seats can fit 2 groups
        result = (n - map.size()) * 2;

        // Process rows having reserved seats
        for (int mask : map.values()) {

            // Check seats 2,3,4,5
            boolean left = (mask & 0b000000111100) == 0;

            // Check seats 6,7,8,9
            boolean right = (mask & 0b001111000000) == 0;

            // Check seats 4,5,6,7
            boolean middle = (mask & 0b000011110000) == 0;

            if (left && right) {
                // Both groups can be placed
                result += 2;
            } else if (left || middle || right) {
                // At least one group can be placed
                result += 1;
            }
        }

        return result;
    }
}