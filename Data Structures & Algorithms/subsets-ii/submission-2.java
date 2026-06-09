// 3. Backtracking - II
// ----------------- 三種方法比較 -----------------
/*
| 方法       | 去重方式                             | 遞迴/迴圈類型        | 時間複雜度    | 空間複雜度  | 優點                           | 缺點                         |
|------------|--------------------------------------|--------------------|-------------|------------|--------------------------------|------------------------------|
| 方法1 Set  | sort(nums) 後全列舉，結果用 Set 過濾  | 二分支（選 / 不選）   | O(2^n)+去重  | O(2^n)+Set  | 實作最直觀，程式碼簡單           | 額外記憶體開銷，結果順序不可控  |
| 方法2 while| sort(nums)，「不選」分支用 while 跳過 | 二分支（選 / 不選）   | O(2^n)       | O(2^n)     | 無需額外資料結構，保留二分支邏輯 | 跳過重複邏輯較不直觀            |
| 方法3 for  | sort(nums)，for 迴圈中 continue 跳過  | for‐迴圈 + 遞迴     | O(2^n)       | O(2^n)     | 程式最簡潔，立即加入結果         | 必須正確使用 j>i 條件避免漏選  |
*/
// --------------------------------------------------
public class Solution {
    // 結果集：儲存所有不重複的子集
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);                      // 先排序，使重複元素相鄰
        backtrack(0, new ArrayList<>(), nums);  // 從索引 0 開始回溯
        return res;
    }

    /**
     * @param i      下一次可選的起始索引
     * @param subset 當前已選元素構成的子集
     * @param nums   已排序的原始陣列
     */
    private void backtrack(int i, List<Integer> subset, int[] nums) {
        // 每到一層就把當前 subset（可能長度 0…n）加入結果
        res.add(new ArrayList<>(subset));

        // 從 i 開始遍歷可選元素
        for (int j = i; j < nums.length; j++) {
            // 跳過同一層中重複的值，避免相同子集重複加入
            if (j > i && nums[j] == nums[j - 1]) {
                continue;
            }
            // 選擇 nums[j]
            subset.add(nums[j]);
            // 下一輪只能從 j+1 之後選，保證子集元素遞增索引、不重複
            backtrack(j + 1, subset, nums);
            // 回溯：撤銷選擇
            subset.remove(subset.size() - 1);
        }
    }
}

/*
🧪 範例流程 (nums = [1, 2, 2])：

呼叫 backtrack(0, [], [1,2,2]):
  → 加入 []  
  j=0: 選 1 → subset=[1]
      backtrack(1, [1], ...)
        → 加入 [1]
        j=1: 選 2 → subset=[1,2]
             backtrack(2, [1,2], ...)
               → 加入 [1,2]
               j=2: 選 2 → subset=[1,2,2]
                      backtrack(3, [1,2,2], ...) → 加入 [1,2,2]
                      回溯 → subset=[1,2]
               完成回溯 → subset=[1]
        j=2: nums[2]==nums[1] → continue（跳過重複）
        完成回溯 → subset=[]
  j=1: 選 nums[1]=2 → subset=[2]
       backtrack(2, [2], ...)
         → 加入 [2]
         j=2: 選 2 → subset=[2,2]
                backtrack(3, [2,2], ...) → 加入 [2,2]
                回溯 → subset=[2]
         完成回溯 → subset=[]
  j=2: nums[2]==nums[1] → continue（跳重複）
結束回溯

最終 res = [ 
    [], [1], [1,2], [1,2,2],
    [2], [2,2]
]
*/