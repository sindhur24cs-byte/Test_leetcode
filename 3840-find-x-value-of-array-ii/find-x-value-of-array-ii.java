class Solution {
    int k, n;
    long[][] cnt;
    int[] prod;

    public int[] resultArray(int[] nums, int k, int[][] queries) {
        this.k = k;
        n = nums.length;

        cnt = new long[4 * n][k];
        prod = new int[4 * n];

        build(1, 0, n - 1, nums);

        int[] ans = new int[queries.length];

        for (int i = 0; i < queries.length; i++) {
            int index = queries[i][0];
            int value = queries[i][1];
            int start = queries[i][2];
            int x = queries[i][3];

            update(1, 0, n - 1, index, value);

            Node res = query(1, 0, n - 1, start, n - 1);

            ans[i] = (int) res.cnt[x];
        }

        return ans;
    }

    void build(int node, int l, int r, int[] nums) {
        if (l == r) {
            prod[node] = nums[l] % k;
            cnt[node][prod[node]] = 1;
            return;
        }

        int mid = (l + r) / 2;

        build(node * 2, l, mid, nums);
        build(node * 2 + 1, mid + 1, r, nums);

        merge(node, node * 2, node * 2 + 1);
    }

    void update(int node, int l, int r, int index, int value) {
        if (l == r) {
            for (int i = 0; i < k; i++)
                cnt[node][i] = 0;

            prod[node] = value % k;
            cnt[node][prod[node]] = 1;
            return;
        }

        int mid = (l + r) / 2;

        if (index <= mid)
            update(node * 2, l, mid, index, value);
        else
            update(node * 2 + 1, mid + 1, r, index, value);

        merge(node, node * 2, node * 2 + 1);
    }

    void merge(int node, int left, int right) {
        for (int i = 0; i < k; i++)
            cnt[node][i] = cnt[left][i];

        for (int j = 0; j < k; j++) {
            int rem = (prod[left] * j) % k;
            cnt[node][rem] += cnt[right][j];
        }

        prod[node] = (prod[left] * prod[right]) % k;
    }

    Node query(int node, int l, int r, int ql, int qr) {
        if (ql <= l && r <= qr)
            return new Node(prod[node], cnt[node]);

        int mid = (l + r) / 2;

        if (qr <= mid)
            return query(node * 2, l, mid, ql, qr);

        if (ql > mid)
            return query(node * 2 + 1, mid + 1, r, ql, qr);

        Node a = query(node * 2, l, mid, ql, qr);
        Node b = query(node * 2 + 1, mid + 1, r, ql, qr);

        long[] res = new long[k];

        for (int i = 0; i < k; i++)
            res[i] = a.cnt[i];

        for (int j = 0; j < k; j++) {
            int rem = (a.prod * j) % k;
            res[rem] += b.cnt[j];
        }

        return new Node((a.prod * b.prod) % k, res);
    }

    static class Node {
        int prod;
        long[] cnt;

        Node(int prod, long[] cnt) {
            this.prod = prod;
            this.cnt = cnt.clone();
        }
    }
}