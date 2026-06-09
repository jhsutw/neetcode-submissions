// 5. Backtracking (Optimal)
public class Solution {
    // 結果集：儲存所有產生的排列
    List<List<Integer>> res;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();
        // 從索引 0 開始，對整個陣列進行 in-place 交換回溯
        backtrack(nums, 0);
        return res;
    }

    /**
     * @param nums 原地存放當前排列狀態的陣列
     * @param idx  下一個要固定的索引位置
     */
    public void backtrack(int[] nums, int idx) {
        // 1) Base case：如果 idx 已經到達陣列長度
        if (idx == nums.length) {
            // 將 nums[] 的當前排列複製成 List 加入結果
            List<Integer> perm = new ArrayList<>();
            for (int num : nums) {
                perm.add(num);
            }
            res.add(perm);
            return;
        }
        // 2) 對每一個可能的 i >= idx，交換到 idx 位置，再繼續固定下一個位置
        for (int i = idx; i < nums.length; i++) {
            swap(nums, idx, i);             // 把 nums[i] 交換到 idx 位置
            backtrack(nums, idx + 1);       // 遞迴：固定下一格
            swap(nums, idx, i);             // 回溯：還原交換前的狀態
        }
    }

    /** 交換陣列中 i 與 j 位置的元素 */
    private void swap(int[] nums, int i, int j) {
        int tmp = nums[i];
        nums[i] = nums[j];
        nums[j] = tmp;
    }
}

/*
-------------------------------
🧪 範例流程（nums = [1,2,3]）：

呼叫 backtrack([1,2,3], 0):
  idx=0, i=0 → swap(0,0): [1,2,3]
    backtrack([1,2,3], 1):
      idx=1, i=1 → swap(1,1): [1,2,3]
        backtrack([1,2,3], 2):
          idx=2, i=2 → swap(2,2): [1,2,3]
            backtrack([1,2,3], 3) → 加入 [1,2,3]
          swap(2,2) 回溯 → [1,2,3]
      i=2 → swap(1,2): [1,3,2]
        backtrack([1,3,2], 2):
          swap(2,2): [1,3,2] → backtrack → 加入 [1,3,2]
        swap(1,2) 回溯 → [1,2,3]
  swap(0,0) 回溯 → [1,2,3]

  i=1 → swap(0,1): [2,1,3]
    backtrack([2,1,3], 1):
      i=1 → [2,1,3] → 加入 [2,1,3]
      i=2 → swap(1,2): [2,3,1] → 加入 [2,3,1]
    swap(0,1) 回溯 → [1,2,3]

  i=2 → swap(0,2): [3,2,1]
    backtrack([3,2,1], 1):
      i=1 → [3,2,1] → 加入 [3,2,1]
      i=2 → swap(1,2): [3,1,2] → 加入 [3,1,2]
    swap(0,2) 回溯 → [1,2,3]

最終 res = [
  [1,2,3], [1,3,2],
  [2,1,3], [2,3,1],
  [3,2,1], [3,1,2]
]
*/