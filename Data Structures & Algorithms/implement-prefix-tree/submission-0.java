public class TrieNode {
    TrieNode[] children = new TrieNode[26]; // 26 個英文字母的子節點引用，初始化全為 null
    boolean endOfWord = false;              // 標記此節點是否代表一個完整單詞的結尾
}

public class PrefixTree {
    private TrieNode root;                  // 字首樹的根節點

    public PrefixTree() {
        root = new TrieNode();              // 建構時產生一個空的根節點
    }

    public void insert(String word) {
        TrieNode cur = root;                // 從根開始插入
        for (char c : word.toCharArray()) {
            int i = c - 'a';                // 計算對應在 children 陣列的索引
            if (cur.children[i] == null) {
                cur.children[i] = new TrieNode(); // 若無此字母的子節點，就創一個
            }
            cur = cur.children[i];          // 移到下一層繼續
        }
        cur.endOfWord = true;               // 最後一個字母節點標記為單詞結尾
    }

    public boolean search(String word) {
        TrieNode cur = root;                // 從根開始搜尋
        for (char c : word.toCharArray()) {
            int i = c - 'a';
            if (cur.children[i] == null) {
                return false;               // 沒有這條分支 → 代表字元不相同、就找不到
            }
            cur = cur.children[i];          // 移到下一層繼續
        }
        return cur.endOfWord;               // 只有結尾標誌為 true 才認為找到完整單詞
    }

    public boolean startsWith(String prefix) {
        TrieNode cur = root;                // 從根開始檢查前綴
        for (char c : prefix.toCharArray()) {
            int i = c - 'a';
            if (cur.children[i] == null) {
                return false;               // 沒有這條分支 → 代表字元不相同、就找不到
            }
            cur = cur.children[i];
        }
        return true;                        // 整個前綴都存在，即為 true
    }
}

// 以下為執行流程示例：
// PrefixTree trie = new PrefixTree();
// trie.insert("apple");            // 插入單詞 "apple"
// trie.search("apple");            // 回傳 true（完整單詞存在）
// trie.search("app");              // 回傳 false（"app" 不是完整單詞）
// trie.startsWith("app");          // 回傳 true（存在以 "app" 為前綴的單詞）
// trie.insert("app");              // 再插入單詞 "app"
// trie.search("app");              // 回傳 true（現在 "app" 也是完整單詞）