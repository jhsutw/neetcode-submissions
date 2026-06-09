// 1. Depth First Search
public class Solution {
    public List<String> findItinerary(List<List<String>> tickets) {
        // 建立鄰接表：key 是起點機場，value 是可到達的目的地機場列表
        Map<String, List<String>> adj = new HashMap<>();
        for (List<String> ticket : tickets) {
            adj.putIfAbsent(ticket.get(0), new ArrayList<>());
        }

        // 對票根據目的地排序，以便 DFS 時能夠走字典序最小的路徑 (依照目的地字母順序排序)
        tickets.sort((a, b) -> a.get(1).compareTo(b.get(1)));
        for (List<String> ticket : tickets) {
            adj.get(ticket.get(0)).add(ticket.get(1));
        }

        // 初始化結果列表，從 "JFK" 出發
        List<String> res = new ArrayList<>();
        res.add("JFK");

        // 啟動 DFS，如果找到合法路徑就回傳結果
        if (dfs("JFK", res, adj, tickets.size() + 1)) {
            return res;
        }

        // 找不到合法路徑則回傳空列表
        return new ArrayList<>();
    }

    // 回傳 boolean 表示是否成功找到一條使用所有機票的合法路徑
    private boolean dfs(String src, List<String> res,
                        Map<String, List<String>> adj, int targetLen) {
        // 如果目前結果長度 == 機票數量 + 1，表示已使用所有機票
        if (res.size() == targetLen) {
            return true;
        }

        // 若 src 沒有出邊（沒有目的地可以飛），直接回傳 false
        if (!adj.containsKey(src)) {
            return false;
        }

        // 複製 src 對應的目的地列表，避免直接修改時影響迴圈
        List<String> temp = new ArrayList<>(adj.get(src));
        for (int i = 0; i < temp.size(); i++) {
            String v = temp.get(i);

            // 嘗試使用這張票：在原本的 adj 清單中移除這張票，代表它已經被「用掉」
            adj.get(src).remove(i);
            res.add(v);

            // 若後續成功，則回傳 true
            if (dfs(v, res, adj, targetLen)) return true;

            // 如果失敗（回溯）：還原剛剛移除的票與結果
            adj.get(src).add(i, v);
            res.remove(res.size() - 1);
        }

        // 所有分支都失敗，返回 false
        return false;
    }
}