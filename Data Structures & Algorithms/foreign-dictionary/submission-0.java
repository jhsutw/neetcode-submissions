// 1. Depth First Search：使用圖建構 + 拓撲排序（DFS 版本）

public class Solution {
    private Map<Character, Set<Character>> adj; // 鄰接表：記錄每個字母後面可以接哪些字母
    private Map<Character, Boolean> visited;    // 拓撲排序用來記錄 DFS 狀態（true: 目前訪問中, false: 訪問完畢）
    private List<Character> result;             // 儲存拓撲排序結果（反向）

    public String foreignDictionary(String[] words) {
        adj = new HashMap<>();

        // 初始化圖中所有出現過的字母（建立一個以出現過的單字字母為key的hashmap）
        for (String word : words) {
            for (char c : word.toCharArray()) {
                adj.putIfAbsent(c, new HashSet<>());
            }
        }

        // 建立字母之間的依賴關係圖（拓撲圖）
        for (int i = 0; i < words.length - 1; i++) {
            String w1 = words[i], w2 = words[i + 1]; // 跟下一個單字比較
            int minLen = Math.min(w1.length(), w2.length()); // minLen為兩者較短長度

            // 特例處理：若 w1 是 w2 的前綴，但更長，例如 ["abc", "ab"] → 無效
            if (w1.length() > w2.length() &&
                w1.substring(0, minLen).equals(w2.substring(0, minLen))) {
                return ""; // 無有效字母順序
            }

            // 找到第一個不同的字母，建立邊 w1[j] → w2[j]
            for (int j = 0; j < minLen; j++) {
                if (w1.charAt(j) != w2.charAt(j)) {
                    adj.get(w1.charAt(j)).add(w2.charAt(j));
                    break; // 只需要第一對不一樣的字母
                }
            }
        }

        visited = new HashMap<>();
        result = new ArrayList<>();

        // 執行 DFS 拓撲排序
        for (char c : adj.keySet()) {
            if (dfs(c)) {
                return ""; // 有 cycle（環） → 無效的字母順序
            }
        }

        // 拓撲排序是逆序加的，所以要反轉回來
        Collections.reverse(result);
        StringBuilder sb = new StringBuilder();
        for (char c : result) {
            sb.append(c);
        }
        return sb.toString();
    }

    // 拓撲排序用 DFS，並偵測環（cycle）
    private boolean dfs(char ch) {
        if (visited.containsKey(ch)) {
            return visited.get(ch); // 若是 true → 表示遇到訪問中的節點 → 有環
        }

        visited.put(ch, true); // 標記為訪問中

        // 遍歷所有鄰接節點
        for (char next : adj.get(ch)) {
            if (dfs(next)) {
                return true; // 有環
            }
        }

        visited.put(ch, false); // 訪問完畢，設為 false
        result.add(ch); // 加入排序結果（後序位置）
        return false;
    }
}