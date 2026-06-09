public class Solution {
    // 儲存所有符合條件的組合結果
    private static List<List<Integer>> res = new ArrayList<>();

    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        res.clear();  // 清空靜態變數，避免跨測資時污染結果
        Arrays.sort(candidates);  // 對 candidates 排序，方便處理重複元素
        dfs(0, new ArrayList<>(), 0, candidates, target);
        return res;
    }

    /**
     * @param idx   當前處理到的 index
     * @param path  暫存目前組合的數字
     * @param cur   path 中數字的總和
     * @param candidates 可選的數字
     * @param target 目標和
     */
    private static void dfs(int idx, List<Integer> path, int cur, int[] candidates, int target) {
        // 若總和等於 target，加入結果中
        if (cur == target) {
            res.add(new ArrayList<>(path));
            return;
        }

        // 從 idx 開始往後探索
        for (int i = idx; i < candidates.length; i++) {
            // 跳過重複的數字，避免產生重複組合
            if (i > idx && candidates[i] == candidates[i - 1]) {
                continue;
            }

            // 若當前數字加上 cur 已經超過 target，就可以直接 break（因為後面數字更大）
            if (cur + candidates[i] > target) {
                break;
            }

            // 選擇 candidates[i]
            path.add(candidates[i]);

            // 遞迴下一層，注意：這裡傳入 i+1，代表每個元素只能用一次
            dfs(i + 1, path, cur + candidates[i], candidates, target);

            // 回溯，移除剛剛選的數字
            path.remove(path.size() - 1);
        }
    }
}