/*
4. Meet In The Middle (BFS)
用兩個 queue 分別從 beginWord 和 endWord 同時展開 BFS，直到中間相遇為止。這樣能有效減少搜尋空間，將複雜度從 O(N) 降到 O(N/2)。
*/
public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 如果 endWord 不在字典中，或與起點相同，無法轉換
        if (!wordList.contains(endWord) || beginWord.equals(endWord))
            return 0;

        int m = wordList.get(0).length();  // 單字長度固定
        Set<String> wordSet = new HashSet<>(wordList);  // 轉為 Set 加速查找

        // 雙向 BFS 的 queue
        Queue<String> qb = new LinkedList<>(), qe = new LinkedList<>();
        // 分別記錄從 begin 和 end 擴展時，每個單字的轉換步數
        Map<String, Integer> fromBegin = new HashMap<>();
        Map<String, Integer> fromEnd = new HashMap<>();

        qb.add(beginWord);
        qe.add(endWord);
        fromBegin.put(beginWord, 1);  // 起點視為第 1 步
        fromEnd.put(endWord, 1);      // 終點視為第 1 步

        // 只要兩個 BFS 都還有東西，就繼續
        while (!qb.isEmpty() && !qe.isEmpty()) {
            // 永遠擴展較小的 queue，減少搜尋空間 (若qb.size() > qe.size()就把兩者對調，所以小的永遠是qb)
            if (qb.size() > qe.size()) {
                Queue<String> tempQ = qb;
                qb = qe;
                qe = tempQ;

                Map<String, Integer> tempMap = fromBegin;
                fromBegin = fromEnd;
                fromEnd = tempMap;
            }

            int size = qb.size();
            for (int k = 0; k < size; k++) {
                String word = qb.poll();
                int steps = fromBegin.get(word);  // 當前單字的步數

                // 嘗試替換 word 的每一個位置上的字母
                for (int i = 0; i < m; i++) {
                    for (char c = 'a'; c <= 'z'; c++) {
                        if (c == word.charAt(i)) continue;

                        // 替換第 i 個字母為 c，得到新字
                        String nei = word.substring(0, i) + c + word.substring(i + 1);
                        if (!wordSet.contains(nei)) continue;  // 不在字典中跳過

                        // 如果這個字出現在對向 BFS 中，表示兩邊相遇
                        if (fromEnd.containsKey(nei))
                            return steps + fromEnd.get(nei);

                        // 若沒走過，就加入 queue 並記錄步數
                        if (!fromBegin.containsKey(nei)) {
                            fromBegin.put(nei, steps + 1);
                            qb.add(nei);
                        }
                    }
                }
            }
        }

        return 0;  // 若沒找到，回傳 0
    }
}

/*
// 範例輸入
beginWord = "hit"
endWord = "cog"
wordList = ["hot", "dot", "dog", "lot", "log", "cog"]

// 初始化：雙向 queue 及對應步數 map
qb = ["hit"]               // begin 端的 BFS queue
qe = ["cog"]               // end 端的 BFS queue
fromBegin = {"hit": 1}     // 起點步數初始化為 1
fromEnd = {"cog": 1}       // 終點步數初始化為 1

// 第一輪擴展：從 "hit" 出發
// 可變換一個字母且在字典中的為 "hot"
qb = ["hot"]
fromBegin = {"hit": 1, "hot": 2}

// 第二輪擴展：從 "cog" 出發
// 可變換一個字母且在字典中的為 "dog", "log"
qe = ["dog", "log"]
fromEnd = {"cog": 1, "dog": 2, "log": 2}

// 第三輪擴展：從 "hot" 出發
// 可變換為 "dot", "lot"
qb = ["dot", "lot"]
fromBegin = {"hit": 1, "hot": 2, "dot": 3, "lot": 3}

// 第四輪擴展：從 "dot" 出發
// 可變換為 "dog"，且 dog 在 fromEnd 裡面 → 兩邊 BFS 相遇
// 從 begin 端走到 "dot" 為 3 步，從 end 端走到 "dog" 為 2 步

// 因此回傳答案為：3 + 2 = 5

*/