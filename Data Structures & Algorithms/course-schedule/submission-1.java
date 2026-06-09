public class Solution {
    /**
     * 判斷能否完成所有課程（Kahn 拓樸排序 / BFS 版本）
     * @param numCourses    課程總數，編號 0…numCourses-1
     * @param prerequisites  先修關係列表，每個元素 [a, b] 表示：要修 b 之前需先修 a
     * @return 若能排出一個合法順序（無環），回傳 true；否則 false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 初始化：計算每門課的「入度」(in-degree)
        //    indegree[i] = 需要先修幾門課程才能修 i
        int[] indegree = new int[numCourses];

        // 2. 建立鄰接表 (adjacency list)
        //    adj.get(u) = 存放修完 u 後，可以去修哪些課程 v
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>()); // 每堂課都有一個arraylist存上完後可以修的課
        }

        // 3. 填入 indegree 與鄰接表
        for (int[] pre : prerequisites) {
            int u = pre[0], v = pre[1];
            // 假設 [u,v] 表示「要修 v 之前需先修 u」
            indegree[v]++;           // v 的入度 +1 (先修課數量++)
            adj.get(u).add(v);       // u → v 邊（修完u可以修v）
        }

        // 4. 初始化隊列：將所有入度為 0（先修課數為0）的課程加入
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.offer(i);
            }
        }

        // 5. BFS 拓樸排序（從不用prereq的課開始，然後慢慢找後面可以上什麼課，直到全部上完）
        int finished = 0;  // 計算已「修完」的課程數
        while (!q.isEmpty()) {
            int course = q.poll();
            finished++;     // 當前課程可被修完

            // 對所有以 course 為先修的課程進行「解除依賴」(修完course去修其他修完course可以修的課）        
            for (int next : adj.get(course)) {
                indegree[next]--;         // next 的入度減一
                if (indegree[next] == 0) {
                    q.offer(next);        // 若入度為 0，加入隊列（去修course的下一堂課）
                }
            }
        }

        // 6. 若「修完」數量 == 課程總數，代表無環、可一次修完所有課
        return finished == numCourses;
    }
}

// 範例 1：可完成所有課程
// numCourses = 2
// prerequisites = [[1, 0]]  // 要修 0 之前沒有依賴，要修 1 之前要先修 0
//
// 1. 計算入度 indegree = [0, 1]
// 2. 建立鄰接表 adj:
//    0 → [1]
//    1 → []
// 3. 初始隊列 q = [0]  (只有課程 0 的入度為 0)
// 4. 開始 BFS 拓樸排序：
//    - poll 0 → finished = 1  
//      next = 1，indegree[1]-- → 0，加入隊列 → q = [1]
//    - poll 1 → finished = 2  
//      沒有 next  
// 5. finished == numCourses (2) → 回傳 true


// 範例 2：無法完成所有課程（形成環）
// numCourses = 2
// prerequisites = [[1, 0], [0, 1]]  
//   // 課程 1 需先修 0，課程 0 又需先修 1 → 互相依賴
//
// 1. 計算入度 indegree = [1, 1]
// 2. 建立鄰接表 adj:
//    0 → [1]
//    1 → [0]
// 3. 初始隊列 q = []  (無任何入度為 0 的課程)
// 4. BFS 階段：隊列為空，無法開始 → finished = 0  
// 5. finished (0) != numCourses (2) → 回傳 false