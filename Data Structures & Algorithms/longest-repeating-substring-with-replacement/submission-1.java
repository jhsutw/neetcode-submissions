class Solution {
    public int characterReplacement(String s, int k) {
        int res = 0;
        // 創建HashSet紀錄string中出現的（無重複值）
        HashSet<Character> charSet = new HashSet<>();
        for (char c : s.toCharArray()){
            charSet.add(c);
        }

        // 用sliding windows的方式找每個charSet中的char的出現次數
        for (char c : charSet){
            int count = 0, l = 0;
            for (int r = 0; r < s.length(); r++){
                // 若char為c就將計數+1
                if (s.charAt(r) == c){
                    count++;
                }

                // 若substring中的字無法透過k次字元轉換的機會都變成c
                // 將左界往右移（若左界值剛好為c，那計數-1）
                while ((r - l + 1) - count > k){
                    if (s.charAt(l) == c){
                        count--;
                    }
                    l++;
                }
                // 更新最常出現的字元計數（轉換k字元後）
                res = Math.max(res, r - l + 1);
            }    
        }
        return res;
    }
}
