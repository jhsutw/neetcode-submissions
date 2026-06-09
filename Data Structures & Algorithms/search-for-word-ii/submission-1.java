// 2. Backtracking (Trie + Hash Set)
class TrieNode {
    Map<Character, TrieNode> children;  // 子節點映射：字元 → 下一層 TrieNode
    boolean isWord;                     // 標記此節點是否為完整單詞結尾

    public TrieNode() {
        children = new HashMap<>();     // 初始化空的子節點映射
        isWord = false;                 // 預設不是單詞結尾
    }

    public void addWord(String word) {
        TrieNode cur = this;
        for (char c : word.toCharArray()) {
            // 若子節點中尚無該字元，就新增一個節點
            cur.children.putIfAbsent(c, new TrieNode());
            cur = cur.children.get(c);  // 移動到下一層
        }
        cur.isWord = true;              // 最後一個字母節點標記為單詞結尾
    }
}

public class Solution {
    private Set<String> res;      // 用 Set 去重並收集結果
    private boolean[][] visit;    // 記錄已訪問格子，避免回頭

    public List<String> findWords(char[][] board, String[] words) {
        // 1. 建立字典樹（Trie）
        TrieNode root = new TrieNode();
        for (String w : words) {
            root.addWord(w);
        }

        int ROWS = board.length, COLS = board[0].length;
        res = new HashSet<>();
        visit = new boolean[ROWS][COLS];

        // 2. 對棋盤每一個起點啟動 DFS
        for (int r = 0; r < ROWS; r++) {
            for (int c = 0; c < COLS; c++) {
                dfs(board, r, c, root, "");
            }
        }
        return new ArrayList<>(res);
    }

    private void dfs(char[][] board, int r, int c, TrieNode node, String word) {
        int ROWS = board.length, COLS = board[0].length;
        // 邊界、已訪、或當前字母不在 Trie 節點的子映射中 → 直接剪枝
        if (r < 0 || c < 0 || r >= ROWS ||
            c >= COLS || visit[r][c] ||
            !node.children.containsKey(board[r][c])) {
            return;
        }

        visit[r][c] = true;                            // 標記已訪
        node = node.children.get(board[r][c]);         // 取得下層 TrieNode
        word += board[r][c];                           // 累積目前路徑字串

        if (node.isWord) {                             // node是單詞結尾
            res.add(word);                             // 若構成完整單詞，加入結果
        }

        // 四個方向繼續 DFS
        dfs(board, r + 1, c, node, word);
        dfs(board, r - 1, c, node, word);
        dfs(board, r, c + 1, node, word);
        dfs(board, r, c - 1, node, word);

        visit[r][c] = false;                           // 回溯：取消標記
    }
}

// 以下為執行流程示例：
// board = [
//   ['o','a','a','n'],
//   ['e','t','a','e'],
//   ['i','h','k','r'],
//   ['i','f','l','v']
// ]
// words = ["oath","pea","eat","rain"]

// 1. Trie 建立完畢，包含所有單詞路徑。
// 2. 在 (0,0) 發現 'o'，匹配進 Trie，再走右→下→下→右，可依序匹配 o→a→t→h，找到 "oath"。
// 3. 繼續從其他起點探索，最終也能匹配 "eat"。
// 4. 因為 Trie 剪枝，沒有完整路徑的 "pea"、"rain" 被快速跳過。
// 最終回傳 ["oath","eat"]（順序可變）。