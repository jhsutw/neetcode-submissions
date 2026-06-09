class Solution {

    public String encode(List<String> strs) {
        // ['apple', 'banana'] > "5#apple6#banana"
        StringBuilder res = new StringBuilder();
        for (String s : strs){
            res.append(s.length()).append('#').append(s);
        }
        // 把stringbuilder轉回string
        return res.toString(); 
    }

    // "5#apple6#banana" > ['apple', 'banana']
    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        // 先用j去找#位置，i與j之間的substring為字串的length
        int i = 0;
        while (i < str.length()){
            int j = i;
            while (str.charAt(j) != '#'){
                j++;
            }
            int length = Integer.parseInt(str.substring(i, j));
            // j為#位置，i = j + 1為string的首字母/ j = i + length為字串尾字母
            i = j + 1;
            j = i + length;
            res.add(str.substring(i, j));
            // 移動到下一組字串位置
            i = j;
        }
        return res;
    }
}
