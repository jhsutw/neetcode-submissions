class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        int[] output = new int[n - k + 1]; // output list 長度：n - k + 1

        // 遍歷nums中每一種長度為k的字串組合（記得i <= n - k 是小於等於）
        for (int i = 0; i <= n - k; i++){
            int maxi = nums[i];
            // 從第i個元素看到i + k -1個
            for (int j = i + 1; j < i + k; j++){
                // 若有大於當前maxi的元素就取帶之
                maxi = Math.max(maxi, nums[j]);
            }
            output[i] = maxi;
        }

        return output;
    }
}
