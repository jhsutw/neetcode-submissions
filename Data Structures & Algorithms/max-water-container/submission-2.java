class Solution {
    public int maxArea(int[] heights) {
        // 初始化left(l)、right(r)、result(res)
        int l = 0;
        int r = heights.length - 1;
        int res = 0;

        /*
        1. 從最寬的 (0, n-1) 開始
        2. 每次縮窄區間，只丟掉「比較矮」的邊（l++/r--）
        3. 代表「丟掉的那一邊」無法形成比當前更大的面積
        */
        while (l < r){
            int area = Math.min(heights[l], heights[r]) * (r - l);
            res = Math.max(res, area);
            // heights[l] <= heights[r] 相等時也要移動！！！
            if (heights[l] <= heights[r]){
                l++;
            } else {
                r--;
            }
        }
        return res;
    }
}
