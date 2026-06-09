public class Solution {

    // 存放最終所有可能的字母組合
    private List<String> res = new ArrayList<>();

    // 數字到字母的映射（索引 0、1 不對應任何字母）
    private String[] digitToChar = {
        "",     // 0
        "",     // 1
        "abc",  // 2
        "def",  // 3
        "ghi",  // 4
        "jkl",  // 5
        "mno",  // 6
        "qprs", // 7  （注意：LeetCode 題目通常是 "pqrs"）
        "tuv",  // 8
        "wxyz"  // 9
    };

    /**
     * 主函式：給定一串數字，返回所有可能的字母組合
     * @param digits 輸入的數字字串（'2'–'9'）
     * @return       所有對應的字母組合
     */
    public List<String> letterCombinations(String digits) {
        // 如果輸入為空，直接回傳空的結果集
        if (digits.isEmpty()) {
            return res;
        }
        // 從第 0 個數字開始回溯
        backtrack(0, "", digits);
        return res;
    }

    /**
     * 回溯函式：構建第 i 個位置的字母，累積到 curStr
     * @param i       當前處理到 digits 的索引
     * @param curStr  已經構建好的前綴字串
     * @param digits  原始數字字串
     */
    private void backtrack(int i, String curStr, String digits) {
        // 當前綴長度等於輸入數字長度，代表已構成一組完整組合
        if (curStr.length() == digits.length()) {
            res.add(curStr);
            return;
        }
        // 取出當前數字對應的所有字母 (i表示當前處理的digit / curStr表示當前加入的英文字母)
        int digit = digits.charAt(i) - '0';      // 轉成整數 2–9
        String letters = digitToChar[digit];     // 例如 digit=2 → "abc"
        // 遍歷每個字母，將其追加到前綴並遞迴處理下一位
        for (char c : letters.toCharArray()) {
            backtrack(i + 1, curStr + c, digits); // i + 1 換下一個digit；curStr + c 加入下一個字母（下一個digit中的）
        }
    }
}

/*
🧪 測試範例：

輸入: "23"
1) i=0, curStr="" → letters="abc"
   - 選 'a' → backtrack(1, "a", "23")
     2) i=1, curStr="a" → letters="def"
        - 選 'd' → backtrack(2, "ad", ...) → curStr 長度=2 → add "ad"
        - 選 'e' → add "ae"
        - 選 'f' → add "af"
   - 返回上一層

   - 選 'b' → backtrack(1, "b", ...)
        - 選 'd' → add "bd"
        - 選 'e' → add "be"
        - 選 'f' → add "bf"

   - 選 'c' → backtrack(1, "c", ...)
        - 選 'd' → add "cd"
        - 選 'e' → add "ce"
        - 選 'f' → add "cf"

最終 res = ["ad","ae","af","bd","be","bf","cd","ce","cf"]
*/
