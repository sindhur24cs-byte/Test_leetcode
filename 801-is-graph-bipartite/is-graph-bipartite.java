import java.util.*;

class Solution {
    public boolean isBipartite(int[][] graph) {
        Queue<Integer> q = new LinkedList<>();
        int n = graph.length;
        int[] color = new int[n];

        for (int i = 0; i < n; i++) {
            if (color[i] != 0) continue;

            color[i] = 1;
            q.add(i);

            while (!q.isEmpty()) {
                int node = q.poll();

                for (int nei : graph[node]) {
                    if (color[nei] == 0) {
                        color[nei] = -color[node];
                        q.add(nei);
                    } 
                    else if (color[nei] == color[node]) {
                        return false;
                    }
                }
            }
        }

        return true;
    }
}