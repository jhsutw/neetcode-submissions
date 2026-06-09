// 3. Backtracking (Trie)
class TrieNode {
    TrieNode[] children = new TrieNode[26]; // 26 字母子節點
    int idx = -1;                           // 該節點對應的 word 索引，-1 表示非結尾
    int refs = 0;                           // 經過該節點的字串數，用於剪枝

    // 將 words[i] 加入 Trie，並在節點上記錄 refs 與 idx
    public void addWord(String word, int i) {
        TrieNode cur = this;
        cur.refs++;                         // 根節點也要 refs++
        for (char c : word.toCharArray()) {
            int index = c - 'a';
            if (cur.children[index] == null) {
                cur.children[index] = new TrieNode();
            }
            cur = cur.children[index];
            cur.refs++;                     // 每到一節點，refs++
        }
        cur.idx = i;                        // 結尾節點記錄單詞在陣列中的索引（記錄目前配對的是哪個word，除了結尾node以外其他node的cur.ids均為-1）
    }
}

public class Solution {
    List<String> res = new ArrayList<>();   // 最終找到的單詞列表

    public List<String> findWords(char[][] board, String[] words) {
        // 1. 建立 Trie
        TrieNode root = new TrieNode();
        for (int i = 0; i < words.length; i++) {
            root.addWord(words[i], i);
        }

        // 2. 從每個格子啟動 DFS
        for (int r = 0; r < board.length; r++) {
            for (int c = 0; c < board[0].length; c++) {
                dfs(board, root, r, c, words);
            }
        }
        return res;
    }

    private void dfs(char[][] board, TrieNode node, int r, int c, String[] words) {
        // 剪枝：邊界、已訪或對應子節點為 null
        if (r < 0 || c < 0 || r >= board.length ||
            c >= board[0].length || board[r][c] == '*' ||
            node.children[board[r][c] - 'a'] == null) {
            return;
        }

        char temp = board[r][c];
        board[r][c] = '*';                       // 標記已訪
        TrieNode prev = node;                    
        node = node.children[temp - 'a'];        // 移到下層節點

        // 若該節點為某單詞結尾，加入結果並進行剪枝
        if (node.idx != -1) {
            res.add(words[node.idx]);            
            node.idx = -1;                       // 避免重複加入 (配對完把該單詞結尾的node.idx設為-1)
            node.refs--;                         // 刪除一條引用 (配對完畢後，經過此根節點的word-1)
            if (node.refs == 0) {                // 若無任何字串經過，剪掉此分支
                prev.children[temp - 'a'] = null;
                board[r][c] = temp;              // 還原標記
                return;
            }
        }

        // 四方向繼續 DFS
        dfs(board, node, r + 1, c, words);
        dfs(board, node, r - 1, c, words);
        dfs(board, node, r, c + 1, words);
        dfs(board, node, r, c - 1, words);

        board[r][c] = temp;                      // 回溯：還原格子
    }
}

// 執行流程示例：
// board = [
//   ['o','a','t','h'],
//   ['e','t','a','e'],
//   ['i','h','k','r'],
//   ['i','f','l','v']
// ];
// words = ["oath","eat","hat"]

// 1. Trie 結構：
//    root.refs=3 → o.refs=1→a.refs=1→t.refs=1→h.idx=0
//             ↘ e.refs=2→a.refs=2→t.idx=2 & t.refs=2→h.idx=2
//             ↘ h.refs=1→a.refs=1→t.idx=1
//
// 2. 從 (0,0) 'o' 開始 DFS：
//    匹配 o→a→t→h，遇 node.idx=0，res 加 "oath"。
//    node.refs-- 變 0，剪除 'h' 分支，提高後續效能。
//
// 3. 從其他起點繼續 DFS，可找到 "eat" 和 "hat"（視棋盤字母排列）。
//
// 最後 res = ["oath","eat","hat"]（順序不限）。