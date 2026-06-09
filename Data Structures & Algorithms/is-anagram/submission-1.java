public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }
    
    // using a HashMap to map key and value (如果沒有key/value只有單一元素就是用HashSet)
    HashMap<Character, Integer> CountS = new HashMap<>();
    HashMap<Character, Integer> CountT = new HashMap<>();

    // using .getOrDefault(s.charAt(i), 0) to get the value of a key from HashMap（還沒建立就設value為0）
    for (int i = 0; i < s.length(); i++){
        CountS.put(s.charAt(i), CountS.getOrDefault(s.charAt(i), 0) + 1);
        CountT.put(t.charAt(i), CountT.getOrDefault(t.charAt(i), 0) + 1);
    }

    // HashMap 比較 value 值用法：a.equals(b) v.s. Arrays 比較是否相同 Arrays.equals(a, b)
    return CountS.equals(CountT);
    }
}
