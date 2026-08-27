import java.util.*;

class Solution {
    public String lexGreaterPermutation(String s, String target) {
        int n = s.length();
        int[] count = new int[26];

        for (char c : s.toCharArray()) {
            count[c - 'a']++;
        }

        char[] result = new char[n];

        // Try to keep the prefix equal to target
        for (int i = 0; i < n; i++) {
            int x = target.charAt(i) - 'a';

            if (count[x] > 0) {
                result[i] = target.charAt(i);
                count[x]--;
            } else {
                break;
            }

            // If this is the last position and result == target,
            // we need to backtrack.
            if (i == n - 1) {
                break;
            }
        }

        // Backtrack from right to left
        for (int i = n - 1; i >= 0; i--) {

            int[] freq = new int[26];

            // Restore all characters from s
            for (char c : s.toCharArray()) {
                freq[c - 'a']++;
            }

            // Match target prefix [0 ... i-1]
            boolean possible = true;

            for (int j = 0; j < i; j++) {
                int x = target.charAt(j) - 'a';

                if (freq[x] == 0) {
                    possible = false;
                    break;
                }

                freq[x]--;
            }

            if (!possible) {
                continue;
            }

            // Find smallest character greater than target[i]
            int current = target.charAt(i) - 'a';

            for (int c = current + 1; c < 26; c++) {

                if (freq[c] > 0) {
                    result = new char[n];

                    // Equal prefix
                    for (int j = 0; j < i; j++) {
                        result[j] = target.charAt(j);
                    }

                    // Make this position greater
                    result[i] = (char) ('a' + c);
                    freq[c]--;

                    // Put remaining characters in sorted order
                    int pos = i + 1;

                    for (int k = 0; k < 26; k++) {
                        while (freq[k] > 0) {
                            result[pos++] = (char) ('a' + k);
                            freq[k]--;
                        }
                    }

                    return new String(result);
                }
            }
        }

        return "";
    }
}