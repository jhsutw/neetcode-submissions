class Solution {
    public String minWindow(String s, String t) {
        // 若t為空，return ""
        if (t.isEmpty()) return "";

        // 建立HashMap存t的（字元, 頻率）    
        Map<Character, Integer> countT = new HashMap<>();

        for (char c : t.toCharArray()){
            countT.put(c, countT.getOrDefault(c, 0) + 1);
        }


        int[] res = {-1, -1}; // res：紀錄結果substring的起始、終止index
        int resLen = Integer.MAX_VALUE; // resLen：把目前字串最小長度設為極大值

        // 遍歷每一種可能性（i為子字串開頭, j為子字串結果）
        for (int i = 0; i < s.length(); i++){
            Map<Character, Integer> countS = new HashMap<>();
            for (int j = i; j < s.length(); j++){
                // 建立HashMap存每一個子字串的（字元, 頻率）  
                countS.put(s.charAt(j), countS.getOrDefault(s.charAt(j), 0) + 1);
                
                // 判斷每一個子字串是否包含t的所有字元
                boolean flag = true;
                for (char c : countT.keySet()){
                    if (countS.getOrDefault(c, 0) < countT.get(c)){
                        flag = false;
                        break;
                    }
                }

                // 符合上述條件就比較resLen與目前的子字串長度，若目前的子字串長度較小則更新resLen/res
                if (flag && (j - i + 1) < resLen){
                    resLen = j - i + 1;
                    res[0] = i;
                    res[1] = j;
                }
            }
        } 
        return resLen == Integer.MAX_VALUE? "" : s.substring(res[0], res[1] + 1);
    }
}
