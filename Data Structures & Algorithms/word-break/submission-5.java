// 6. Dynamic Programming (Trie)
class TrieNode {
    HashMap<Character, TrieNode> children; // 存放子節點的映射（每個字元對應下一個 TrieNode）
    boolean isWord; // 標記從 root 到此節點的字元路徑是否構成一個完整的字典單字

    public TrieNode() {
        children = new HashMap<>();
        isWord = false; // 預設不是單字結尾
    }
}

class Trie {
    TrieNode root;

    public Trie() {
        root = new TrieNode(); // 初始化根節點
    }

    // 將字典中的一個單字插入到 Trie 中
    public void insert(String word) {
        TrieNode node = root;
        for (char c : word.toCharArray()) {
            // 如果當前節點沒有對應字元的子節點，建立一個新的
            node.children.putIfAbsent(c, new TrieNode());
            node = node.children.get(c); // 移動到子節點
        }
        node.isWord = true; // 單字結尾標記
    }

    // 檢查 s[i...j] 這段字串是否在 Trie 中存在並且是字典單字
    public boolean search(String s, int i, int j) {
        TrieNode node = root;
        for (int idx = i; idx <= j; idx++) {
            if (!node.children.containsKey(s.charAt(idx))) {
                return false; // 中途沒有對應字元，直接返回 false
            }
            node = node.children.get(s.charAt(idx));
        }
        return node.isWord; // 只有走到單字結尾才算 true
    }
}

public class Solution {
    public boolean wordBreak(String s, List<String> wordDict) {
        Trie trie = new Trie();
        // 將字典中的每個單字插入 Trie
        for (String word : wordDict) {
            trie.insert(word);
        }

        int n = s.length();
        boolean[] dp = new boolean[n + 1];
        dp[n] = true; // 空字串視為可切分

        // 找出字典中最長單字的長度，優化搜尋範圍
        int maxLen = 0;
        for (String word : wordDict) {
            maxLen = Math.max(maxLen, word.length());
        }

        // 從字串尾端往前 DP
        for (int i = n - 1; i >= 0; i--) {
            // 嘗試從 i 開始，檢查長度不超過 maxLen 的子字串
            for (int j = i; j < Math.min(n, i + maxLen); j++) {
                // 如果 s[i...j] 在字典中
                if (trie.search(s, i, j)) {
                    // 那麼 dp[i] 的值取決於 dp[j + 1]
                    // （切掉 s[i...j] 後的剩餘部分是否能成功切分）
                    dp[i] = dp[j + 1];
                    if (dp[i]) break; // 已經找到一種切分方式，提前結束
                }
            }
        }

        return dp[0]; // 從 s[0] 開始是否能成功切分
    }
}