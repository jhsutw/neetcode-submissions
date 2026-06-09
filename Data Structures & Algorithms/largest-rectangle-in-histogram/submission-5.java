// 5. Stack (Optimal) 跟上一個做法類似只是把兩個case合併
public class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;
        Stack<Integer> stack = new Stack<>(); // 用來儲存「柱子的 index」

        // i <= n：讓我們在最後補上一個 0 當作「地板」，逼所有柱子出 stack 結算面積
        for (int i = 0; i <= n; i++) {
            // 如果 stack 不是空的，而且：
            // 1. i == n：代表我們已經走到陣列尾端 → 強制清算
            // 2. heights[i] 比目前 stack 頂端的還矮 → 要 pop 並結算面積
            while (!stack.isEmpty() && 
                   (i == n || heights[stack.peek()] >= heights[i])) {

                // pop 出來的柱子高度
                int height = heights[stack.pop()];

                // 如果 stack 清空了，代表這根柱子可以延伸到 index 0
                // 否則只能延伸到 stack.peek() 右邊一格
                int width = stack.isEmpty() ? i : i - stack.peek() - 1;

                // 更新最大面積
                maxArea = Math.max(maxArea, height * width);
            }

            // 無論如何都把當前 index 推入 stack（stack 維持遞增）
            stack.push(i);
        }

        return maxArea;
    }
}
