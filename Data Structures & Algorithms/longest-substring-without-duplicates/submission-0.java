class Solution {
    public int lengthOfLongestSubstring(String s) {
        int res = 0;
        // 遍歷每一個字元
        for (int i = 0; i < s.length(); i++){
            // 建立HashSet檢查是否有重複值
            Set<Character> charSet = new HashSet<>();
            // 從該字元往"後"（i = j）判定substring
            for (int j = i; j < s.length(); j++){
                if (charSet.contains(s.charAt(j))){
                    break;
                }
                charSet.add(s.charAt(j));
            }
            // 每檢查完一個字元後的substring長度，就跟最大值比較
            res = Math.max(res, charSet.size());
        }
        return res;
    }
}
