class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // 創建一個HashMap來放s1的（字元, 頻率)
        // 先把str轉成charArray才能一一放入HashMap中
        Map<Character, Integer> count1 = new HashMap<>();
        for (char c : s1.toCharArray()){
            count1.put(c, count1.getOrDefault(c, 0) + 1);
        }

        // 設定need（count1的長度）為配對的終止條件
        // 不能寫need = s1.length(); 因為重複值會被計算；用Hashmap計算size不會計算重複值
        int need = count1.size();
        for (int i = 0; i < s2.length(); i++){
            // 遍歷以i為起點的子字串，看是否包含s1
            // 若s2子字串每個字元的HashMap計數跟s1相同，回傳true
            Map<Character, Integer> count2 = new HashMap<>();
            int cur = 0;
            for (int j = i; j < s2.length(); j++){
                char c = s2.charAt(j);
                count2.put(c, count2.getOrDefault(c, 0) + 1);

                // s2子字串出現了s1沒出現的字元，換下一個i為起點找相符的子字串
                if (count1.getOrDefault(c, 0) < count2.get(c)){
                    break;
                } // 若s2子字串出現了s1出現的字元，就檢查下一個字元是否也在s1出現過
                if (count1.getOrDefault(c, 0) == count2.get(c)){
                    cur++;
                } // 當兩HashMap長度相同時，return true
                if (cur == need){
                    return true;
                }

            }
        }
        return false;
    }
}
