// Prefix & Suffix Arrays
class Solution {
    public int trap(int[] height) {
        int n = height.length;
        if (n == 0){
            return 0;
        }

        // 建立leftMax/rightMax兩個陣列來存每格左右兩邊的最大值，最後再一次算每隔上面的水量
        // 優點：可以把時間複雜度從O(N^2)降為O(N)
        int[] leftMax = new int[n];
        int[] rightMax = new int[n];

        // 第一格不用跑（因為左邊沒有值）
        leftMax[0] = height[0];
        for (int i = 1; i < n; i++){
            leftMax[i] = Math.max(leftMax[i - 1], height[i]);
        }

        // 最後一格不用跑（因為右邊沒有值）
        rightMax[n - 1] = height[n - 1];
        for (int i = n - 2; i >= 0; i--){
            rightMax[i] = Math.max(rightMax[i + 1], height[i]);
        }

        int res = 0;
        for (int i = 0; i < n; i++){
            res += Math.min(leftMax[i], rightMax[i]) - height[i];
        }
        return res;
    }
}
