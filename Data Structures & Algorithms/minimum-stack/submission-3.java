class MinStack {
    private Stack<Integer> stack;
    private Stack<Integer> minStack;

    public MinStack() {
        stack = new Stack<>();
        minStack = new Stack<>();
    }
    
    // 在push的時後就同時判斷val是否為stack的最小值（該值會在minStack的top值）
    public void push(int val) {
        stack.push(val);
        if (minStack.isEmpty() || val <= minStack.peek()){
            minStack.push(val);
        }
    }
    
    // 若pop掉的值剛好為stack的最小值（miStack的top值），也要同時把miStack的top值pop掉
    public void pop() {
        if (stack.isEmpty()) return;
        int top = stack.pop();
        if (top == minStack.peek()){
            minStack.pop();
        }
    }
    
    public int top() {
        return stack.peek();
    }
    
    // minStack的top值即為最小值
    public int getMin() {
        return minStack.peek();
    }
}
