// 3. Hierholzer's Algorithm (Iteration)
public class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        // 建立鄰接表，並將目的地放入 PriorityQueue（確保字典序最小的城市先被處理）
        Map<String, PriorityQueue<String>> adj = new HashMap<>();
        for (List<String> ticket : tickets) {
            adj.computeIfAbsent(ticket.get(0), k -> new PriorityQueue<>())
                .add(ticket.get(1)); // 加入目的地
        }

        // LinkedList 用於從前面插入機場名稱，建構路徑
        LinkedList<String> res = new LinkedList<>();

        // Stack 模擬遞迴的過程 (用stack剛好解決post-order問題)
        Stack<String> stack = new Stack<>();
        stack.push("JFK"); // 旅程從 JFK 機場出發

        // 開始 DFS
        while (!stack.isEmpty()) {
            String curr = stack.peek(); // 查看目前機場

            // 如果當前機場已經沒有下一站了，表示這段路走到底了
            if (!adj.containsKey(curr) || adj.get(curr).isEmpty()) {
                res.addFirst(stack.pop()); // 從 stack 彈出，並插入結果的最前面（回程路徑）
            } else {
                // 還有可走的航班，就從 PriorityQueue 拿出字典序最小的下一站
                stack.push(adj.get(curr).poll());
            }
        }

        // 回傳最終的行程路線
        return res;
    }
}