import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        List<List<Integer>> result = new ArrayList<>();
        Arrays.sort(candidates);
        find(candidates, target, 0, new ArrayList<>(), result);
        return result;
    }

    void find(int[] arr, int target, int start, 
              List<Integer> current, List<List<Integer>> result) {

        if (target == 0) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int i = start; i < arr.length; i++) {
            if (i > start && arr[i] == arr[i - 1]) {
                continue;
            }

            if (arr[i] > target) {
                break;
            }

            current.add(arr[i]);

            find(arr, target - arr[i], i + 1, current, result);

            current.remove(current.size() - 1);
        }
    }
}