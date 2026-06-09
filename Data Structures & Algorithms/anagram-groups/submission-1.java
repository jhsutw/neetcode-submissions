// sol 1 是用排序好的 string 作為 key
// sol 2 是用string的char point arraylist 作為 key
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, ArrayList<String>> res = new HashMap<>();
        for (String s : strs){
            // 創建char point arraylist紀錄string中每個字母出現次數
            int[] count = new int[26];
            for (char c : s.toCharArray()){
                count[c - 'a']++;
            }
        // 把char point arraylist變成string的形式再放入HashMap中
        String key = Arrays.toString(count);
        // 若沒有該key則創立key及value（空的陣列）
        res.putIfAbsent(key, new ArrayList<>());
        // 若有該key則找到該key的value並add s
        res.get(key).add(s);
        }
        // 不可以只寫res.values()，要先創造一個記憶空間給它
        return new ArrayList<>(res.values());
    }
}
