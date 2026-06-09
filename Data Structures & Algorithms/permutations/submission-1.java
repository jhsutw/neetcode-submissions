public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        // 初始化 permutations 的 list，先放入一個空 list 作為起點
        List<List<Integer>> perms = new ArrayList<>();
        // perms：放重新排列的結果
        perms.add(new ArrayList<>());

        // 對每一個數字進行處理
        for (int num : nums) {
            // new_perms：放新的一輪重新排列的結果（因為正在遞迴perms，用一個new_perms裝結果才不會亂掉）
            List<List<Integer>> new_perms = new ArrayList<>();

            // 遍歷目前所有已有的排列
            for (List<Integer> p : perms) {

                // 將當前數字插入 p 的每一個可能位置
                for (int i = 0; i <= p.size(); i++) {
                    // 用p_copy才不會影響下輪數字插入位置（因為是要插p）
                    List<Integer> p_copy = new ArrayList<>(p);  // 複製原排列
                    p_copy.add(i, num);                         // 在第 i 個位置插入新數字
                    new_perms.add(p_copy);                      // 加入新的排列組合
                }
            }

            // 更新 perms，進入下一輪數字處理
            perms = new_perms;
        }

        return perms;
    }
}

/*
// 初始 perms: [[]]

// 處理 num = 1
//   插入到 [] → [1]
//   perms: [[1]]

// 處理 num = 2
//   插入到 [1] 的每個位置 → [2,1], [1,2]
//   perms: [[2,1], [1,2]]

// 處理 num = 3
//   插入到 [2,1] → [3,2,1], [2,3,1], [2,1,3]
//   插入到 [1,2] → [3,1,2], [1,3,2], [1,2,3]
//   perms: [[3,2,1], [2,3,1], [2,1,3], [3,1,2], [1,3,2], [1,2,3]]
*/