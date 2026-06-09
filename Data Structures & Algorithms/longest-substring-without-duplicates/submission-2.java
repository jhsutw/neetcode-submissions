// sliding window (optimal)
class Solution {
    public int lengthOfLongestSubstring(String s) {
        // 創建HashMap紀錄（字元, 出現的index）
        HashMap<Character, Integer> mp = new HashMap<>();
        int l = 0, res = 0;

        for (int r = 0; r < s.length(); r++){
            if (mp.containsKey(s.charAt(r))){
                // 把下界更新為現在下界的下一個值
                // 不能寫 l = mp.get(s.charAt(r)) + 1
                // 因為當重複字元在 l 左邊，其實根本不會影響目前的子字串，但會把 l 錯誤地往回拉，造成視窗倒退、結果錯誤！
                l = Math.max(mp.get(s.charAt(r)) + 1, l);
            }

            // mp中放入（字元：出現的index）
            mp.put(s.charAt(r), r);
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
