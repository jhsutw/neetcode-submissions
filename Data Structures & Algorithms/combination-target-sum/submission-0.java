public class Solution {
    List<List<Integer>> res;

    public List<List<Integer>> combinationSum(int[] nums, int target) {
        res = new ArrayList<>();
        List<Integer> cur = new ArrayList<>();
        backtrack(nums, target, cur, 0);  // 從 index 0 開始搜尋
        return res;
    }

    // 回溯函數：嘗試從 nums[i...] 開始組出總和為 target 的組合
    public void backtrack(int[] nums, int target, List<Integer> cur, int i) {
        // 若剛好湊出 target，將目前組合加入結果
        if (target == 0) { // target 每次都會減掉上一個加入組合的值，當target == 0時該組合的sum為target
            res.add(new ArrayList<>(cur));
            return;
        }

        // 若超出 target 或遍歷完所有數字，剪枝結束
        if (target < 0 || i >= nums.length) {
            return;
        }

        // 選擇 nums[i]（可重複選）
        cur.add(nums[i]);
        backtrack(nums, target - nums[i], cur, i);  // 不移動 index，因為數字可重複選
        cur.remove(cur.size() - 1);                 // 回溯，撤銷選擇

        // 不選 nums[i]，考慮下一個數字
        backtrack(nums, target, cur, i + 1);
    }
}

/*
nums = [2, 3, 6, 7];
target = 7;

start: target = 7, index = 0, current = []
│
├─ 選 2 -> target = 5, current = [2]
│   ├─ 選 2 -> target = 3, current = [2, 2]
│   │   ├─ 選 2 -> target = 1, current = [2, 2, 2]
│   │   │   ├─ 選 2 -> target = -1 ❌（剪枝）
│   │   │   └─ 不選 2，換成 3 -> target = -2 ❌（剪枝）
│   │   └─ 不選 2，換成 3 -> target = 0 ✅（找到解）→ [2, 2, 3]
│   └─ 不選 2，換成 3 -> target = 2
│       └─ 選 3 -> target = -1 ❌（剪枝）
│       └─ 不選 3，換成 6、7... ❌（都超過了）
│
├─ 不選 2，直接從 3 開始 -> target = 4, current = [3]
│   ├─ 選 3 -> target = 1
│   │   ├─ 選 3 -> target = -2 ❌（剪枝）
│   └─ 不選 3，換成 6、7 ❌
│
├─ 選 6 -> target = 1
│   └─ 選任意數都會超過 ❌
│
├─ 選 7 -> target = 0 ✅（找到解）→ [7]
*/