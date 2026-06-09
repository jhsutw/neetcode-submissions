class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }

        int n = height.length;
        int res = 0;

        // 每隔上面的水量計算公式 Math.min(leftMax, rightMax) - height[i]
        // 一開始要把leftMax/rightMax設為height[i]是因為當height[i]是最高點，該格的水量會是height[i]-height[i]=0
        for (int i = 0; i < n; i++){
            int leftMax = height[i];
            int rightMax = height[i];

            // 找i格左邊的最高點
            for (int j = 0; j < i; j++){
                leftMax = Math.max(leftMax, height[j]);
            }

            // 找i格右邊的最高點
            for (int j = i + 1; j < n; j++){
                rightMax = Math.max(rightMax, height[j]);
            }

            res += Math.min(leftMax, rightMax) - height[i];
        }
        return res;
    }
}
