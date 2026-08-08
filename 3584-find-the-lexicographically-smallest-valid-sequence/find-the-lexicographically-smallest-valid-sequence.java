class Solution {

    public int[] validSequence(String word1, String word2) {

        int n = word1.length();
        int m = word2.length();

        // right[j] = position where word2[j] can be matched
        // while matching the rest from right to left
        int[] right = new int[m];

        int i = n - 1;
        int j = m - 1;

        while (i >= 0 && j >= 0) {

            if (word1.charAt(i) == word2.charAt(j)) {
                right[j] = i;
                j--;
            }

            i--;
        }

        // If the last part cannot be matched
        // we will handle it while building the answer.

        int[] ans = new int[m];

        i = 0;
        j = 0;

        boolean changed = false;

        while (i < n && j < m) {

            // Character already matches
            if (word1.charAt(i) == word2.charAt(j)) {

                ans[j] = i;
                j++;
            }

            // Change this character
            else if (!changed) {

                // There must be enough characters after i
                // to match the remaining word2
                if (j == m - 1 || right[j + 1] > i) {

                    ans[j] = i;
                    changed = true;
                    j++;
                }
            }

            i++;
        }

        // Couldn't form the complete sequence
        if (j != m) {
            return new int[0];
        }

        return ans;
    }
}