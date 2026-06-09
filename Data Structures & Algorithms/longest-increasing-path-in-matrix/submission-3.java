// 3. Topological Sort (Kahn's Algorithm)
// 先找到局部最小值的位置，然後再利用queue往四周擴散（找比自己大的），看最多能夠往外擴散幾層
public class Solution {
    public int longestIncreasingPath(int[][] matrix) {
        int ROWS = matrix.length, COLS = matrix[0].length;
        // indegree[r][c]：把每個格子視為圖的節點，邊從「較小值」指向「較大值」
        // 這裡記錄 (r,c) 的入度（有多少較小的鄰居指向它）
        int[][] indegree = new int[ROWS][COLS];
        // 四方向：上、下、左、右
        int[][] directions = {{-1, 0}, {1, 0}, {0, -1}, {0, 1}};

        // 建立入度：若鄰居值 < 當前值，表示有一條「鄰居 -> 當前」的邊
        for (int r = 0; r < ROWS; ++r) {
            for (int c = 0; c < COLS; ++c) {
                for (int[] d : directions) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < ROWS && nc >= 0 &&
                        nc < COLS && matrix[nr][nc] < matrix[r][c]) {
                        indegree[r][c]++;
                    }
                }
            }
        }

        // 拓撲排序的起點：入度為 0 的節點（局部最小值）
        Queue<int[]> q = new LinkedList<>();
        for (int r = 0; r < ROWS; ++r) {
            for (int c = 0; c < COLS; ++c) {
                if (indegree[r][c] == 0) {
                    q.offer(new int[]{r, c});
                }
            }
        }

        int LIS = 0; // 最長遞增路徑長度（按層數累加）
        // Kahn 拓撲排序的分層 BFS：
        // 每一層代表路徑延伸一格，因此層數即為最長遞增路徑長度
        while (!q.isEmpty()) {
            int size = q.size();
            for (int i = 0; i < size; ++i) {
                int[] node = q.poll();
                int r = node[0], c = node[1];
                // 往「更大值」的鄰居擴展，等價於沿著有向邊前進
                for (int[] d : directions) {
                    int nr = r + d[0], nc = c + d[1];
                    if (nr >= 0 && nr < ROWS && nc >= 0 &&
                        nc < COLS && matrix[nr][nc] > matrix[r][c]) {
                        // 消去一條入邊；若入度為 0，加入下一層
                        if (--indegree[nr][nc] == 0) {
                            q.offer(new int[]{nr, nc});
                        }
                    }
                }
            }
            LIS++; // 走完一層，路径長度 +1
        }
        return LIS;
    }
}