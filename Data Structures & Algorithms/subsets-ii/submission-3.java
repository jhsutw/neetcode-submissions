// 4. Iteration
public class Solution {
    public List<List<Integer>> subsetsWithDup(int[] nums) {
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());
        int prevIdx = 0;
        int idx = 0;

        for (int i = 0; i < nums.length; i++) {
            // 如果當前元素和前一個重複，則只從上一次新增子集的起始位置開始擴展
            idx = (i >= 1 && nums[i] == nums[i - 1]) ? prevIdx : 0;
            // 記錄本輪擴展前的結果集大小
            prevIdx = res.size();
            // 從 idx 到 prevIdx－1 這些子集，分別添加 nums[i]
            for (int j = idx; j < prevIdx; j++) {
                List<Integer> tmp = new ArrayList<>(res.get(j));
                tmp.add(nums[i]);
                res.add(tmp);
            }
        }

        return res;
    }
}

// --------- 運行舉例 (nums = [1,2,2]) ---------
// 初始狀態：
// res = [[]], prevIdx = 0
//
// i = 0 (nums[0] = 1)：
//   idx = 0 (不重複)； prevIdx = res.size() = 1
//   j = 0 → tmp = [] + 1 = [1] → res = [ [], [1] ]
//
// i = 1 (nums[1] = 2)：
//   idx = 0 (2 ≠ 1)； prevIdx = res.size() = 2
//   j = 0 → tmp = [] + 2 = [2]
//   j = 1 → tmp = [1] + 2 = [1,2]
//   res = [ [], [1], [2], [1,2] ]
//
// i = 2 (nums[2] = 2)：
//   idx = prevIdx = 2 (因為 2 == 前一個 2)
//   prevIdx = res.size() = 4
//   j = 2 → tmp = [2] + 2 = [2,2]
//   j = 3 → tmp = [1,2] + 2 = [1,2,2]
//   res = [ [], [1], [2], [1,2], [2,2], [1,2,2] ]
//
// 最終結果： [ [], [1], [2], [1,2], [2,2], [1,2,2] ]

// ---------------- 各種解法比較（中文註解表格） ----------------
/*
| 方法         | 重複處理方式                               | 枚舉機制               | 時間複雜度   | 空間複雜度   | 優點                                 | 缺點                             |
|--------------|---------------------------------------------|------------------------|-------------|-------------|--------------------------------------|----------------------------------|
| 方法1 Set    | sort + 全列舉所有子集，最後用 Set 去重       | 二分支「選／不選」      | O(2^n)+去重 | O(2^n)+Set  | 實作最直觀，容易理解                   | 需額外 Set 開銷、結果順序不可控     |
| 方法2 while  | sort，遞迴「不選」分支用 while 跳過相同值    | 二分支「選／不選」      | O(2^n)      | O(2^n)      | 無需額外資料結構，保留二分支邏輯       | 跳過重複邏輯於「不選」路徑，較不直觀   |
| 方法3 for+continue | sort，在 for 迴圈中用 if(j>i && nums[j]==nums[j-1]) continue 跳過 | for 迴圈 + 遞迴        | O(2^n)      | O(2^n)      | 程式簡潔，單一遞迴結構立即收集結果       | 必須正確判斷 j>i，否則易漏子集         |
| 方法4 索引迭代 | sort，記錄上一輪新增起點 prevIdx 僅擴展新子集 | 索引 for 迴圈疊代擴展    | O(2^n)      | O(2^n)      | 無遞迴、程式量少，可直接擴展前一次結果集 | 理解 prevIdx 機制稍有門檻             |
*/
// -----------------------------------------------------------------