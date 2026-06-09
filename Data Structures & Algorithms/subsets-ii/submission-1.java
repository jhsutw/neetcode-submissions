public class Solution {
    // 結果集：存放所有不重複的子集
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);                     // 先排序，讓相同元素相鄰
        backtrack(0, new ArrayList<>(), nums); // 從索引 0 開始回溯
        return res;
    }

    /**
     * @param i      當前處理到的 index
     * @param subset 當前已選元素組成的子集
     * @param nums   原始（已排序）陣列
     */
    private void backtrack(int i, List<Integer> subset, int[] nums) {
        // 走到底：把目前子集加入結果（sublist會重複，就是因為input list中的元素有重複）
        if (i == nums.length) {
            res.add(new ArrayList<>(subset));
            return;
        }

        // --- 分支 1：選擇 nums[i] ---
        subset.add(nums[i]);
        backtrack(i + 1, subset, nums);
        subset.remove(subset.size() - 1);

        // --- 分支 2：不選 nums[i]，但要跳過所有與 nums[i] 相同的值 ---
        // 例如 nums = [1,2,2] 在 i=1 時，跳過第二個 2
        while (i + 1 < nums.length && nums[i] == nums[i + 1]) {
            i++;
        }
        backtrack(i + 1, subset, nums);
    }
}

/*
🧪 範例：nums = [1,2,2]

呼叫 backtrack(0, [], [1,2,2]):
  └─ 選 1 → subset=[1], i=1
     └─ backtrack(1,[1],...)
        ├─ 選 2 → subset=[1,2], i=2
        │   ├─ backtrack(2,[1,2],...)
        │   │   ├─ 選 2 → subset=[1,2,2], i=3 → add [1,2,2]
        │   │   └─ 不選 2 → subset=[1,2], i=3 → add [1,2]
        │   └─ 回溯 → subset=[1]
        └─ 不選 2（跳過重複）
            // 因為 nums[1]==nums[2], 所以 while 會把 i 從 1 跳到 2
            backtrack(3, [1], ...) → add [1]

  └─ 回溯 → subset=[]
  └─ 不選 1
     backtrack(1, [], ...)
     ├─ 選 2 → subset=[2], i=2
     │   ├─ 選 2 → subset=[2,2], i=3 → add [2,2]
     │   └─ 不選 2 → subset=[2], i=3 → add [2]
     └─ 不選 2（跳過重複）
         // 跳過第二個 2
         backtrack(3, [], ...) → add []

最終 res = [
  [], [1], [1,2], [1,2,2],
  [2], [2,2]
]
*/