public class Solution {
    
    // encode：[“apple”, “ok”] -> “5,2,#appleok”
    public String encode(List<String> strs) {
        // 避免空值錯誤
        if (strs.isEmpty()) return "";
        // StringBuilder比String更靈活！
        /*
        Ｓtring 每次用 + 連接字串都會產生新字串物件。
        StringBuilder 則是直接在原來的 buffer 上修改，不會創建新物件。
        StringBuilder 支援許多操作，例如：
            .append()：加字串
            .insert()：插入字串
            .delete()：刪除特定範圍字串
            .reverse()：反轉字串
            .setCharAt()：修改特定位置字元
        */
        StringBuilder res = new StringBuilder();
        List<Integer> sizes = new ArrayList<>();
        // 紀錄每個str的長度
        for (String str : strs) {
            sizes.add(str.length());
        }
        // 先輸出size至res
        for (int size : sizes) {
            res.append(size).append(',');
        }
        // 用#符號隔開size及str
        res.append('#');
        for (String str : strs) {
            res.append(str);
        }
        // 把res從stringbuilder轉回str
        return res.toString();
    }

    // decode：“5,2,#appleok” -> [“apple”, “ok”] 
    public List<String> decode(String str) {
        // 避免空值 > 回傳空ArrayList
        if (str.length() == 0) {
            return new ArrayList<>();
        }
        List<String> res = new ArrayList<>();
        List<Integer> sizes = new ArrayList<>();
        int i = 0;
        // 遞迴str每個char，若char不為#
        while (str.charAt(i) != '#') {
            StringBuilder cur = new StringBuilder();
            while (str.charAt(i) != ',') {
                cur.append(str.charAt(i));
                i++;
            }
            sizes.add(Integer.parseInt(cur.toString()));
            i++;
        }
        i++;
        for (int sz : sizes) {
            res.add(str.substring(i, i + sz));
            i += sz;
        }
        return res;
    }
}