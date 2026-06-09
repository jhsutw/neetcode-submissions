class Solution {
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;
        // 設定每一個元素對應到的block的最大值（由左到右、由右到左各一個）
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        leftMax[0] = nums[0];
        rightMax[n - 1] = nums[n - 1];
        
        // 若為block的第一個元素，該元素即預設為block的最大值
        // 若同一個block右邊元素大於左邊元素，右邊元素的最大值為自己；否則為左邊值
        for (int i = 1; i < n; i++){
            if (i % k == 0){
                leftMax[i] = nums[i];
            } else {
                leftMax[i] = Math.max(leftMax[i - 1], nums[i]);
            }

        // 若為block的倒數第一個元素，該元素即預設為block的最大值
        // 若同一個block左邊元素大於右邊元素，左邊元素的最大值為自己；否則為右邊值
            if ((n - 1 - i) % k == 0){
                rightMax[n - 1 - i] = nums[n - 1 - i];
            } else {
                rightMax[n - 1 - i] = Math.max(rightMax[n - i], nums[n - 1 - i]);
            }
        }
        
        // output長度為n - k + 1
        int[] output = new int[n - k + 1];

        // output[i]為該index的leftMax[i + k - 1]與rightMax[i]較大值
        for (int i = 0; i < n - k + 1; i++){
            output[i] = Math.max(leftMax[i + k - 1], rightMax[i]);
        }
        return output;
    }
}
