class Solution {
    public long countCommas(long n) {
        long ans = 0;

        for (long start = 1000; start <= n; ) {
            long end = Math.min(n, start * 1000 - 1);

            long commas = 0;
            long temp = start;

            while (temp >= 1000) {
                commas++;
                temp /= 1000;
            }

            ans += (end - start + 1) * commas;

            if (start > Long.MAX_VALUE / 1000)
                break;

            start *= 1000;
        }

        return ans;
    }
}