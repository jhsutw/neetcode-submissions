public class Solution {
    private Set<List<Integer>> res; // 用 Set 來去重（避免重複組合）

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new HashSet<>(); // 最終結果使用 HashSet 去重
        Arrays.sort(candidates); // 排序讓重複值排列在一起，方便剪枝
        generateSubsets(candidates, target, 0, new ArrayList<>(), 0);
        return new ArrayList<>(res); // 最後轉回 List
    }

    // i: 當前 index，cur: 當前組合，total: cur 總和
    private void generateSubsets(int[] candidates, int target, int i, List<Integer> cur, int total) {
        // 成功情況：總和剛好等於 target
        if (total == target) {
            res.add(new ArrayList<>(cur)); // 加入複本
            return;
        }

        // 剪枝：超出總和或索引越界
        if (total > target || i == candidates.length) {
            return;
        }

        // 選擇第 i 個元素（每個只能選一次，所以遞增 index）
        cur.add(candidates[i]);
        generateSubsets(candidates, target, i + 1, cur, total + candidates[i]);
        cur.remove(cur.size() - 1); // 回溯

        // 不選擇第 i 個元素，直接跳過 (不加candidates[i]到total)
        generateSubsets(candidates, target, i + 1, cur, total);
    }
}

/*
candidates = [2, 3, 6]
target = 5

// 初始呼叫
generateSubsets([2, 3, 6], target=5, i=0, cur=[], total=0)

// 選擇 index 0 的元素 2
cur = [2], total = 2
generateSubsets([2, 3, 6], target=5, i=1, cur=[2], total=2)

    // 選擇 index 1 的元素 3
    cur = [2, 3], total = 5 -> ✅ 加入結果
    generateSubsets([2, 3, 6], target=5, i=2, cur=[2, 3], total=5)

        // 選擇 index 2 的元素 6 → total 超過，剪枝
        cur = [2, 3, 6], total = 11 -> ❌ return

        // 不選 index 2 的元素 6
        cur = [2, 3], total = 5 -> ❌ i == 3，return

    // 不選擇 index 1 的元素 3（這就是那一行在做的）
    cur = [2], total = 2
    generateSubsets([2, 3, 6], target=5, i=2, cur=[2], total=2)

        // 選擇 index 2 的元素 6
        cur = [2, 6], total = 8 -> ❌ return

        // 不選 index 2 的元素 6
        cur = [2], total = 2 -> i == 3，return


// 不選擇 index 0 的元素 2（這也是那一行在做的）
cur = [], total = 0
generateSubsets([2, 3, 6], target=5, i=1, cur=[], total=0)

    // 選擇 index 1 的元素 3
    cur = [3], total = 3
    generateSubsets([2, 3, 6], target=5, i=2, cur=[3], total=3)

        // 選擇 index 2 的元素 6
        cur = [3, 6], total = 9 -> ❌ return

        // 不選 index 2 的元素 6
        cur = [3], total = 3 -> ❌ i == 3，return

    // 不選 index 1 的元素 3
    cur = [], total = 0
    generateSubsets([2, 3, 6], target=5, i=2, cur=[], total=0)

        // 選擇 index 2 的元素 6
        cur = [6], total = 6 -> ❌ return

        // 不選 index 2 的元素 6
        cur = [], total = 0 -> i == 3，return
*/