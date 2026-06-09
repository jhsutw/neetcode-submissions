class Solution {
    public boolean isValid(String s) {
        Stack<Character> stack = new Stack<>();
        Map<Character, Character> closeToOpen= new HashMap<>();

        closeToOpen.put(')', '(');
        closeToOpen.put('}', '{');
        closeToOpen.put(']', '[');

        // 把s的字串與closeToOpen中的字串作配對，同時留意前後順序
        for (char c : s.toCharArray()){
            // 若c為“關括號”，檢查前一個被丟入stack中的是否是“開括號”，是的話把開括號pop掉（配對成功！）；否則return false（順序錯誤！）
            // 先開的括號要先關 i.e. {[()]}
            // stack的特性為“後進先出”
            if (closeToOpen.containsKey(c)){
                if (!stack.isEmpty() && stack.peek() == closeToOpen.get(c)){
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                // 若c為開括號，push進stack中
                stack.push(c);
            }
        }
        // 最後stack為empty則return ture
        return stack.isEmpty();
    }
}
