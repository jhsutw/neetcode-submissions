class Solution {
    public String minWindow(String s, String t) {
        // 若t為空，return ""
        if (t.isEmpty()) return "";

        // 建立兩個HashMap來紀錄s和t的（字元, 頻率）
        Map<Character, Integer> countT = new HashMap<>();
        Map<Character, Integer> window = new HashMap<>();
        for (char c : t.toCharArray()){
            countT.put(c, countT.getOrDefault(c, 0) + 1);
        }

        int need = countT.size(); // need：t中所有不重複字元數
        int have = 0; // 目前s/t字元match的數量
        int[] res = {-1, -1}; // res：紀錄結果substring的起始、終止index
        int resLen = Integer.MAX_VALUE; // resLen：把目前字串最小長度設為極大值
        int l = 0; // l：左界的index

        for (int r = 0; r < s.length(); r++){
            char c = s.charAt(r);
            window.put(c, window.getOrDefault(c, 0) + 1);

            // 若向右（右界右移）多放入一個字元會讓該字元的出現頻率於t/window相同，have++（配對加一）
            if (countT.containsKey(c) && window.get(c).equals(countT.get(c))){
                have++;
            }

            // 當已經符合條件後，開始找最短的window長度（左界右移）
            while (need == have){
                if ((r - l + 1) < resLen){ // 若當前的substring小於當前最小值才更新
                    resLen = r - l + 1; // resLen：目前substring長度
                    res[0] = l; 
                    res[1] = r;
                }
                
                // 把左界右移
                // 若左界值的字元剛好為t中的字元，並且會讓其於window中的出現頻率小於t，have--（配對-1）
                // 之所以have--而非不左界右移，是因為可以再把右界右移去找該值（反正如果後來找到的substring長度大於現有的，resLen也不會被更新）
                char leftChar = s.charAt(l);
                window.put(leftChar, window.get(leftChar) - 1);
                if (countT.containsKey(leftChar) && window.get(leftChar) < countT.get(leftChar)){
                    have--;
                }
                l++;
            }
        }
        // resLen == Integer.MAX_VALUE? 代表t並沒有與任何一個window配對成功
        return resLen == Integer.MAX_VALUE? "" : s.substring(res[0], res[1] + 1);
    }
}
