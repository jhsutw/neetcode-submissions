public class Solution {
    
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();   // 最終結果：存放所有子集合
        List<Integer> subset = new ArrayList<>();      // 當前正在組成的子集合
        dfs(nums, 0, subset, res);                     // 從索引 0 開始進行 DFS 探索
        return res;
    }

    // 遞迴函數：試著在第 i 個位置決定是否選 nums[i]
    private void dfs(int[] nums, int i, List<Integer> subset, List<List<Integer>> res) {
        // 🔚 終止條件：當 i 超過陣列長度，表示已經處理完所有元素
        if (i >= nums.length) {
            res.add(new ArrayList<>(subset));  // 將目前的 subset 複製一份加入結果
            return;
        }

        // ✅ 選擇 nums[i]
        subset.add(nums[i]);
        dfs(nums, i + 1, subset, res);   // 探索包含 nums[i] 的子集合

        // ❌ 不選 nums[i]（回溯：撤銷前面的選擇）
        subset.remove(subset.size() - 1);
        dfs(nums, i + 1, subset, res);   // 探索不包含 nums[i] 的子集合
    }
}
/*
nums = [1, 2, 3] 為例

dfs(0, subset=[])                ← 開始遞迴
├── 選 1 → subset=[1]
│   ├── 選 2 → subset=[1, 2]
│   │   ├── 選 3 → subset=[1, 2, 3] → 加入結果 ✅
│   │   └── 不選 3 → subset=[1, 2] → 加入結果 ✅
│   └── 不選 2 → subset=[1]
│       ├── 選 3 → subset=[1, 3] → 加入結果 ✅
│       └── 不選 3 → subset=[1] → 加入結果 ✅
└── 不選 1 → subset=[]
    ├── 選 2 → subset=[2]
    │   ├── 選 3 → subset=[2, 3] → 加入結果 ✅
    │   └── 不選 3 → subset=[2] → 加入結果 ✅
    └── 不選 2 → subset=[]
        ├── 選 3 → subset=[3] → 加入結果 ✅
        └── 不選 3 → subset=[] → 加入結果 ✅
*/
