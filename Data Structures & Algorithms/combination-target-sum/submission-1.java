public class Solution {
    List<List<Integer>> res;

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        res = new ArrayList<>();
        Arrays.sort(nums); // 排序是為了讓剪枝生效（提早結束不可能的組合）
        dfs(0, new ArrayList<>(), 0, nums, target);
        return res;
    }

    // i：目前遍歷到 nums 的哪個 index
    // cur：當前正在組合的列表
    // total：當前 cur 的總和
    private void dfs(int i, List<Integer> cur, int total, int[] nums, int target) {
        // 如果總和剛好等於目標，加入結果
        if (total == target) {
            res.add(new ArrayList<>(cur));
            return;
        }

        for (int j = i; j < nums.length; j++) {
            // 若加上 nums[j] 超過目標值，提早結束這條路徑（剪枝）
            if (total + nums[j] > target) {
                return;
            }

            // 選擇 nums[j]，因為元素可以重複選擇，所以下一次還是從 j 開始
            cur.add(nums[j]);
            dfs(j, cur, total + nums[j], nums, target);
            cur.remove(cur.size() - 1); // 回溯，撤銷選擇
        }
    }
}

/*
int[] nums = {2, 3, 6, 7};
int target = 7;

dfs(0, [], 0)         // 開始從 0 開始嘗試

  → 選 2 → dfs(0, [2], 2)
    → 選 2 → dfs(0, [2,2], 4)
      → 選 2 → dfs(0, [2,2,2], 6)
        → 選 2 → dfs(0, [2,2,2,2], 8) ✘ 超過，剪枝
      → 選 3 → dfs(1, [2,2,3], 7) ✔ 加入結果

  → 選 3 → dfs(1, [3], 3)
    → 選 3 → dfs(1, [3,3], 6)
      → 選 3 → dfs(1, [3,3,3], 9) ✘ 超過，剪枝

  → 選 6 → dfs(2, [6], 6)
    → 選 6 → dfs(2, [6,6], 12) ✘ 超過，剪枝

  → 選 7 → dfs(3, [7], 7) ✔ 加入結果
*/