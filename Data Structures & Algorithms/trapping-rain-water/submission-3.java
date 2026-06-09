// two pointers（偏向前兩種方法一格一格算水量）
class Solution {
    public int trap(int[] height) {
        if (height == null || height.length == 0){
            return 0;
        }

        int l = 0, r = height.length - 1;
        int leftMax = height[l], rightMax = height[r];
        int res = 0;
        // 用左右夾擊的方式算水量
        while (l < r){
            // 因為水量高度由矮的那邊決定，所以高度小的一側先算下一格水量
            if (leftMax < rightMax){
                l++;
                // 水的高度為水窪兩側牆壁矮的那邊的高度決定
                // 若height[l]比此（leftMax）高度低則差值為該格水量
                leftMax = Math.max(leftMax, height[l]);
                res += leftMax - height[l];
            } else {
                r--;
                rightMax = Math.max(rightMax, height[r]);
                res += rightMax - height[r];
            }
        }
        return res;
    }
}
