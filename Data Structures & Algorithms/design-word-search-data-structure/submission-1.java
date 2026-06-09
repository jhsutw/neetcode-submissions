public class TrieNode {
    TrieNode[] children;  // 26 個英文字母對應的子節點陣列
    boolean word;         // 標記此節點是否代表一個完整單詞的結尾

    public TrieNode() {
        children = new TrieNode[26];  // 初始化所有子節點為 null
        word = false;                 // 預設不是單詞結尾
    }
}

public class WordDictionary {
    
    private TrieNode root;    // 字典的根節點

    public WordDictionary() {
        root = new TrieNode(); // 建構時建立一個空的根節點
    }

    public void addWord(String word) {
        TrieNode cur = root;   
        for (char c : word.toCharArray()) {
            int idx = c - 'a'; 
            // 如果對應字母的子節點不存在，就創建新節點
            if (cur.children[idx] == null) {
                cur.children[idx] = new TrieNode();
            }
            cur = cur.children[idx];  // 移到子節點
        }
        cur.word = true;  // 最後一個字母節點標記為單詞結尾
    }

    public boolean search(String word) {
        // 從根節點開始，呼叫遞迴函式處理 '.' 通配符
        return dfs(word, 0, root);
    }

    private boolean dfs(String word, int pos, TrieNode node) {
        TrieNode cur = node;
        for (int i = pos; i < word.length(); i++) {
            char c = word.charAt(i);
            if (c == '.') {
                // 遇到 '.'，嘗試所有非 null 的子節點
                for (TrieNode child : cur.children) {
                    if (child != null && dfs(word, i + 1, child)) { // 跳過'.'節點往後配對
                        return true;  // 任一分支匹配成功，即可回傳 true
                    }
                }
                return false; // 所有分支都失敗，回傳 false
            } else {
                int idx = c - 'a';
                // 如果對應子節點不存在，代表匹配失敗
                if (cur.children[idx] == null) {
                    return false;
                }
                cur = cur.children[idx];  // 繼續往下匹配
            }
        }
        // 遍歷完所有字元後，只有當前節點標記為單詞結尾才算找到
        return cur.word;
    }
}

// 以下為執行流程示例：
// WordDictionary dict = new WordDictionary();
// dict.addWord("bad");
// dict.addWord("dad");
// dict.addWord("mad");
//
// // 完整匹配測試
// dict.search("pad");    // 回傳 false，"pad" 不存在
// dict.search("bad");    // 回傳 true，"bad" 已加入
//
// // 通配符測試
// dict.search(".ad");    // 回傳 true，可匹配 "bad"、"dad"、"mad"
// dict.search("b..");    // 回傳 true，"b" + 任意兩字元 可匹配 "bad"
// dict.search("..d");    // 回傳 true，任意兩字元 + "d" 可匹配 "bad"、"dad"、"mad"
// dict.search("m..");    // 回傳 true，可匹配 "mad"
// dict.search("b.z");    // 回傳 false，因為 "b_z" 對應子節點不存在
