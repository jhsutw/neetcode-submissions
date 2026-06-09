public class Solution {
    // 對每門課 (key) 建立其先修課程列表 (value)
    private Map<Integer, List<Integer>> preMap = new HashMap<>();
    // 記錄當前 DFS 路徑上正在拜訪的課程，用於檢測環
    private Set<Integer> visiting = new HashSet<>();

    /**
     * 判斷是否能完成所有課程（檢測有向圖是否有環）
     * @param numCourses    課程總數，編號從 0 到 numCourses-1
     * @param prerequisites  先修關係陣列，prerequisites[i] = [a, b] 表示上 a 要先修 b
     * @return 若無環，返回 true；否則 false
     */
    public boolean canFinish(int numCourses, int[][] prerequisites) {
        // 1. 初始化：每門課都先對應到空的先修列表（課程是0~n-1）
        for (int i = 0; i < numCourses; i++) {
            preMap.put(i, new ArrayList<>());
        }
        // 2. 建立鄰接表：將 prerequisites 裡的 [a,b] 加入 preMap[a]
        for (int[] prereq : prerequisites) {
            int course = prereq[0], prereqCourse = prereq[1];
            preMap.get(course).add(prereqCourse);
        }

        // 3. 對每門課做 DFS，檢測是否有環（從0檢查到n-1）
        for (int c = 0; c < numCourses; c++) {
            if (!dfs(c)) {
                // 一旦偵測到回傳 false（有環）
                return false;
            }
        }
        // 全部 DFS 都通過，無環
        return true;
    }

    /**
     * DFS 檢測從課程 crs 出發，能否走完其所有先修課而不環返回自己
     * @param crs 當前課程編號
     * @return 若此課程及其先修子圖無環，回傳 true；否則 false
     */
    private boolean dfs(int crs) {
        // 1. 若當前節點已在「拜訪中集合」中，表示遇到環
        if (visiting.contains(crs)) {
            return false;
        }
        // 2. 若此課程無先修課（列表為空），可直接完成
        if (preMap.get(crs).isEmpty()) {
            return true;
        }

        // 3. 開始拜訪此節點，加入 visiting 標記
        visiting.add(crs);
        // 4. 遞迴檢測所有先修課
        for (int pre : preMap.get(crs)) {
            if (!dfs(pre)) { // 除了目前節點的prereq以外，也遞迴prereq的prereq是否存在環
                return false;  // 子路徑有環，立即回傳 false
            }
        }
        // 5. 拜訪結束，（當前拜訪節點）從 visiting 中移除
        visiting.remove(crs);

        // 6. 將此課程先修列表清空作為「備忘錄」(memo)，避免重複計算
        preMap.put(crs, new ArrayList<>());
        return true;
    }
}

// 範例 1：可完成所有課程
// numCourses = 2
// prerequisites = [[1, 0]]  // 課程 1 需先修課程 0

// 建立 preMap：
// 0 → []
// 1 → [0]

// 走訪 course 0：
//   preMap.get(0).isEmpty() → true → dfs(0) 回傳 true

// 走訪 course 1：
//   visiting 加入 1
//   先修列表 preMap.get(1) = [0]
//     呼叫 dfs(0) → 直接回傳 true
//   visiting 移除 1
//   清空 preMap.get(1)
//   dfs(1) 回傳 true

// canFinish 回傳 true  (無環路，皆可完成)


// 範例 2：無法完成所有課程（有環）
// numCourses = 2
// prerequisites = [[1, 0], [0, 1]]  
//   課程 1 需先修 0；課程 0 需先修 1 → 形成環

// 建立 preMap：
// 0 → [1]
// 1 → [0]

// 走訪 course 0：
//   visiting 加入 0
//   先修列表 preMap.get(0) = [1]
//     呼叫 dfs(1):
//       visiting 加入 1
//       先修列表 preMap.get(1) = [0]
//         呼叫 dfs(0):
//           visiting.contains(0) → true → 偵測到環，立即回傳 false
//       dfs(1) 回傳 false
//   dfs(0) 回傳 false

// canFinish 回傳 false  (有環路，無法完成)