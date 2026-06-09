public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }

        // create a int array to record the existence of each char
        int[] count = new int[26];
        for (int i = 0; i < s.length(); i++){
            // s.charAt(i) - 'a' (unicode 數字相減 would be an int)
            count[s.charAt(i) - 'a']++;
            count[t.charAt(i) - 'a']--;
        }

        // iterate through the int array to check if s&t have the same amount of each char
        for (int val : count){
            if (val != 0){
                return false;
            }
        }
        return true;
    }
}
