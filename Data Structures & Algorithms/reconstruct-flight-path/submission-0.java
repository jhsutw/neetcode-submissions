// 2. Hierholzer's Algorithm (Recursion)
// 使用 Hierholzer's Algorithm 來構造 Eulerian 路徑（每張票用一次）
// 遇到多條出邊時，優先走字典序較小的目的地
public class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        // 建立鄰接表，使用 PriorityQueue 讓每個節點的出邊按照字典序排序
        Map<String, PriorityQueue<String>> adj = new HashMap<>();
        for (List<String> ticket : tickets) {
            String src = ticket.get(0);  // 出發機場
            String dst = ticket.get(1);  // 目的地機場
            // 如果 src 還沒有對應的 queue，就建立一個，並加入目的地
            adj.computeIfAbsent(src, k -> new PriorityQueue<>()).offer(dst);
        }

        List<String> res = new ArrayList<>();
        dfs(adj, "JFK", res);  // 從 JFK 開始 DFS 構造行程

        // 由於是 post-order 加入的，最後要 reverse 才是正確順序
        Collections.reverse(res);
        return res;
    }

    private void dfs(Map<String, PriorityQueue<String>> adj,
                     String src, List<String> res) {
        // 取得從當前 src 出發的目的地 queue（按字典序排序）
        PriorityQueue<String> queue = adj.get(src);

        // 只要還有票（出邊），就一直走
        while (queue != null && !queue.isEmpty()) {
            String dst = queue.poll();  // 選擇字典序最小的下一站
            dfs(adj, dst, res);         // 繼續遞迴走訪下一個機場
        }

        // 當前機場沒有更多出邊了，才把它加入結果（post-order）
        res.add(src);
    }
}