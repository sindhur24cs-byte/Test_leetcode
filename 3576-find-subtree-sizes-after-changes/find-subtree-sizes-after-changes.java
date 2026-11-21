class Solution {
    public int[] findSubtreeSizes(int[] parent, String s) {
        int n = parent.length;

        ArrayList<Integer>[] adj = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            adj[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            adj[parent[i]].add(i);
        }

        int[] newParent = parent.clone();
        int[] last = new int[26];

        for (int i = 0; i < 26; i++) {
            last[i] = -1;
        }

        dfs(0, adj, s, last, newParent);

        ArrayList<Integer>[] tree = new ArrayList[n];

        for (int i = 0; i < n; i++) {
            tree[i] = new ArrayList<>();
        }

        for (int i = 1; i < n; i++) {
            tree[newParent[i]].add(i);
        }

        int[] ans = new int[n];

        dfsSize(0, tree, ans);

        return ans;
    }

    void dfs(int node, ArrayList<Integer>[] adj, String s,
             int[] last, int[] newParent) {

        int ch = s.charAt(node) - 'a';
        int old = last[ch];

        if (node != 0 && old != -1) {
            newParent[node] = old;
        }

        last[ch] = node;

        for (int child : adj[node]) {
            dfs(child, adj, s, last, newParent);
        }

        last[ch] = old;
    }

    int dfsSize(int node, ArrayList<Integer>[] tree, int[] ans) {
        ans[node] = 1;

        for (int child : tree[node]) {
            ans[node] += dfsSize(child, tree, ans);
        }

        return ans[node];
    }
}