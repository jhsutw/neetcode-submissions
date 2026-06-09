public class MinStack {
    long min;               // 用來記錄目前的最小值，使用 long 是為了避免溢位
                            // 超過 int 的最大值（2^31 - 1 = 2,147,483,647）時，就需要使用 long
    Stack<Long> stack;      // 主 stack，儲存的是 val - min 的差值

    public MinStack() {
        stack = new Stack<>();  // 初始化 stack
    }

    // 推入新元素
    public void push(int val) {
        if (stack.isEmpty()) {
            stack.push(0L);     // 若為第一個元素，差值是 0
                                // 如果你寫 stack.push(0) 而不是 stack.push(0L)，0 是 int，而你的 stack 是 Stack<Long>，會出現型別錯誤！
            min = val;          // 同時把該值記為 min
        } else {
            stack.push(val - min); // 存進 val 與目前 min 的差
            if (val < min) min = val; // 若 val 更小，更新 min
        }
    }

    // 移除頂部元素
    public void pop() {
        if (stack.isEmpty()) return;

        long pop = stack.pop();  // 取出頂部差值

        if (pop < 0) {
            // 如果 pop 為負，代表當初推入的是新的最小值
            // 要將 min 還原為前一個 min：min = min - (val - oldMin)
            min = min - pop;
        }
    }

    // 回傳頂部元素的實際值
    public int top() {
        long top = stack.peek();  // 取得頂部差值
        if (top > 0) {
            return (int) (top + min);  // 差值為正，代表原始值比 min 大，還原實際值
        } else {
            return (int) min;         // 差值為 0 或負，代表原始值是 min
        }
    }

    // 回傳目前的最小值
    public int getMin() {
        return (int) min;
    }
}