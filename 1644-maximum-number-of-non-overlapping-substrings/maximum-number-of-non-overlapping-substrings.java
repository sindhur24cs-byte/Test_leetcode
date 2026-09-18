class Solution {
    public List<String> maxNumOfSubstrings(String s) {
        int n = s.length();

        int[] first = new int[26];
        int[] last = new int[26];

        Arrays.fill(first, n);
        Arrays.fill(last, -1);

        // First and last occurrence
        for (int i = 0; i < n; i++) {
            int ch = s.charAt(i) - 'a';
            first[ch] = Math.min(first[ch], i);
            last[ch] = i;
        }

        List<int[]> intervals = new ArrayList<>();

        // Build valid intervals
        for (int c = 0; c < 26; c++) {
            if (first[c] == n) continue;

            int l = first[c];
            int r = last[c];
            boolean valid = true;

            for (int i = l; i <= r; i++) {
                int ch = s.charAt(i) - 'a';

                if (first[ch] < l) {
                    valid = false;
                    break;
                }

                r = Math.max(r, last[ch]);
            }

            if (valid) {
                intervals.add(new int[]{l, r});
            }
        }

        // Sort by ending index
        intervals.sort((a, b) -> {
            if (a[1] == b[1]) return a[0] - b[0];
            return a[1] - b[1];
        });

        List<String> ans = new ArrayList<>();
        int end = -1;

        // Greedy interval scheduling
        for (int[] in : intervals) {
            if (in[0] > end) {
                ans.add(s.substring(in[0], in[1] + 1));
                end = in[1];
            }
        }

        return ans;
    }
}