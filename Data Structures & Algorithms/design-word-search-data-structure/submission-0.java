public class WordDictionary {

    private List<String> store;                     // 用來儲存所有加入的單詞

    public WordDictionary() {
        store = new ArrayList<>();                  // 建構時初始化空的 ArrayList
    }

    public void addWord(String word) {
        store.add(word);                            // 將單詞加入列表中
    }

    public boolean search(String word) {
        // 對每個已加入的單詞進行比對
        for (String w : store) {
            if (w.length() != word.length()) continue;  // 長度不同就跳過
            int i = 0;
            // 逐字比對：相同字元或搜尋字串為 '.' 都視為匹配
            while (i < w.length()) {
                if (w.charAt(i) == word.charAt(i) || word.charAt(i) == '.') {
                    i++;
                } else {
                    break;                              // 有不匹配就退出迴圈
                }
            }
            if (i == w.length()) {
                return true;                           // 全部字元都匹配 → 找到
            }
        }
        return false;                                  // 對所有單詞都沒匹配 → 不存在
    }
}

// 以下為執行流程示例：
// WordDictionary dict = new WordDictionary();
// dict.addWord("bad");
// dict.addWord("dad");
// dict.addWord("mad");
//
// dict.search("pad");    // 回傳 false，列表中沒有 "pad"
// dict.search("bad");    // 回傳 true，完全匹配 "bad"
// dict.search(".ad");    // 回傳 true，'.' 可以匹配 'b','d','m'
// dict.search("b..");    // 回傳 true，'b' + 任兩個字元
// dict.search("..d");    // 回傳 true，任兩字元 + 'd'
// dict.search("...s");   // 回傳 false，長度不符或最後字元不同