public class Solution {
    public boolean isAnagram(String s, String t) {
        if (s.length() != t.length()){
            return false;
        }

        // using toCharArray to transform str into array
        char[] sSort = s.toCharArray();
        char[] tSort = t.toCharArray();

        // using .sort()/.equal() to compare two str
        Arrays.sort(sSort);
        Arrays.sort(tSort);
        return Arrays.equals(sSort, tSort);
    }
}
