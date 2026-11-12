
class Solution {
    public List<List<Integer>> getSkyline(int[][] buildings) {

        List<List<Integer>> ans = new ArrayList<>();

        TreeSet<Integer> points = new TreeSet<>();

        for (int i = 0; i < buildings.length; i++) {
            points.add(buildings[i][0]);
            points.add(buildings[i][1]);
        }

        int prev = 0;

        for (int x : points) {
            int max = 0;

            for (int i = 0; i < buildings.length; i++) {
                if (buildings[i][0] <= x && x < buildings[i][1]) {
                    max = Math.max(max, buildings[i][2]);
                }
            }

            if (max != prev) {
                ans.add(Arrays.asList(x, max));
                prev = max;
            }
        }

        return ans;
    }
}
