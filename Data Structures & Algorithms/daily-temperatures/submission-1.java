class Solution {
    public int[] dailyTemperatures(int[] temperatures) {
        // 創建一個int array放res
        // 創建一個stack暫存（溫度, 天數index)
        int[] res = new int[temperatures.length];
        Stack<int[]> stack = new Stack<>();

        // 遞迴每一個元素
        // 若當前的溫度大於stack第一個溫度值，就把天數差異記錄到res中 (當前溫度會不斷跟stack第一個溫度值比較，直到比stack第一個溫度值為止) 
        // 把每一個元素push進stack中
        // res預設值為0，故不需針對往後沒有更高溫度的案例做處理
        for (int i = 0; i< temperatures.length; i++){
            int t = temperatures[i];
            while (!stack.isEmpty() && t > stack.peek()[0]){
                int[] pair = stack.pop();
                res[pair[1]] = i - pair[1];
            }
            stack.push(new int[]{t, i});
        }
        return res;
    } 
}
