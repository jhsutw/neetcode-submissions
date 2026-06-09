// brute force
public class Solution {
    public boolean checkInclusion(String s1, String s2) {
        // 先把s1的字母排序（先變成CharArray才能排序，排序完再轉回來Str）
        char[] s1Arr = s1.toCharArray();
        Arrays.sort(s1Arr);
        String sortedS1 = new String(s1Arr);
        

        for (int i = 0; i < s2.length(); i++){
            for (int j = i; j < s2.length(); j++){
                // 排序 i ~ j 之間的子字串
                // substring(i, j)取i ~ j - 1的字元，所以要j + 1
                char[] subStrArr = s2.substring(i, j + 1).toCharArray();
                Arrays.sort(subStrArr);
                String sortedSubStr = new String(subStrArr);

                // 比較 i ~ j 之間的子字串是否等於 s1
                if (sortedSubStr.equals(sortedS1)){
                    return true;
                }
            }
        }
        return false;
    }
}
