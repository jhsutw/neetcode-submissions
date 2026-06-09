class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // 如果s1的長度大於s2 return false
        if (s1.length() > s2.length()){
            return false;
        }

        // 初始化sliding window
        // 把s1所有字元放入HashMap（字元, 頻率）
        // 把s2的前i個字元（跟s1等長）放入HashMap（字元, 頻率）
        int[] s1Count = new int[26];
        int[] s2Count = new int[26];
        for (int i = 0; i < s1.length(); i++){
            s1Count[s1.charAt(i) - 'a']++;
            s2Count[s2.charAt(i) - 'a']++;
        }

        int matches = 0;
        // 計算初始化的sliding window（s2）有幾個字元跟s1配對到
        for (int i = 0; i < 26; i++){
            if (s1Count[i] == s2Count[i]){
                matches++;
            }
        }

        // 設定右界
        int l = 0;
        // 把sliding window向右移動
        for (int r = s1.length(); r < s2.length(); r++){
            // 設定配對成共終點線
            if (matches == 26){
                return true;
            }

            // 若sliding window向右新增的字元會讓其跟s1的‘s1Count[index]元素數量相等，matches++
            // 若反而讓其比s1的‘s1Count[index]元素數量多出現一次（原本相等），matches--
            int index = s2.charAt(r) - 'a';
            s2Count[index]++;
            if (s1Count[index] == s2Count[index]){
                matches++;
            } else if (s1Count[index] + 1 == s2Count[index]){
                matches--;
            }

            // 若sliding window向左刪減的字元會讓其跟s1的‘s1Count[index]元素數量相等（原本多出現一次），matches++
            // 若反而讓其比s1的‘s1Count[index]元素數量少出現一次（原本相等），matches--
            index = s2.charAt(l) - 'a';
            s2Count[index]--;
            if (s1Count[index] == s2Count[index]){
                matches++;
            } else if (s1Count[index] - 1 == s2Count[index]) {
                matches--;
            }
            l++;
        }
        return matches == 26;
    }
}
