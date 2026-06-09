// 3. Topological Sort (DFS)
public class Solution {
    // 順序結果容器（最終的拓撲排序）
    private List<Integer> output = new ArrayList<>();
    // 每門課的入度（尚未滿足的先修課數量）
    private int[] indegree;
    // 鄰接表：從一門課指向它可以解鎖的後續課程列表
    private List<List<Integer>> adj;

    // 深度優先搜尋：從“入度為零”的節點開始遞迴訪問
    private void dfs(int node) {
        // 一旦進入此節點，就把它放進結果
        output.add(node);
        // 標記此課已“處理”，相當於把它從圖中移除
        indegree[node]--;
        // 扣減所有它能解鎖的後續課程的入度
        for (int nei : adj.get(node)) {
            indegree[nei]--;
            // 當某後續課程的入度降為零，即可繼續遞迴
            if (indegree[nei] == 0) {
                dfs(nei);
            }
        }
    }

    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1. 初始化鄰接表與入度陣列
        adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());
        }
        indegree = new int[numCourses];

        // 2. 建立圖結構：
        //    prerequisites[i] = [a, b] 表示 b → a（b 是 a 的先修課）
        for (int[] pre : prerequisites) {
            indegree[pre[0]]++;            // a 的入度 +1
            adj.get(pre[1]).add(pre[0]);   // 在 b 的鄰接列表加入 a
        }

        // 3. 對每個入度為 0 的課程啟動 DFS
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                dfs(i);
            }
        }

        // 4. 如果最終結果數量不等於課程總數，表示有環，回傳空陣列
        if (output.size() != numCourses) {
            return new int[0];
        }

        // 5. 把 List<Integer> 轉成 int[]
        int[] res = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            res[i] = output.get(i);
        }
        return res;
    }
}