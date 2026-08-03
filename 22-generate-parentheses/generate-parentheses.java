import java.util.*;

class Solution {

    public void solve(int open, int close, int n, String s, List<String> ans) {
        if (s.length() == 2 * n) {
            ans.add(s);
            return;
        }

        if (open < n) {
            solve(open + 1, close, n, s + "(", ans);
        }

        if (close < open) {
            solve(open, close + 1, n, s + ")", ans);
        }
    }

    public List<String> generateParenthesis(int n) {
        List<String> ans = new ArrayList<>();
        solve(0, 0, n, "", ans);
        return ans;
    }
}