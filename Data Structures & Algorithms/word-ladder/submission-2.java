/*
3. Breadth First Search - III
✅ Wildcard Pattern + BFS
建立 pattern（帶 * 號的模板）對應的鄰居清單，藉此快速找出只差一個字母的單詞。
*/

public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 如果 wordList 中沒有 endWord，則無法轉換
        if (!wordList.contains(endWord)) {
            return 0;
        }

        // 建立 pattern -> words 對應，例如 h*t -> [hot, hit]
        Map<String, List<String>> nei = new HashMap<>();
        wordList.add(beginWord);  // 將 beginWord 一併處理進 wordList

        for (String word : wordList) { // 把每個字的其中一個char變成*（cat -> *at, c*t. ca*）
            for (int j = 0; j < word.length(); j++) {
                String pattern = word.substring(0, j) + "*" + word.substring(j + 1);
                nei.computeIfAbsent(pattern, k -> new ArrayList<>()).add(word);
            }
        }

        Set<String> visit = new HashSet<>();    // 記錄訪問過的單詞
        Queue<String> q = new LinkedList<>();
        q.offer(beginWord);                     // BFS 起點
        int res = 1;                            // 層數（轉換步數）

        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; i++) {
                String word = q.poll();
                if (word.equals(endWord)) {
                    return res;                // 找到目標單詞，回傳轉換步數
                }

                // 根據 wildcard pattern 尋找所有只差一字的鄰居
                for (int j = 0; j < word.length(); j++) {
                    String pattern = word.substring(0, j) + "*" + word.substring(j + 1);
                    for (String neiWord : nei.getOrDefault(pattern, Collections.emptyList())) { // 遍歷符合該pattern的字串list
                        if (!visit.contains(neiWord)) {
                            visit.add(neiWord); // neiWord不是*形式是原形式
                            q.offer(neiWord);  // 加入 BFS queue
                        }
                    }
                }
            }
            res++; // 層數 +1
        }

        return 0;  // 若無法找到 endWord，則回傳 0
    }
}