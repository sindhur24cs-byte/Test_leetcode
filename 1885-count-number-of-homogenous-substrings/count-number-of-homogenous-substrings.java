class Solution {
    public int countHomogenous(String s) {
        int ans = 0;
        int mod = 1000000007;
        int left = 0;

        for (int right = left; right < s.length(); right++) {
            if (s.charAt(left) != s.charAt(right)) {
                left = right;
            }

            ans = (ans + right - left + 1) % mod;
        }

        return ans;
    }
}