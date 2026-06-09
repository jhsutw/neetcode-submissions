// stack（跟前兩個方法不同，他不是一格一格看水量，而是看兩個高處中間低處的總水量）
class Solution {
    public int trap(int[] height) {
        if (height.length == 0){
            return 0;
        }
        // 建立一個stack存放（左邊高點、低點、右邊高點）
        Stack<Integer> stack = new Stack<>();
        int res = 0;

        for (int i = 0; i < height.length; i++){
            // 確保stack中有值&&有找到右邊高點，才proceed
            while (!stack.isEmpty() && height[i] >= height[stack.peek()]){
                // 把中間低點pop掉（後面要算的是左右高點的差值），並記錄其高度
                int mid = height[stack.pop()];
                if (!stack.isEmpty()){
                    int right = height[i];
                    int left = height[stack.peek()];
                    // 高度：左右高點高度的min值 - 中間低點的高度
                    int h = Math.min(right, left) - mid;
                    // 寬度：左右高點的index差 - 1
                    int w = i - stack.peek() - 1;
                    res += h * w;
                }
            }
            stack.push(i);
        }
        return res;
    }
}
