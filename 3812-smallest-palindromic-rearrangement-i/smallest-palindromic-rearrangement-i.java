class Solution {
    public String smallestPalindrome(String s) {
        int[] count = new int[26];
        
        // Step 1: Count frequency of each character
        for (int i = 0; i < s.length(); i++) {
            count[s.charAt(i) - 'a']++;
        }
        
        StringBuilder half = new StringBuilder();
        String middle = "";
        
        // Step 2: Build the first half lexicographically & identify middle char
        for (int i = 0; i < 26; i++) {
            if (count[i] > 0) {
                char ch = (char) ('a' + i);
                
                // If frequency is odd, save this character as the middle element
                if (count[i] % 2 != 0) {
                    middle = String.valueOf(ch);
                }
                
                // Append count / 2 characters to the first half
                for (int j = 0; j < count[i] / 2; j++) {
                    half.append(ch);
                }
            }
        }
        
        // Step 3: Combine first half + middle + reversed first half
        String firstHalf = half.toString();
        String secondHalf = half.reverse().toString();
        
        return firstHalf + middle + secondHalf;
    }
}