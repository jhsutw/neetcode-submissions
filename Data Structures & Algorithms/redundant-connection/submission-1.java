// 2. Depth First Search (Optimal)
public class Solution {
    // 訪問標記：記錄節點是否已被 DFS 過
    private boolean[] visit;
    // 鄰接表：儲存無向圖的連線關係
    private List<List<Integer>> adj;
    // cycle 集合：存放檢測到的環上所有節點
    private Set<Integer> cycle;
    // cycleStart：記錄環開始的節點，用於回溯時標記整個環
    private int cycleStart;

    public int[] findRedundantConnection(int[][] edges) {
        int n = edges.length;
        // 1. 初始化鄰接表 (節點從 1 到 n)
        adj = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            adj.add(new ArrayList<>()); 
        }
        // 2. 根據 edges 建圖
        for (int[] edge : edges) {
            int u = edge[0], v = edge[1];
            adj.get(u).add(v);
            adj.get(v).add(u);
        }

        // 3. 準備 DFS 環路檢測
        visit = new boolean[n + 1];
        cycle = new HashSet<>();
        cycleStart = -1;
        // 從節點 1 開始 DFS（題目保證圖連通）
        dfs(1, -1);

        // 4. 反向遍歷輸入邊，找到最後一條同時在 cycle 中的邊（題目：如果有很多個可能的答案，回傳最後一個）
        for (int i = edges.length - 1; i >= 0; i--) {
            int u = edges[i][0], v = edges[i][1];
            if (cycle.contains(u) && cycle.contains(v)) { // 環中只要任兩個相鄰點間的edge被拆掉，都會變回non cycle graph
                return new int[]{u, v};
            }
        }
        return new int[0];
    }

    // DFS 檢測環並收集環上節點
    private boolean dfs(int node, int par) {
        // 若再次訪問到已標記節點，發現環，記錄環的起點
        if (visit[node]) {
            cycleStart = node;
            return true;
        }
        visit[node] = true;  // 標記當前節點為已訪問
        // 遍歷所有鄰居
        for (int nei : adj.get(node)) {
            if (nei == par) {
                // 忽略走回父節點的那條邊
                continue;
            }
            // 遞迴訪問；若子樹檢測到環，開始回溯收集
            if (dfs(nei, node)) {
                // 若尚未結束收集，cycleStart != -1，將當前 node 加入環集合（發現還的起點node會被設為cycleStart）
                if (cycleStart != -1) {
                    cycle.add(node);
                }
                // 當回溯回到環的起點時，結束收集
                if (node == cycleStart) {
                    cycleStart = -1;
                }
                return true;  // 繼續一路回傳 true
            }
        }
        return false;  // 該子樹無環
    }
}