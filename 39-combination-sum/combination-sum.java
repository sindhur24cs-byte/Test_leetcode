
import java.util.*;

class Solution {
    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> ans = new ArrayList<>();
        find(candidates, target, 0, new ArrayList<>(), ans);
        return ans;
    }

    public void find(int[] candidates, int target, int index,
                     List<Integer> list, List<List<Integer>> ans) {

        if (target == 0) {
            ans.add(new ArrayList<>(list));
            return;
        }

        if (target < 0 || index == candidates.length) {
            return;
        }

        list.add(candidates[index]);
        find(candidates, target - candidates[index], index, list, ans);

        list.remove(list.size() - 1);
        find(candidates, target, index + 1, list, ans);
    }
}