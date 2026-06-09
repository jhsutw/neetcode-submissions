class Solution {
    public int maxSubArray(int[] nums) {
        
        // 初始化 maxSub（最大的總和）跟currSub（當下的總和）
        int maxSub = nums[0];
        int currSub = 0;

        for (int num : nums) {
            if (currSub < 0) { // 只要發現上一輪的currSub小於0，就重新劃相加的區間
                currSub = 0;
            }
            currSub += num; // currSub加上當前值（這句放上一句後面，是擔心第一個元素就是負數或maxSub真的就是負數）

            maxSub = Math.max(currSub, maxSub); // 若currSub大於maxSub就替換它
        }
        return maxSub;
    }
}
