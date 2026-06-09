public class Solution {
    public List<List<Integer>> permute(int[] nums) {
        // Base case：如果 nums 為空，回傳一個包含空 list 的 list
        // 這是為了讓遞迴最後能回傳 [[]]，以便往上一層加值
        if (nums.length == 0) {
            return Arrays.asList(new ArrayList<>());
        }

        // 先取出 nums[1:] 的所有排列（使用遞迴）
        // Arrays.copyOfRange(nums, 1, nums.length) 取得從 index 1 到末尾的新陣列（每次把nums迭代更新成1:nums.length，所以nums[0]會一直往後推進）
        List<List<Integer>> perms = permute(Arrays.copyOfRange(nums, 1, nums.length));

        List<List<Integer>> res = new ArrayList<>();

        // 把 nums[0] 插入每一個子排列的每一個可能位置
        for (List<Integer> p : perms) {
            for (int i = 0; i <= p.size(); i++) {
                // 複製一份子排列
                List<Integer> p_copy = new ArrayList<>(p);
                // 在第 i 個位置插入 nums[0]
                p_copy.add(i, nums[0]);
                // 加入結果集
                res.add(p_copy);
            }
        }

        return res;
    }
}

/*
permute([1, 2, 3])
↓
permute([2, 3])
    ↓
    permute([3])
        ↓
        permute([]) → [[]]

        // 對 [[]] 插入 3 → [[3]]

    // 對 [[3]] 插入 2：
    // [2, 3], [3, 2]

↑ 回到 permute([1, 2, 3])
// 對 [[2, 3], [3, 2]] 插入 1：

// 插入到 [2, 3]：
[1, 2, 3]
[2, 1, 3]
[2, 3, 1]

// 插入到 [3, 2]：
[1, 3, 2]
[3, 1, 2]
[3, 2, 1]

最終結果：
[
 [1, 2, 3],
 [2, 1, 3],
 [2, 3, 1],
 [1, 3, 2],
 [3, 1, 2],
 [3, 2, 1]
]
*/