class Solution {
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int maxArea = 0;

        // 遞迴每一個heights中的元素
        for (int i = 0; i < n; i++){
            int height = heights[i];

            // 往右只要找到比自己高的bar就把rightMost++
            int rightMost = i + 1;
            while (rightMost < n && heights[rightMost] >= height){
                rightMost++;
            }

            // 往左只要找到比自己矮的bar就把rightMost--
            // leftMost = i 因為要把自己算進去（width才不會少1）
            int leftMost = i;
            while (leftMost >= 0 && heights[leftMost] >= height){
                leftMost--;
            }

            // 修正邊界（因為 while 會多走一步）
            rightMost--;
            leftMost++;

            // 計算以第 i 根柱子為高度時，所能形成的最大長方形面積
            int width = rightMost - leftMost + 1;
            int area = height * width;

            // 若當前的面積大於當前最大值，則取代之
            maxArea = Math.max(maxArea, height * (rightMost - leftMost + 1));
        }
        return maxArea;
    }
}
