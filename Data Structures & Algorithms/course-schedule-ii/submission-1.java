public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 1. 用來記錄每門課程的「入度」（有幾個前置課程）
        int[] indegree = new int[numCourses];
        // 2. 用鄰接表儲存圖：從某門課能「指向」哪些後續課程
        List<List<Integer>> adj = new ArrayList<>();
        for (int i = 0; i < numCourses; i++) {
            adj.add(new ArrayList<>());  // 初始化每門課的鄰接清單 (記錄每堂課修完還可以再修哪些課)
        }

        // 3. 遍歷所有前置條件，填入 indegree 與 adj
        for (int[] pre : prerequisites) {
            // pre[0] → pre[1] 表示要先修 pre[0] 再修 pre[1]
            indegree[pre[1]]++;           // 課程 pre[1] 的入度 +1
            adj.get(pre[0]).add(pre[1]);  // 在 pre[0] 的鄰接表中加入 pre[1]
        }

        // 4. 建立一個佇列，先把所有「入度為 0」的課程（無須任何前置）放進去
        Queue<Integer> q = new LinkedList<>();
        for (int i = 0; i < numCourses; i++) {
            if (indegree[i] == 0) {
                q.add(i);
            }
        }

        // 5. 拓撲排序的核心：不斷從佇列取出「可修課程」
        int finish = 0;                     // 記錄已處理（排入順序）的課程數
        int[] output = new int[numCourses]; // 最終的修課順序
        while (!q.isEmpty()) {
            int node = q.poll();  
            // 為了符合 LeetCode 要求的「reverse order」，我們從尾端開始填（先修完的課放後面）
            output[numCourses - finish - 1] = node;
            finish++;

            // 處理這門課對其他課程的影響：把所有相鄰課程的入度減 1
            for (int nei : adj.get(node)) {
                indegree[nei]--;
                // 當某課的入度變成 0，就代表它所有前置都已經排好了
                if (indegree[nei] == 0) {
                    q.add(nei);
                }
            }
        }

        // 6. 如果無法排完所有課（出現環），就回傳空陣列
        if (finish != numCourses) {
            return new int[0];
        }
        // 7. 否則回傳正確的拓撲排序結果
        return output;
    }
}