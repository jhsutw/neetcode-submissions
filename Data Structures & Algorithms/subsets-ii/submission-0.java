// 1. brute force
/* 跟上題差異：
                   Permute（全排列）	                             SubsetsWithDup（含重複子集）
問題目標	        將長度為 n 的陣列所有元素 全部 排列出來（n ! 種排列）	從陣列中選出 任意長度（0…n）的子集合，輸出所有可能子集
輸出結果	        每個結果都是「長度 = n」的排列	                     每個結果長度可變（0…n），最後去重
*/
public class Solution {
    // 用 Set 去重，最後再轉回 List
    Set<List<Integer>> res = new HashSet<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);                     // 先排序，讓重複元素相鄰
        backtrack(nums, 0, new ArrayList<>()); // 從索引 0 開始回溯
        return new ArrayList<>(res);           // 將 Set 轉為 List 回傳
    }

    /**
     * @param nums   排序後的原陣列
     * @param i      當前決定是否加入 nums[i]
     * @param subset 當前已選元素組成的子集
     */
    private void backtrack(int[] nums, int i, List<Integer> subset) {
        // 走到陣列末端：把目前子集加入結果（透過 Set 自動去重）
        if (i == nums.length) { // 上題是長度固定（每次都要確認哪些值還沒使用過，用他），這題是長度可變（只要都看過一次就好）
            res.add(new ArrayList<>(subset));
            return;
        }

        // 1) 選擇 nums[i]
        subset.add(nums[i]);
        backtrack(nums, i + 1, subset);

        // 撤銷選擇
        subset.remove(subset.size() - 1);

        // 2) 不選 nums[i]
        backtrack(nums, i + 1, subset);
    }
}

/*
🧪 範例流程 (nums = [1, 2, 2])：

初始: subset = [], i = 0
└─ 選 nums[0]=1 → subset=[1], i=1
   └─ 選 nums[1]=2 → subset=[1,2], i=2
      └─ 選 nums[2]=2 → subset=[1,2,2], i=3 → add [1,2,2]
      └─ 不選 nums[2] → subset=[1,2], i=3       → add [1,2]
   └─ 撤銷 → subset=[1], i=1
   └─ 不選 nums[1]  → subset=[1], i=2
      └─ 選 nums[2]=2 → subset=[1,2], i=3       → add [1,2]
      └─ 不選 nums[2] → subset=[1], i=3         → add [1]

└─ 撤銷 → subset=[]
└─ 不選 nums[0]    → subset=[], i=1
   └─ 選 nums[1]=2 → subset=[2], i=2
      └─ 選 nums[2]=2 → subset=[2,2], i=3      → add [2,2]
      └─ 不選 nums[2] → subset=[2], i=3         → add [2]
   └─ 撤銷 → subset=[]
   └─ 不選 nums[1] → subset=[], i=2
      └─ 選 nums[2]=2 → subset=[2], i=3         → add [2]
      └─ 不選 nums[2] → subset=[], i=3           → add []

最終 res（Set 去重後）含：
[]
[1]
[2]
[1,2]
[2,2]
[1,2,2]
*/