import java.util.*;

public class Solution {
    public String smallestNumber(String num, long t) {
        // Step 1: Prime factorize t
        int c2 = 0, c3 = 0, c5 = 0, c7 = 0;
        long tempT = t;
        
        while (tempT % 2 == 0) { c2++; tempT /= 2; }
        while (tempT % 3 == 0) { c3++; tempT /= 3; }
        while (tempT % 5 == 0) { c5++; tempT /= 5; }
        while (tempT % 7 == 0) { c7++; tempT /= 7; }
        
        if (tempT > 1) return "-1";

        int n = num.length();
        int[] baseReq = new int[]{c2, c3, c5, c7};

        // Find the index of the first '0'
        int firstZero = num.indexOf('0');
        int maxL = (firstZero == -1) ? n : firstZero;

        // Pre-calculate prefix requirements state: prefReq[i] is state after num[0...i-1]
        int[][] prefReq = new int[maxL + 1][4];
        prefReq[0] = baseReq.clone();

        for (int i = 0; i < maxL; i++) {
            prefReq[i + 1] = prefReq[i].clone();
            consumeDigit(prefReq[i + 1], num.charAt(i) - '0');
        }

        // Try to match prefix of length L down from maxL
        for (int L = maxL; L >= 0; L--) {
            int[] curReq = prefReq[L];

            if (L == n) {
                if (isFeasible(curReq, 0)) return num;
                continue;
            }

            int startDigit = num.charAt(L) - '0' + 1;

            for (int d = startDigit; d <= 9; d++) {
                int[] nextReq = curReq.clone();
                consumeDigit(nextReq, d);

                int remLen = n - 1 - L;
                if (isFeasible(nextReq, remLen)) {
                    StringBuilder sb = new StringBuilder();
                    sb.append(num, 0, L);
                    sb.append(d);
                    fillSuffixFast(sb, nextReq, remLen);
                    return sb.toString();
                }
            }
        }

        // If no valid number of length n, build length max(n + 1, minLenForT)
        int minLenForT = getMinDigitsNeeded(baseReq);
        int targetLen = Math.max(n + 1, minLenForT);
        
        StringBuilder sb = new StringBuilder();
        fillSuffixFast(sb, baseReq, targetLen);
        return sb.toString();
    }

    private void consumeDigit(int[] req, int d) {
        if (d == 2) { req[0] = Math.max(0, req[0] - 1); }
        else if (d == 3) { req[1] = Math.max(0, req[1] - 1); }
        else if (d == 4) { req[0] = Math.max(0, req[0] - 2); }
        else if (d == 5) { req[2] = Math.max(0, req[2] - 1); }
        else if (d == 6) { req[0] = Math.max(0, req[0] - 1); req[1] = Math.max(0, req[1] - 1); }
        else if (d == 7) { req[3] = Math.max(0, req[3] - 1); }
        else if (d == 8) { req[0] = Math.max(0, req[0] - 3); }
        else if (d == 9) { req[1] = Math.max(0, req[1] - 2); }
    }

    private int getMinDigitsNeeded(int[] req) {
        int r2 = req[0], r3 = req[1];
        int count = req[2] + req[3];
        
        count += r2 / 3;
        r2 %= 3;
        
        count += r3 / 2;
        r3 %= 2;

        if (r2 == 2 && r3 == 1) count += 2;
        else if (r2 == 1 && r3 == 1) count += 1;
        else count += (r2 > 0 ? 1 : 0) + (r3 > 0 ? 1 : 0);
        
        return count;
    }

    private boolean isFeasible(int[] req, int availLen) {
        return getMinDigitsNeeded(req) <= availLen;
    }

    // Direct O(1) construction of the smallest suffix
    private void fillSuffixFast(StringBuilder sb, int[] req, int remLen) {
        int needed = getMinDigitsNeeded(req);
        int ones = remLen - needed;
        
        for (int i = 0; i < ones; i++) {
            sb.append('1');
        }

        // Generate exact required digits greedily for remaining positions
        int remainingSlots = needed;
        int[] curReq = req.clone();
        for (int i = 0; i < remainingSlots; i++) {
            for (int d = 1; d <= 9; d++) {
                int[] nextReq = curReq.clone();
                consumeDigit(nextReq, d);
                if (getMinDigitsNeeded(nextReq) <= remainingSlots - 1 - i) {
                    sb.append(d);
                    curReq = nextReq;
                    break;
                }
            }
        }
    }
}