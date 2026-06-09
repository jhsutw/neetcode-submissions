// 2. Breadth First Search - II
public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 如果 endWord 不在 wordList 中，或與 beginWord 相同，無法轉換
        if (!wordList.contains(endWord) || beginWord.equals(endWord)) return 0;

        // 將 wordList 存入 HashSet 以加快查找速度 (List可以直接轉HashSet！)
        Set<String> words = new HashSet<>(wordList);

        int res = 0;  // 儲存目前的轉換步數
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);  // 將初始單詞加入 BFS queue

        // 開始 BFS
        while (!q.isEmpty()) {
            res++;  // 每進一層，代表多了一步轉換
            for (int i = q.size(); i > 0; i--) {
                String node = q.poll();  // 拿出目前層的節點
                if (node.equals(endWord)) return res;  // 找到目標，回傳步數

                // 嘗試替換 node 的每一個字母位置 (把node每個字母替換成a~z其他char)
                for (int j = 0; j < node.length(); j++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == node.charAt(j)) continue;  // 忽略相同字元

                        // 替換 j 位置的字元
                        String nei = node.substring(0, j) + c + node.substring(j + 1);

                        // 若新字存在於字典中
                        if (words.contains(nei)) {
                            q.offer(nei);       // 加入下一層的 BFS queue
                            words.remove(nei);  // 為避免重複訪問，立刻從字典移除（直接remove不用用visiting）
                        }
                    }
                }
            }
        }

        return 0;  // 若無法轉換為 endWord，回傳 0
    }
}

/*
✅ 第一個方法（建圖 + BFS）
// 建立字與字之間的連線（只差一個字母）
// 對 beginWord 特別處理連接點
// 使用 adjacency list 與 index 進行 BFS

✅ 第二個方法（不建圖 + inline BFS）
// 不建圖，直接在 BFS 時生成所有可能的鄰居（字母替換）
// 使用 wordList 的 HashSet 作為訪問字典
*/