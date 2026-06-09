public class Solution {
    
    /**
     * 主函式：利用迭代的方式，將数字串映射为所有可能的字母组合
     * @param digits 输入的数字字符串（'2'–'9'）
     * @return       对应的所有字母组合
     */
    public List<String> letterCombinations(String digits) {
        // 如果输入为空，直接返回空列表
        if (digits.isEmpty()) {
            return new ArrayList<>();
        }
        
        // 初始结果集只包含一个空串，方便后续拼接
        List<String> res = new ArrayList<>();
        res.add("");
        
        // 数字到字母的映射表
        String[] digitToChar = {
            "",     // 0
            "",     // 1
            "abc",  // 2
            "def",  // 3
            "ghi",  // 4
            "jkl",  // 5
            "mno",  // 6
            "qprs", // 7 （注意：通常为 "pqrs"）
            "tuv",  // 8
            "wxyz"  // 9
        };

        // 对每一个数字字符，扩展当前结果集
        for (char digit : digits.toCharArray()) {
            List<String> tmp = new ArrayList<>();
            // 取出该数字对应的所有字母
            String letters = digitToChar[digit - '0'];
            // 对已有的每个组合，拼接上每一个可能的字母
            for (String curStr : res) {
                for (char c : letters.toCharArray()) {
                    tmp.add(curStr + c);
                }
            }
            // 更新结果集
            res = tmp;
        }
        return res;
    }
}

/*
🧪 示例演示 (digits = "23")：

初始 res = [""]

处理 '2' → letters = "abc"
   对 "" 拼接 → ["a","b","c"]

res 更新为 ["a","b","c"]

处理 '3' → letters = "def"
   对 "a" 拼接 → ["ad","ae","af"]
   对 "b" 拼接 → ["bd","be","bf"]
   对 "c" 拼接 → ["cd","ce","cf"]

最终 res = ["ad","ae","af","bd","be","bf","cd","ce","cf"]
*/