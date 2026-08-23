class Solution {
    public boolean sumGame(String num) {
        int n = num.length();
        int deltaS = 0;
        int deltaQ = 0;

        for (int i = 0; i < n / 2; i++) {
            if (num.charAt(i) == '?') {
                deltaQ++;
            } else {
                deltaS += num.charAt(i) - '0';
            }
        }

        for (int i = n / 2; i < n; i++) {
            if (num.charAt(i) == '?') {
                deltaQ--;
            } else {
                deltaS -= num.charAt(i) - '0';
            }
        }

        return (deltaS * 2 + deltaQ * 9) != 0;
    }
}