// 1. Cycle Detection (DFS)
public class Solution {
    public int[] findOrder(int numCourses, int[][] prerequisites) {
        // 建立「課程 → 其先修課列表」的鄰接表
        Map<Integer, List<Integer>> prereq = new HashMap<>();
        for (int[] pair : prerequisites) {
            // pair[0] 依賴於 pair[1]
            prereq
                .computeIfAbsent(pair[0], k -> new ArrayList<>())
                .add(pair[1]);
        }

        // 用來存最終的拓撲排序結果
        List<Integer> output = new ArrayList<>();
        // visit：已完成處理的課程（不會重複訪問），
        Set<Integer> visit = new HashSet<>();
        // cycle：當前遞歸棧上的節點，用於檢測環路
        Set<Integer> cycle = new HashSet<>();

        // 對每個課程都執行 DFS，檢查是否有環，並產生拓撲序
        for (int course = 0; course < numCourses; course++) {
            if (!dfs(course, prereq, visit, cycle, output)) {
                // 若有環則無法修完所有課，回傳空陣列
                return new int[0];
            }
        }

        // 將 List<Integer> 轉成 int[]
        int[] result = new int[numCourses];
        for (int i = 0; i < numCourses; i++) {
            result[i] = output.get(i);
        }
        return result;
    }

    private boolean dfs(
        int course,
        Map<Integer, List<Integer>> prereq,
        Set<Integer> visit,
        Set<Integer> cycle,
        List<Integer> output
    ) {
        // 如果當前節點已在 cycle 中，代表遇到環路
        if (cycle.contains(course)) {
            return false;
        }
        // 如果已在 visit 中，代表已處理完成，直接跳過
        if (visit.contains(course)) {
            return true;
        }

        // 將節點標記進入遞歸棧
        cycle.add(course);
        // 遞歸訪問其所有先修課，檢查有沒有環
        for (int pre : prereq.getOrDefault(course, Collections.emptyList())) {
            if (!dfs(pre, prereq, visit, cycle, output)) {
                return false;
            }
        }
        // 處理完後，移出遞歸棧並標記為已訪問
        cycle.remove(course);
        visit.add(course);
        // 後序加入 output，確保拓撲排序正確
        output.add(course);
        return true;
    }
}