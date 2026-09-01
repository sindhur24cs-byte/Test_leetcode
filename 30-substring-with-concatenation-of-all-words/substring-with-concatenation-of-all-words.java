import java.util.*;

class Solution {
    public List<Integer> findSubstring(String s, String[] words) {
        List<Integer> result = new ArrayList<>();

        int wordLen = words[0].length();
        int wordCount = words.length;
        int totalLen = wordLen * wordCount;

        if (totalLen > s.length()) {
            return result;
        }

        Map<String, Integer> required = new HashMap<>();

        for (String word : words) {
            required.put(word, required.getOrDefault(word, 0) + 1);
        }

        for (int offset = 0; offset < wordLen; offset++) {
            int left = offset;
            int count = 0;

            Map<String, Integer> current = new HashMap<>();

            for (int right = offset; right + wordLen <= s.length(); right += wordLen) {
                String word = s.substring(right, right + wordLen);

                if (!required.containsKey(word)) {
                    current.clear();
                    count = 0;
                    left = right + wordLen;
                    continue;
                }

                current.put(word, current.getOrDefault(word, 0) + 1);
                count++;

                while (current.get(word) > required.get(word)) {
                    String leftWord = s.substring(left, left + wordLen);

                    current.put(leftWord, current.get(leftWord) - 1);
                    left += wordLen;
                    count--;
                }

                if (count == wordCount) {
                    result.add(left);

                    String leftWord = s.substring(left, left + wordLen);
                    current.put(leftWord, current.get(leftWord) - 1);
                    left += wordLen;
                    count--;
                }
            }
        }

        return result;
    }
}