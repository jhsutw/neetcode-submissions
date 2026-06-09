// 1. Breadth First Search - I
public class Solution {
    public int ladderLength(String beginWord, String endWord, List<String> wordList) {
        // 如果 endWord 不在字典中，或 beginWord 就是 endWord，則無法轉換，回傳 0
        if (!wordList.contains(endWord) || beginWord.equals(endWord)) {
            return 0;
        }

        int n = wordList.size();               // 字典中單字數量
        int m = wordList.get(0).length();      // 每個單字的長度（題目保證一致）

        // 建立鄰接表，adj[i] 存的是與 wordList[i] 僅一字不同的其他單字的 index
        List<List<Integer>> adj = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());
        }

        // 將 wordList 中的單字與其 index 做映射，方便後續查找
        Map<String, Integer> mp = new HashMap<>();
        for (int i = 0; i < n; i++) {
            mp.put(wordList.get(i), i);
        }

        // 檢查 wordList 中每兩個單字是否只差一個字母，是的話就加邊
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                int cnt = 0;
                for (int k = 0; k < m; k++) { // 檢查兩個單字是否只差一個字母（遍歷index為i&j的每個char）
                    if (wordList.get(i).charAt(k) != wordList.get(j).charAt(k)) {
                        cnt++;
                    }
                }
                if (cnt == 1) { // 只有一個char不同
                    adj.get(i).add(j);  // i 和 j 相連
                    adj.get(j).add(i);
                }
            }
        }

        Queue<Integer> q = new LinkedList<>(); // BFS queue
        int res = 1;                           // 目前的轉換步數（初始為 1）
        Set<Integer> visit = new HashSet<>(); // 記錄已拜訪過的節點

        // 先處理 beginWord：找與其僅差一字的單字作為 BFS 起點
        for (int i = 0; i < m; i++) {
            for (char c = 'a'; c <= 'z'; c++) {
                if (c == beginWord.charAt(i)) continue; // 遇到相同的char跳過（一樣就不用轉換了！）
                // 嘗試將 beginWord 的每個字母換成 a-z 中的其他字母
                String word = beginWord.substring(0, i) + c + beginWord.substring(i + 1); // 把begin每個char都換成a~z其他char試看看
                if (mp.containsKey(word) && !visit.contains(mp.get(word))) { // 把創造出來的word跟其他word list中的詞配對
                    q.add(mp.get(word));         // 將可達的單字 index 放入 queue
                    visit.add(mp.get(word));     // 標記為已拜訪
                }
            }
        }

        // 開始 BFS，從 beginWord 的相鄰節點開始擴展
        while (!q.isEmpty()) {
            res++;                      // 每進入新一層，步數 +1
            int size = q.size();        // 當層節點數（所有符合轉換結果的word）
            for (int i = 0; i < size; i++) { // 嘗試所有可能的next word
                int node = q.poll();    // 取出當層節點
                if (wordList.get(node).equals(endWord)) {
                    return res;         // 找到 endWord，回傳轉換步數
                }
                for (int nei : adj.get(node)) {
                    if (!visit.contains(nei)) { // 避免重複走訪
                        visit.add(nei);
                        q.add(nei);     // 將下一層節點加入 queue
                    }
                }
            }
        }

        return 0; // 若走訪結束仍找不到 endWord，回傳 0（無法轉換）
    }
}