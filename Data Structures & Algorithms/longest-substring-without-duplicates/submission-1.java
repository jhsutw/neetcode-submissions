// sliding window
class Solution {
    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> charSet = new HashSet<>();

        // 設定左界、result
        int l = 0;
        int res = 0;

        // 右界一直向右迭代，直到發現s.charAt(r)為重複值，刪除s.charAt(l)，代表s.charAt(l)就是s.charAt(r)的重複值
        // 更新左界為右邊一個值
        for (int r = 0; r < s.length(); r++){
            while (charSet.contains(s.charAt(r))){
                charSet.remove(s.charAt(l));
                l++;
            }
            // 把s.charAt(r)加回來
            charSet.add(s.charAt(r));
            // 比較新的substring是否比舊的長
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
