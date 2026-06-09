// 用Hashmap來存（sorted String, anagrams list）
class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, ArrayList<String>> res = new HashMap<>();
        for (String s : strs){
            // 先把每一個String都按照字母順序排列
            char[] charArray = s.toCharArray();
            Arrays.sort(charArray);
            String sortedS = new String(charArray);
            // 若HashMap中沒有該sorted string則創建新的Key（sorted String）、value（空的List）
            res.putIfAbsent(sortedS, new ArrayList<>());
            // 把string放入對應key（sortedS）的value list中
            res.get(sortedS).add(s);
        }
        // return HashMap中所有的value(all anagrams together into sublists)
        return new ArrayList<>(res.values());
    }
}
