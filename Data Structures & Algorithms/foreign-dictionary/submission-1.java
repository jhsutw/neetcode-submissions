// 2. Topological Sort (Kahn's Algorithm)
/*
1. 建構字母圖（adjacency list）與入度表
2. 用 w1[j] != w2[j] 找出首個不同字母來建立邊
3. 用 BFS 的拓撲排序（Kahn's Algorithm）來決定字母順序
4. 若排序結果無法涵蓋所有字母 → 說明圖中存在環（矛盾）→ 回傳空字串
*/
public class Solution {
    public String foreignDictionary(String[] words) {
        Map<Character, Set<Character>> adj = new HashMap<>(); // 鄰接表：記錄每個字母後面接哪些字母
        Map<Character, Integer> indegree = new HashMap<>();   // 記錄每個字母的入度（有多少前置字母）

        // 初始化圖中所有出現過的字母
        for (String word : words) {
            for (char c : word.toCharArray()) {
                adj.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        // 根據相鄰單詞比較首個不同字母，建立字母之間的順序關係
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i];
            String w2 = words[i + 1];
            int minLen = Math.min(w1.length(), w2.length());

            // 特例處理：若前一個單字是後一個的前綴，但更長 → 不合法
            if (w1.length() > w2.length() &&
                w1.substring(0, minLen).equals(w2.substring(0, minLen))) {
                return ""; // 無效字典順序
            }

            // 找到第一個不同字母，建立 w1[j] → w2[j] 的邊
            for (int j = 0; j < minLen; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    if (!adj.get(w1.charAt(j)).contains(w2.charAt(j))) {
                        adj.get(w1.charAt(j)).add(w2.charAt(j)); // 建立鄰接關係
                        indegree.put(w2.charAt(j),
                                     indegree.get(w2.charAt(j)) + 1); // 增加入度
                    }
                    break; // 只需建立第一個不同字母的關係
                }
            }
        }

        // 將所有入度為 0 的字母加入 queue（即沒有依賴其他字母）
        Queue<Character> q = new LinkedList<>();
        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                q.offer(c);
            }
        }

        StringBuilder res = new StringBuilder();
        // BFS 拓撲排序過程
        while (!q.isEmpty()) {
            char char_ = q.poll();     // 拿出入度為 0 的節點
            res.append(char_);         // 加入結果順序
            for (char neighbor : adj.get(char_)) {
                indegree.put(neighbor, indegree.get(neighbor) - 1); // 移除邊
                if (indegree.get(neighbor) == 0) {
                    q.offer(neighbor); // 若新的入度為 0，加入 queue
                }
            }
        }

        // 若結果長度小於圖中節點數 → 表示有環存在，拓撲排序失敗
        if (res.length() != indegree.size()) {
            return "";
        }

        return res.toString(); // 成功取得字母順序
    }
}