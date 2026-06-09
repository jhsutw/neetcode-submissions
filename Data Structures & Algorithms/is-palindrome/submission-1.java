class Solution {
    public boolean isPalindrome(String s) {
        // 初始化左右兩邊的字元位置（l右、r左），從外向內夾擊
        int l = 0, r = s.length() - 1;

        while (l < r){
            // 跳過左側非數字及英文字母的字元
            while (l < r && !alphaNum(s.charAt(l))){
                l++;
            }

            // 跳過右側非數字及英文字母的字元
            while (r > l && !alphaNum(s.charAt(r))){
                r--;
            }

            // 若左右兩邊的值不同就return false（Character.toLowerCase(s.charAt(l))排除大小寫差異）
            if (Character.toLowerCase(s.charAt(l)) != Character.toLowerCase(s.charAt(r))){
                return false;
            }
            l++; r--;
        }
        return true;
    }

    // 透過比較字元的 ASCII 值來保留數字及英文字母
    public boolean alphaNum(char c) {
        return (c >= 'A' && c <= 'Z' ||
                c >= 'a' && c <= 'z' ||
                c >= '0' && c <= '9');
    }
}
