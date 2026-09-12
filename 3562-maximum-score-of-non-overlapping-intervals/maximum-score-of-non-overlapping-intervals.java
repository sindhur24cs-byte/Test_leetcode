class Solution {
    public int[] maximumWeight(java.util.List<java.util.List<Integer>> intervals) {
        int n = intervals.size();

        int[][] a = new int[n][4];

        for (int i = 0; i < n; i++) {
            a[i][0] = intervals.get(i).get(0);
            a[i][1] = intervals.get(i).get(1);
            a[i][2] = intervals.get(i).get(2);
            a[i][3] = i;
        }

        java.util.Arrays.sort(a, (x, y) -> {
            if (x[1] != y[1])
                return Integer.compare(x[1], y[1]);
            return Integer.compare(x[3], y[3]);
        });

        int[] prev = new int[n];

        for (int i = 0; i < n; i++) {
            int l = 0, r = i - 1;
            prev[i] = -1;

            while (l <= r) {
                int m = (l + r) / 2;

                if (a[m][1] < a[i][0]) {
                    prev[i] = m;
                    l = m + 1;
                } else {
                    r = m - 1;
                }
            }
        }

        long[][] dp = new long[5][n + 1];
        java.util.List<Integer>[][] best =
            new java.util.ArrayList[5][n + 1];

        for (int k = 0; k <= 4; k++) {
            for (int i = 0; i <= n; i++) {
                best[k][i] = new java.util.ArrayList<>();
            }
        }

        for (int i = 1; i <= n; i++) {
            for (int k = 0; k <= 4; k++) {
                dp[k][i] = dp[k][i - 1];
                best[k][i] =
                    new java.util.ArrayList<>(best[k][i - 1]);
            }

            for (int k = 1; k <= 4; k++) {
                int p = prev[i - 1] + 1;

                long value =
                    dp[k - 1][p] + a[i - 1][2];

                java.util.List<Integer> list =
                    new java.util.ArrayList<>(best[k - 1][p]);

                list.add(a[i - 1][3]);
                java.util.Collections.sort(list);

                if (value > dp[k][i] ||
                    (value == dp[k][i] &&
                     compare(list, best[k][i]) < 0)) {

                    dp[k][i] = value;
                    best[k][i] = list;
                }
            }
        }

        java.util.List<Integer> ans = best[0][n];

        for (int k = 1; k <= 4; k++) {
            if (dp[k][n] > dp[0][n] ||
                (dp[k][n] == dp[0][n] &&
                 compare(best[k][n], ans) < 0)) {

                dp[0][n] = dp[k][n];
                ans = best[k][n];
            }
        }

        int[] result = new int[ans.size()];

        for (int i = 0; i < ans.size(); i++)
            result[i] = ans.get(i);

        return result;
    }

    int compare(java.util.List<Integer> a,
                java.util.List<Integer> b) {

        int n = Math.min(a.size(), b.size());

        for (int i = 0; i < n; i++) {
            if (!a.get(i).equals(b.get(i)))
                return Integer.compare(a.get(i), b.get(i));
        }

        return Integer.compare(a.size(), b.size());
    }
}