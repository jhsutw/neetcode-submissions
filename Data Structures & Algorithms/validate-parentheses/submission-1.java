// brute force
class Solution {
    public boolean isValid(String s) {
        // 符號可以一起寫不用兩邊分開寫
        while (s.contains("()") || s.contains("{}") ||
        s.contains("[]")){
            s = s.replace("()", "");
            s = s.replace("{}", "");
            s = s.replace("[]", "");
        }
        return s.isEmpty();
    }
}
