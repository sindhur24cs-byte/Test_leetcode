import java.util.*;

class Solution {
    private List<List<Integer>> adj;
    private int[] tin, tout, depth, rootMask, bit;
    private int[][] up;
    private int timer = 0;
    private int LOGN = 17;
    private char[] sChars;
    private int n;

    private void dfs(int u, int p, int currentMask) {
        tin[u] = ++timer;
        up[u][0] = p;
        for (int i = 1; i < LOGN; i++) {
            up[u][i] = up[up[u][i - 1]][i - 1];
        }

        int charBit = 1 << (sChars[u] - 'a');
        currentMask ^= charBit;
        rootMask[u] = currentMask;

        for (int v : adj.get(u)) {
            if (v != p) {
                depth[v] = depth[u] + 1;
                dfs(v, u, currentMask);
            }
        }
        tout[u] = timer;
    }

    private void updateBit(int idx, int val) {
        while (idx <= n + 1) {
            bit[idx] ^= val;
            idx += idx & (-idx);
        }
    }

    private int queryBit(int idx) {
        int res = 0;
        while (idx > 0) {
            res ^= bit[idx];
            idx -= idx & (-idx);
        }
        return res;
    }

    private int getCurrentMask(int u) {
        return rootMask[u] ^ queryBit(tin[u]);
    }

    private int getLca(int u, int v) {
        if (depth[u] < depth[v]) {
            int temp = u; u = v; v = temp;
        }
        for (int i = LOGN - 1; i >= 0; i--) {
            if (depth[u] - (1 << i) >= depth[v]) {
                u = up[u][i];
            }
        }
        if (u == v) return u;
        for (int i = LOGN - 1; i >= 0; i--) {
            if (up[u][i] != up[v][i]) {
                u = up[u][i];
                v = up[v][i];
            }
        }
        return up[u][0];
    }

    public List<Boolean> palindromePath(int n, int[][] edges, String s, String[] queries) {
        this.n = n;
        this.sChars = s.toCharArray();
        this.adj = new ArrayList<>();
        for (int i = 0; i < n; i++) adj.add(new ArrayList<>());

        for (int[] edge : edges) {
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        tin = new int[n];
        tout = new int[n];
        depth = new int[n];
        rootMask = new int[n];
        up = new int[n][LOGN];
        bit = new int[n + 2];

        dfs(0, 0, 0);

        List<Boolean> answer = new ArrayList<>();

        for (String q : queries) {
            String[] parts = q.split(" ");
            if (parts[0].equals("update")) {
                int u = Integer.parseInt(parts[1]);
                char newC = parts[2].charAt(0);
                char oldC = sChars[u];

                if (oldC != newC) {
                    int diff = (1 << (oldC - 'a')) ^ (1 << (newC - 'a'));
                    sChars[u] = newC;
                    updateBit(tin[u], diff);
                    updateBit(tout[u] + 1, diff);
                }
            } else {
                int u = Integer.parseInt(parts[1]);
                int v = Integer.parseInt(parts[2]);
                int lca = getLca(u, v);

                int maskU = getCurrentMask(u);
                int maskV = getCurrentMask(v);
                int lcaCharBit = 1 << (sChars[lca] - 'a');

                int pathMask = maskU ^ maskV ^ lcaCharBit;
                boolean isPalindrome = (pathMask == 0) || ((pathMask & (pathMask - 1)) == 0);
                answer.add(isPalindrome);
            }
        }

        return answer;
    }
}