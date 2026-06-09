class Solution {
    public String encode(List<String> strs) {
        StringBuilder res = new StringBuilder();
        for (String str : strs) {
            res.append(str.length()).append("#").append(str);
        }
        return res.toString();
    }

    public List<String> decode(String str) {
        List<String> res = new ArrayList<>();
        int i = 0;
        while (i < str.length()) {
            int slash = str.indexOf("#", i); // 從 index i 開始，找第一個 # 的位置
            int size = Integer.parseInt(str.substring(i, slash)); // Integer.parseInt() 把字串轉成整數; str.substring() 取 i ~ slash - 1 字串
            i = slash + 1;
            res.add(str.substring(i, i + size));
            i += size;
        }
        return res;
    }
}