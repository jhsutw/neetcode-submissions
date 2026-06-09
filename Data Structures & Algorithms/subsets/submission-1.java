// 2. Iteration
public class Solution {
    public List<List<Integer>> subsets(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(new ArrayList<>());  // 初始子集：空集合 []

        for (int num : nums) {
            int size = res.size();   // 當前 res 裡的子集個數
            for (int i = 0; i < size; i++) {
                // 複製 res 裡現有的每個子集，加上當前的 num
                List<Integer> subset = new ArrayList<>(res.get(i));
                subset.add(num);
                res.add(subset); // 將新子集加入結果中
            }
        }

        return res;
    }
}
/*
以 nums = [1, 2, 3] 為例：

初始：res = [[]]

處理 1：
res 中現有：[]
subset 加上 1：變成 [1]
subset 加入 res 變成：[[], [1]]

處理 2：
現有：[], [1]
subset 加上 2：[2], [1, 2]
subset 加入 res：[[], [1], [2], [1, 2]]

處理 3：
現有：[], [1], [2], [1,2]
subset 加上 3：[3], [1,3], [2,3], [1,2,3]
subset 加入 res：[[], [1], [2], [1, 2], [3], [1, 3], [2, 3], [1, 2, 3]]
*/