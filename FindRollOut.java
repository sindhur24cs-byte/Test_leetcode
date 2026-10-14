class Solution {
    public String findRollOut(String s, int[] roll) {
        int n = s.length();
        int[] count = new int[n + 1];

        for (int i = 0; i < roll.length; i++) {
            count[roll[i]]++;
        }

        for (int i = n - 1; i >= 1; i--) {
            count[i] += count[i + 1];
        }

        char[] arr = s.toCharArray();

        for (int i = 0; i < n; i++) {
            int shift = count[i + 1] % 26;
            arr[i] = (char) ('a' + (arr[i] - 'a' + shift) % 26);
        }

        return new String(arr);
    }
}
