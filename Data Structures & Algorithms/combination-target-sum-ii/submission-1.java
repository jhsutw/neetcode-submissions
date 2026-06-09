// 2. Backtracking
public class Solution {
    private List<List<Integer>> res; // 用來存放所有符合條件的組合

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res = new ArrayList<>();
        Arrays.sort(candidates); // 先排序，便於去除重複組合
        dfs(candidates, target, 0, new ArrayList<>(), 0);
        return res;
    }

    /**
     * @param candidates 原始候選數字陣列
     * @param target 目標和
     * @param i 當前遞迴處理到的 index
     * @param cur 當前正在建立的組合
     * @param total 當前組合的總和
     */
    private void dfs(int[] candidates, int target, int i, List<Integer> cur, int total) {
        // 若總和剛好等於目標，加入結果集
        if (total == target) {
            res.add(new ArrayList<>(cur)); // 要 new 一份避免後續修改影響結果
            return;
        }

        // 若超過目標或已經處理完所有數字，直接返回
        if (total > target || i == candidates.length) {
            return;
        }

        // --- 選擇第 i 個數字 ---
        cur.add(candidates[i]);
        dfs(candidates, target, i + 1, cur, total + candidates[i]);
        cur.remove(cur.size() - 1); // 回溯：移除最後一個加入的元素

        // --- 不選擇第 i 個數字，並跳過後續所有重複的數字 ---
        // 為了避免產生重複的組合，例如多次選擇相同的 1
        while (i + 1 < candidates.length && candidates[i] == candidates[i + 1]) {
            i++; // 跳過所有相同的元素
        }
        dfs(candidates, target, i + 1, cur, total);
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