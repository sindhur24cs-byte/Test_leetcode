class Solution {
    public String countAndSay(int n) {
        String s = "1";

        for (int i = 2; i <= n; i++) {
            StringBuilder next = new StringBuilder();

            int j = 0;

            while (j < s.length()) {
                int count = 1;

                while (j + 1 < s.length() && s.charAt(j) == s.charAt(j + 1)) {
                    count++;
                    j++;
                }

                next.append(count);
                next.append(s.charAt(j));

                j++;
            }

            s = next.toString();
        }

        return s;
    }
}