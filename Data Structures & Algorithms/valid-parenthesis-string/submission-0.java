public class Solution {
    public boolean checkValidString(String s) {
        int leftMin = 0, leftMax = 0;  
        // leftMin：可能的最少左括號數量
        // leftMax：可能的最多左括號數量
        // 因為 * 可能當作 '('、')' 或空字元，所以範圍會浮動

        for (char c : s.toCharArray()) {
            if (c == '(') {
                // 遇到左括號時，最少與最多的左括號數都要 +1
                leftMin++;
                leftMax++;
            } else if (c == ')') {
                // 遇到右括號時，左括號數都要 -1
                leftMin--;
                leftMax--;
            } else {
                // 遇到 '*' 時，有三種可能：
                // * 當 '(' → leftMax +1
                // * 當 ')' → leftMin -1
                // * 當空字元 → 不變
                // 綜合起來：leftMin -1, leftMax +1
                leftMin--;
                leftMax++;
            }

            // 若 leftMax < 0，代表右括號太多，無法配對，直接回傳 false
            if (leftMax < 0) {
                return false;
            }

            // 若 leftMin < 0，代表可以把部分 ')' 或 '*' 視為空字元，重置為 0
            if (leftMin < 0) {
                leftMin = 0;
            }
        }

        // 最後若 leftMin == 0，代表可以完全匹配成功
        return leftMin == 0;
    }
}