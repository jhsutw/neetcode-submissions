public class Solution {
    List<List<Integer>> res = new ArrayList<>(); // 儲存所有符合條件的結果
    Map<Integer, Integer> count = new HashMap<>(); // 記錄每個數字出現的次數

    public List<List<Integer>> combinationSum2(int[] nums, int target) {
        List<Integer> cur = new ArrayList<>(); // 當前遞迴路徑
        List<Integer> A = new ArrayList<>();   // 去重後的候選數字列表

        // 統計每個數字的出現次數，並保留唯一值於 A 中
        for (int num : nums) {
            if (!count.containsKey(num)) {
                A.add(num); // 新數字才加入 A
            }
            count.put(num, count.getOrDefault(num, 0) + 1); // 更新計數
        }

        // 執行回溯
        backtrack(A, target, cur, 0);
        return res;
    }

    /**
     * 回溯函數
     * @param nums 已去重排序後的數字列表
     * @param target 剩餘目標值
     * @param cur 當前正在組合的數字列表
     * @param i 當前遞迴走到的 index
     */
    private void backtrack(List<Integer> nums, int target, List<Integer> cur, int i) {
        // 找到一組解，加入結果
        if (target == 0) {
            res.add(new ArrayList<>(cur));
            return;
        }

        // 超出邊界或總和過大，返回
        if (target < 0 || i >= nums.size()) {
            return;
        }

        int num = nums.get(i); // 目前考慮的數字

        // 嘗試選擇 nums[i]，前提是這個數字還有剩餘可用
        if (count.get(num) > 0) {
            cur.add(num); // 加入當前路徑
            count.put(num, count.get(num) - 1); // 將該數字使用次數 -1

            backtrack(nums, target - num, cur, i); // 可重複選，但限制次數

            count.put(num, count.get(num) + 1); // 回溯時恢復次數
            cur.remove(cur.size() - 1); // 移除剛剛加的數字
        }

        // 嘗試不選 nums[i]，直接跳到下一個數字
        backtrack(nums, target, cur, i + 1);
    }
}

/*
🧪 測資：
int[] nums = {1, 1, 2};
int target = 3;

✅ 預處理後：
count = {1: 2, 2: 1}
A = [1, 2] → 已去重排序

回溯處理：
// 初始：cur = [], target = 3, i = 0
1. cur = [1], count[1] = 1, target = 2, i = 0
   - 再選 1：cur = [1, 1], count[1] = 0, target = 1, i = 0
     - 無法再選 1（count[1] = 0）
     - 嘗試跳過 1：cur = [1, 1], target = 1, i = 1
       - 選 2：cur = [1, 1, 2], total = 3 > target → 結束
     - 回溯：移除一個 1 → cur = [1]
   - 跳過 1 → i = 1：cur = [1], target = 2
     - 選 2：cur = [1, 2], total = 3 ✅ 加入結果

2. 回到最初：cur = [], i = 1
   - 選 2：cur = [2], total = 2 → i = 2，結束

🔚 最終結果：[[1,1,1] ❌（超過總和）], [1,2]
*/