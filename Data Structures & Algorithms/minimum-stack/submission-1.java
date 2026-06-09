class MinStack {

    private Stack<Integer> stack;

    public MinStack() {
        // 左邊的型別變數已經在private標住了
        stack = new Stack<>();
    }
    // void代表不會回傳值
    public void push(int val) {
        stack.push(val);
    }
    
    public void pop() {
        stack.pop();
    }
    
    public int top() {
        return stack.peek();
    }
    
    public int getMin() {
        Stack<Integer> tmp = new Stack<>(); 
        int mini = stack.peek();

        // 把stack每個值都拿出來比較大小，找到最小值
        // 比較完以後把該值丟到tmp中
        while (!stack.isEmpty()){
            mini = Math.min(mini, stack.peek());
            tmp.push(stack.pop());
        }
        // 把tmp中的值再丟回stack中（維持stack原本結構，程式碼易維護）
        while (!tmp.isEmpty()){
            stack.push(tmp.pop());
        }
        return mini;
    }
}
