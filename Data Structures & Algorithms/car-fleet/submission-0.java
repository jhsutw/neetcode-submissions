class Solution {
    public int carFleet(int target, int[] position, int[] speed) {
        // 創造一個長度為position.length且長度為2的int array
        int[][] pair = new int[position.length][2];
        // 把pair的第一個元素指派為position；第二個元素指派為speed
        for (int i = 0; i < position.length; i++){
            pair[i][0] = position[i];
            pair[i][1] = speed[i];
        }

        // 把pair依照position降冪排序
        Arrays.sort(pair, (a, b) -> Integer.compare(b[0], a[0]));
        // 創建一個stack放各車抵達終點所需的時間
        Stack<Double> stack = new Stack<>();
        for (int[] p : pair) {
            // 計算各車抵達終點所需的時間
            stack.push((double) (target - p[0]) / p[1]);
            // 若當前的車（stack.size() - 1）抵達終點所需的時間小於前一輛（stack.size() - 2），就從stack pop掉當前的車抵達終點所需的時間
            if (stack.size() >= 2 &&
                stack.peek() <= stack.get(stack.size() - 2)){
                    stack.pop();
                }
        }
        // 回傳size為fleet num
        return stack.size();
    }
}
