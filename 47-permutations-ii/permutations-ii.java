
class Solution {
    List<List<Integer>> result = new ArrayList<>();
    List<Integer> temp = new ArrayList<>();

    public List<List<Integer>> permuteUnique(int[] nums) {
        Arrays.sort(nums);
        boolean[] used = new boolean[nums.length];

        backtrack(nums, used);

        return result;
    }

    public void backtrack(int[] nums, boolean[] used) {

        if (temp.size() == nums.length) {
            result.add(new ArrayList<>(temp));
            return;
        }

        for (int i = 0; i < nums.length; i++) {

            if (used[i]) {
                continue;
            }

            if (i > 0 && nums[i] == nums[i - 1] && !used[i - 1]) {
                continue;
            }

            temp.add(nums[i]);
            used[i] = true;

            backtrack(nums, used);

            temp.remove(temp.size() - 1);
            used[i] = false;
        }
    }
}
