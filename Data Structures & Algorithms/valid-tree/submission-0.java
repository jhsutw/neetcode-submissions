// 1. Cycle Detection (DFS)
public class Solution {
    public boolean validTree(int n, int[][] edges) {
        // 1. 樹必有 n-1 條邊；若邊數 > n-1，一定有迴路，直接回傳 false
        if (edges.length > n - 1) {
            return false;
        }

        // 2. 建鄰接表 (undirected graph)
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            adj.add(new ArrayList<>());  // 每個節點起始為空鄰接串列
        }
        for (int[] edge : edges) {
            // edge[0] ↔ edge[1]
            adj.get(edge[0]).add(edge[1]);
            adj.get(edge[1]).add(edge[0]);
        }

        // 3. 用 DFS 檢測環和連通性
        Set<Integer> visit = new HashSet<>();
        // 從節點 0 開始搜尋，parent 設 -1 表示無父節點
        if (!dfs(0, -1, visit, adj)) {
            return false;  // 檢測到迴路
        }

        // 4. 若所有節點都被訪問，則圖連通且無迴路 => 是一棵樹
        return visit.size() == n;
    }

    private boolean dfs(int node, int parent, Set<Integer> visit,
                        List<List<Integer>> adj) {
        // 若已訪問過，代表從另一條路徑回到此節點 => 迴路
        if (visit.contains(node)) {
            return false;
        }

        // 標記當前節點已訪問
        visit.add(node);

        // 遍歷所有鄰居
        for (int nei : adj.get(node)) {
            // 忽略回走到父節點的那條邊
            if (nei == parent) {
                continue;
            }
            // 遞迴訪問；若子呼叫發現迴路，立即回傳 false
            if (!dfs(nei, node, visit, adj)) {
                return false;
            }
        }
        return true;  // 當前子樹無迴路
    }
}