public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        int n = nums.length;  // 原陣列的長度
        List<List<Integer>> res = new ArrayList<>();

        // 枚舉所有 2^n 種子集（每一種對應一個整數的 bit 組合）
        for (int i = 0; i < (1 << n); i++) {  // 從 0 到 2^n - 1 (若 n = 3，則遍歷 0~7)
            List<Integer> subset = new ArrayList<>();
            for (int j = 0; j < n; j++) { // (若 n = 3，則遍歷 0~2)
                // 檢查 i 的第 j 位是否為 1，若是就加上 nums[j]
                if ((i & (1 << j)) != 0) {
                    subset.add(nums[j]);
                }
            }
            res.add(subset);  // 將對應的子集加入結果
        }

        return res;
    }
}

/*
📌 舉例：nums = [1, 2, 3]
n = 3，所以總共會有 2^3 = 8 種組合：

i (十進位)	i (二進位)	subset
0	       000	      []
1	       001	      [1]
2	       010	      [2]
3	       011	      [1, 2]
4	       100	      [3]
5	       101	      [1, 3]
6	       110	      [2, 3]
7	       111	      [1, 2, 3]
*/
