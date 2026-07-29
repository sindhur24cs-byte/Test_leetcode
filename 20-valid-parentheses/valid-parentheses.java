import java.util.ArrayDeque;
import java.util.Deque;

class Solution {
    public boolean isValid(String s) {
        // Quick check: odd length strings can never be fully balanced
        if (s.length() % 2 != 0) {
            return false;
        }

        Deque<Character> stack = new ArrayDeque<>();

        for (char c : s.toCharArray()) {
            if (c == '(') {
                stack.push(')');
            } else if (c == '{') {
                stack.push('}');
            } else if (c == '[') {
                stack.push(']');
            } else {
                // If stack is empty or top doesn't match current character
                if (stack.isEmpty() || stack.pop() != c) {
                    return false;
                }
            }
        }

        // Valid if no leftover unclosed brackets
        return stack.isEmpty();
    }
}