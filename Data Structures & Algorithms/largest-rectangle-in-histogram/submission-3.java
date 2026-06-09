class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] leftMost = new int[n];
        int[] rightMost = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < n; i++){
            leftMost[i] = -1;
            // pop掉左側比自己高的柱子（只留下第一個比自己矮之前的）
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]){
                stack.pop();
            }
            // 把左側的第一個比自己矮的柱子設為leftMost
            if (!stack.isEmpty()){
                leftMost[i] = stack.peek();
            }
            stack.push(i);
        }

        stack.clear(); // 清空 stack 以用於右邊界的處理
        for (int i = n - 1; i >= 0; i--) {
            rightMost[i] = n;
            // pop掉右側比自己高的柱子（只留下第一個比自己矮之後的）
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                stack.pop();
            }
            // 把右側的第一個比自己矮的柱子設為rightMost
            if (!stack.isEmpty()) {
                rightMost[i] = stack.peek();
            }
            stack.push(i);
        }
        // 計算每個heights中每個元素的maxArea，然後再找出最大的maxArea
        int maxArea = 0;
        for (int i = 0; i < n; i++){
            leftMost[i] += 1;
            rightMost[i] -= 1;
            maxArea = Math.max(maxArea, (rightMost[i] - leftMost[i] + 1) * heights[i]);
        }
        return maxArea;
    }
}
