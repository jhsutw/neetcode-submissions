class Solution {
    public List<List<Integer>> threeSum(int[] nums) {
        // 先排序nums，確保輸出的list中的值會升序排列
        Arrays.sort(nums);
        List<List<Integer>> res = new ArrayList<>();

        for (int i = 0; i < nums.length; i++){
            // 若最小值為正，三數值相加不可能為0
            if (nums[i] > 0) break;
            // 跳過重複值
            if (i > 0 && nums[i] == nums[i - 1]) continue;

            // 用i的下一個index為left、最後一個index為right，左右夾擊
            int l = i + 1, r = nums.length - 1;
            while (l < r){
                int sum = nums[i] + nums[l] + nums[r];
                if (sum > 0){
                    r--;
                } else if (sum < 0){
                    l++;
                } else {
                    res.add(Arrays.asList(nums[i], nums[l], nums[r]));
                    l++;
                    r--;
                    // 跳過重複值（因為只要l不重複，整個triple就不會重複，所以不用針對r再做一次）
                    while (l < r && nums[l] == nums[l - 1]){
                        l++;
                    }
                }
            }
        }
        return res;
    }
}
