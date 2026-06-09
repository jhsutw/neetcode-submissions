public class Solution {
    // 儲存所有排列結果
    List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> permute(int[] nums) {
        // 從空排列、mask=0（尚未選任何元素）開始回溯
        backtrack(new ArrayList<>(), nums, 0);
        return res;
    }

    /**
     * @param perm 當前排列
     * @param nums 輸入陣列
     * @param mask 整數的第 i 位 (1<<i) 表示 nums[i] 是否已被選過 (1=已選, 0=未選)
     */
    private void backtrack(List<Integer> perm, int[] nums, int mask) {
        // 當 perm 長度到達 nums 長度，就得到一組完整排列
        if (perm.size() == nums.length) {
            res.add(new ArrayList<>(perm));  // 複製一份後加入結果
            return;
        }

        // 嘗試對每個位置 i 做選或不選
        for (int i = 0; i < nums.length; i++) {
            // 如果第 i 位還沒被選 (mask & (1<<i) == 0)，就可以選它
            if ((mask & (1 << i)) == 0) {
                perm.add(nums[i]);               // 選 nums[i]
                // 設定 mask 的第 i 位為 1，表示已選
                backtrack(perm, nums, mask | (1 << i));
                perm.remove(perm.size() - 1);    // 回溯，撤銷選擇
                // mask 回傳時自動還原，因為我們傳入的是新的整數 (mask | (1<<i))
            }
        }
    }
}