class Solution {
    public int largestRectangleArea(int[] heights) {
        int maxArea = 0;
        // 用stack放各元素以及當前最矮的bar
        Stack<int[]> stack = new Stack<>();


        // 遞迴heights中所有的元素（start：當前最矮的bar）
        // 若當前的heights[i]沒有比上一個矮，就會持續把start的index以及其高度放入stack中
        // 直到找到一個比上一個矮的bar（end），就往前找比他更矮的，找到以後計算(end - start)*height為maxArea，並不斷找新的max值
        for (int i = 0; i < heights.length; i++){
            int start = i;
            while (!stack.isEmpty() && stack.peek()[1] > heights[i]){
                int[] top = stack.pop();
                int index = top[0];
                int height = top[1];
                maxArea = Math.max(maxArea, height * (i - index));
                start = index; // index往左傳
            }
            stack.push(new int[]{start, heights[i]});// (柱子能往左延伸的起點index, 自己的高度)
        }
        
        // 第二輪處理 stack 中剩下的柱子（右邊界到陣列末尾）
        for (int[] pair : stack){
            int index = pair[0];
            int height = pair[1];
            maxArea = Math.max(maxArea, height * (heights.length - index));
        }
        return maxArea;
    }
}
