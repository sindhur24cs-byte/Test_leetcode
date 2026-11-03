class Solution {
    public int reverse(int x) {

        String s = String.valueOf(x);

        if (x < 0) {
            s = s.substring(1);
        }

        StringBuilder sb = new StringBuilder(s);
        sb.reverse();

        try {
            int ans = Integer.parseInt(sb.toString());

            if (x < 0) {
                return -ans;
            }

            return ans;
        }
        catch (Exception e) {
            return 0;
        }
    }
}