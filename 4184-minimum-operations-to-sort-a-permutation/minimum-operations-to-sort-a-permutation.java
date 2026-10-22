import java.util.*;

class Solution {
    public int minOperations(int[] nums) {
        int n = nums.length;
        int zero = 0;

        for (int i = 0; i < n; i++) {
            if (nums[i] == 0) {
                zero = i;
                break;
            }
        }

        int[][] dist = new int[2][n];

        for (int i = 0; i < 2; i++) {
            Arrays.fill(dist[i], -1);
        }

        Queue<int[]> q = new LinkedList<>();
        q.offer(new int[]{0, 0});
        dist[0][0] = 0;

        while (!q.isEmpty()) {
            int[] cur = q.poll();
            int rev = cur[0];
            int k = cur[1];

            int nextK = (k + 1) % n;

            if (dist[rev][nextK] == -1) {
                dist[rev][nextK] = dist[rev][k] + 1;
                q.offer(new int[]{rev, nextK});
            }

            int nextRev = 1 - rev;
            nextK = (n - k) % n;

            if (dist[nextRev][nextK] == -1) {
                dist[nextRev][nextK] = dist[rev][k] + 1;
                q.offer(new int[]{nextRev, nextK});
            }
        }

        int ans = Integer.MAX_VALUE;

        int k = zero;
        boolean ok = true;

        for (int i = 0; i < n; i++) {
            if (nums[(i + k) % n] != i) {
                ok = false;
                break;
            }
        }

        if (ok) {
            ans = Math.min(ans, dist[0][k]);
        }

        k = (n - 1 - zero + n) % n;
        ok = true;

        for (int i = 0; i < n; i++) {
            int index = n - 1 - ((i + k) % n);

            if (nums[index] != i) {
                ok = false;
                break;
            }
        }

        if (ok) {
            ans = Math.min(ans, dist[1][k]);
        }

        return ans == Integer.MAX_VALUE ? -1 : ans;
    }
}