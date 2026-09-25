import java.util.*;

class Solution {

    int i = 0;

    Set<String> parse(String s) {

        Set<String> result = new HashSet<>();

        while (i < s.length() && s.charAt(i) != '}' && s.charAt(i) != ',') {

            Set<String> cur;

            if (s.charAt(i) == '{') {

                i++;
                cur = new HashSet<>();

                while (s.charAt(i) != '}') {

                    Set<String> temp = parse(s);

                    cur.addAll(temp);

                    if (s.charAt(i) == ',') {
                        i++;
                    }
                }

                i++;

            } else {

                cur = new HashSet<>();
                cur.add(String.valueOf(s.charAt(i)));
                i++;
            }

            if (result.isEmpty()) {
                result.addAll(cur);
            } else {

                Set<String> temp = new HashSet<>();

                for (String a : result) {
                    for (String b : cur) {
                        temp.add(a + b);
                    }
                }

                result = temp;
            }
        }

        return result;
    }

    public List<String> braceExpansionII(String expression) {

        i = 0;

        Set<String> set = parse(expression);

        List<String> ans = new ArrayList<>(set);

        Collections.sort(ans);

        return ans;
    }
}