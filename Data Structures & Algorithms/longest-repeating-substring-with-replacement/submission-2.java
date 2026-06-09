// sliding window (optimal)
// 做法跟上一個方法差不多，只是把HashSet搭配count改成HashMap一次處理
class Solution {
    public int characterReplacement(String s, int k) {
        // 建立一個HashMap紀錄string中出現的（字元, 頻率)
        HashMap<Character, Integer> count = new HashMap<>();
        int res = 0;

        // 用sliding windows的方式找每個char的出現次數，紀錄進HashMap中並找出出現頻率最高的字元(maxf)
        int l = 0, maxf = 0;
        for (int r = 0; r < s.length(); r++){
            count.put(s.charAt(r), count.getOrDefault(s.charAt(r), 0) + 1);
            maxf = Math.max(maxf, count.get(s.charAt(r)));

            // 若substring中的字無法透過k次字元轉換的機會都變成s.charAt(r)
            // 將左界往右移（若左界值剛好為s.charAt(r)，那出現頻率-1）
            while ((r - l + 1) - maxf > k){
                count.put(s.charAt(l), count.get(s.charAt(l)) - 1);
                l++;
            }
            // 更新最常出現的字元計數（轉換k字元後）
            res = Math.max(res, r - l + 1);
        }
        return res;
    }
}
