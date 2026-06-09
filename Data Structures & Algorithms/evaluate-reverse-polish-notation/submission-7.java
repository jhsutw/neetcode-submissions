class Solution {
    public int evalRPN(String[] tokens) {
        // 建立一個stack放待運算及運算好的值
        Stack<Integer> stack = new Stack<>();

        // 遍歷tokens裡每個元素
        for (String c : tokens){
            // 運算完把值丟入stack成為下一輪待運算的元素 or 最終結果
            if (c.equals("+")){
                stack.push(stack.pop() + stack.pop());
            } else if (c.equals("-")){
                int a = stack.pop();
                int b = stack.pop();
                // stack為後進先出（順序與原本相反），所以要用b-a
                stack.push(b - a);
            } else if (c.equals("*")){
                stack.push(stack.pop() * stack.pop());
            } else if (c.equals("/")){
                int a = stack.pop();
                int b = stack.pop();
                // stack為後進先出（順序與原本相反），所以要用b/a
                stack.push(b / a);                
            } else{
                // 若c非為運算符號，就把c丟入stack（待運算）
                stack.push(Integer.parseInt(c));
            }
        }
        // stack最後會剩一個元素為result
        return stack.pop();
    }
}
