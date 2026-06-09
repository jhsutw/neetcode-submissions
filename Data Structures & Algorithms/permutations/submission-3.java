// 3. Backtracking
public class Solution {
    // 儲存所有可能的排列結果
    List<List<Integer>> res;

    public List<List<Integer>> permute(int[] nums) {
        res = new ArrayList<>();  // 初始化結果集合
        // 開始回溯演算法，初始情況是空排列和全部未被選取的元素
        backtrack(new ArrayList<>(), nums, new boolean[nums.length]);
        return res;
    }

    public void backtrack(List<Integer> perm, int[] nums, boolean[] pick) {
        // 當目前排列長度等於輸入陣列長度時，表示形成了一個合法排列
        if (perm.size() == nums.length) {
            res.add(new ArrayList<>(perm));  // 加入結果集中（注意要複製一份）
            return;
        }

        // 遍歷所有數字，嘗試將未被選取的數放入當前排列中
        for (int i = 0; i < nums.length; i++) {
            if (!pick[i]) {  // 若第 i 個數還沒被使用
                perm.add(nums[i]);     // 選擇該數字加入排列
                pick[i] = true;        // 標記該數字已被選用
                backtrack(perm, nums, pick);  // 遞迴繼續嘗試填下一個位置
                perm.remove(perm.size() - 1); // 回溯：移除最後一個元素（恢復現場）
                pick[i] = false;       // 回溯：把該數字的使用狀態設為未選取
            }
        }
    }
}

/*
backtrack([], [1,2,3], [F,F,F])
  └─ i=0 ➜ [1], pick[0]=T
    └─ i=1 ➜ [1,2], pick[1]=T
      └─ i=2 ➜ [1,2,3], pick[2]=T
        ➜ perm.size==3 → 加入結果集 [[1,2,3]]
      └─ 回溯 ➜ 移除 3 → [1,2], pick[2]=F
    └─ i=2 ➜ [1,3], pick[2]=T
      └─ i=1 ➜ [1,3,2], pick[1]=T
        ➜ 加入結果集 [[1,2,3], [1,3,2]]
      └─ 回溯 ➜ 移除 2 → [1,3], pick[1]=F
    └─ 回溯 ➜ 移除 3 → [1], pick[2]=F
  └─ 回溯 ➜ 移除 1 → [], pick[0]=F

  └─ i=1 ➜ [2], pick[1]=T
    └─ i=0 ➜ [2,1], pick[0]=T
      └─ i=2 ➜ [2,1,3] → 加入結果集
    └─ i=2 ➜ [2,3,1] → 加入結果集
  └─ i=2 ➜ [3], pick[2]=T
    └─ i=0 ➜ [3,1], pick[0]=T
      └─ i=1 ➜ [3,1,2] → 加入結果集
    └─ i=1 ➜ [3,2,1] → 加入結果集
*/