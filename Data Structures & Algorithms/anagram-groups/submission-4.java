class Solution {
    public List<List<String>> groupAnagrams(String[] strs) {
        List<List<String>> res = new ArrayList<>();
        Map<String, List<String>> map = new HashMap<>();
        for (String str : strs) {
            String strT = Arrays.toString(transform(str)); // int[] -> string
            if (map.containsKey(strT)) {
                map.get(strT).add(str);
            } else {
                List<String> list = new ArrayList<>();
                list.add(str);
                map.put(strT, list);
            }
        }
        
        for (List<String> list : map.values()) {
            res.add(list);
        }

        return res;
    }

    public int[] transform(String candidate) {
        int[] result = new int[26];

        for (char c : candidate.toCharArray()) {
            result[c - 'a']++;
        }

        return result;
    }
}