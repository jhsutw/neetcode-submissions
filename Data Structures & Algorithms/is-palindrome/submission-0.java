class Solution {
    public boolean isPalindrome(String s) {
        // 把str轉為stringbuilder
        StringBuilder newStr = new StringBuilder();
        // s.toCharArray(): str轉為CharArray方便提取
        // Character.isLetterOrDigit(c): 僅加入英文字母及數字（排除空格或其他符號）
        // Character.toLowerCase(c): 把英文字母變小寫
        for (char c : s.toCharArray()){
            if (Character.isLetterOrDigit(c)){
                newStr.append(Character.toLowerCase(c));
            }
        }

        // newStr.toString() 把stringbuilder轉回str
        // newStr.reverse() 把string前後顛倒（reverse是string builder的語法，不能顛倒過來寫）
        return newStr.toString().equals(newStr.reverse().toString());
    }
}
