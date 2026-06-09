class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> res = new HashMap<>();
        
        for (int i = 0; i < strs.length; i++) {
            int[] key = new int[26];
            String cur = strs[i];
            for (int j = 0; j < strs[i].length(); j++) {
                key[cur.charAt(j) - 'a']++;
            }

            String keyStr = Arrays.toString(key);
            res.putIfAbsent(keyStr, new ArrayList<>());
            res.get(keyStr).add(cur);
        }
        return new ArrayList<>(res.values());
    }
}
