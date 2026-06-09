// brute force
class Solution {
    public int characterReplacement(String s, int k) {
        int res = 0;
        for (int i = 0; i < s.length(); i++){
            // 建立一個hashmap來記錄substring(i, j)中的（字元, 頻率）
            HashMap<Character, Integer> count = new HashMap<>();
            // substring中最常出現字元的字數
            int maxf = 0;
            for (int j = i; j < s.length(); j++){
                // 計算substring中各字元的出現次數
                count.put(s.charAt(j), count.getOrDefault(s.charAt(j), 0) + 1);
                // 把最常出現字元的字數存進maxf
                maxf = Math.max(maxf, count.get(s.charAt(j)));
                // 判定能不能用k個字元轉換機會把substring的字元都變成該最常出現字元
                if ((j - i + 1) - maxf <= k){
                    res = Math.max(res, j - i + 1);
                }
            }
        }
        return res;
    }
}
