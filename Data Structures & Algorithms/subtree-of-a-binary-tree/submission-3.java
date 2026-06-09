/**
 * Definition for a binary tree node.
 * public class TreeNode {
 *     int val;
 *     TreeNode left;
 *     TreeNode right;
 *     TreeNode() {}
 *     TreeNode(int val) { this.val = val; }
 *     TreeNode(int val, TreeNode left, TreeNode right) {
 *         this.val = val;
 *         this.left = left;
 *         this.right = right;
 *     }
 * }
 */

public class Solution {

    // 將二元樹序列化為字串（使用前序遍歷 + null 標記）
    public String serialize(TreeNode root) {
        if (root == null) {
            return "$#";  // 使用 "$" 標記 null，"#" 作為分隔符
        }
        // 前序遍歷：當前節點 + 左子樹 + 右子樹
        return "$" + root.val + serialize(root.left) + serialize(root.right);
    }
    // 計算 Z-Function：用於快速找子字串匹配（類似 KMP，但更直觀）
    // z[i] 表示 s[0:] 與 s[i:] 最長的前綴匹配長度
    public int[] z_function(String s) {
        int[] z = new int[s.length()];
        int l = 0, r = 0, n = s.length();

        for (int i = 1; i < n; i++) {

            // 如果 i 落在目前 Z 區間 [l, r] 內，
            // 我們可以參考對稱位置 z[i - l] 的值來快速推測 z[i]
            if (i <= r) {
                z[i] = Math.min(r - i + 1, z[i - l]); // 快速遞推已有區間（不能超過 r）
            }

            // 嘗試從目前 z[i] 開始往右延伸匹配
            while (i + z[i] < n && s.charAt(z[i]) == s.charAt(i + z[i])) {
                z[i]++;
            }

            // 如果新的匹配區間超出 r，就更新 [l, r]
            if (i + z[i] - 1 > r) {
                l = i;
                r = i + z[i] - 1;
            }
        }

        return z;
    }

    // 主函式：判斷 subRoot 是否為 root 的子樹
    public boolean isSubtree(TreeNode root, TreeNode subRoot) {
        // 將兩棵樹序列化為字串
        String serialized_root = serialize(root);
        String serialized_subRoot = serialize(subRoot);

        // 合併字串，並用 "|" 作為分隔符避免誤匹配
        String combined = serialized_subRoot + "|" + serialized_root;

        // 計算 Z 函數
        int[] z_values = z_function(combined);
        int sub_len = serialized_subRoot.length();

        // 從分隔符之後開始比對，看是否存在與 subRoot 完全匹配的區段
        for (int i = sub_len + 1; i < combined.length(); i++) {
            if (z_values[i] == sub_len) {
                return true; // 找到匹配子字串 → subRoot 是 root 的子樹
            }
        }

        return false; // 沒找到 → 不是子樹
    }
}

/**
 * 範例：
 *
 * root:
 *       3
 *      / \
 *     4   5
 *    / \
 *   1   2
 *
 * subRoot:
 *     4
 *    / \
 *   1   2
 *
 * === 執行流程 ===
 *
 * Step 1: 將兩棵樹序列化為字串（包含 null 節點，用 $# 表示）
 * serialize(root)     → "$3$4$1$#$#$2$#$#$5$#$#"
 * serialize(subRoot)  → "$4$1$#$#$2$#$#"
 *
 * Step 2: 使用 Z-Function 做子字串匹配
 * 建立字串 combined = serialized_subRoot + "|" + serialized_root
 * → "$4$1$#$#$2$#$#|$3$4$1$#$#$2$#$#$5$#$#"
 *
 * Step 3: 對 combined 執行 Z-Function
 * Z-Function 的作用是在每個位置 i 計算 s[i:] 與 s[0:] 的最長前綴長度
 
 i.e. 
 String s = "ababcabab";
 | `i` | `s[i:]`   | `z[i]` | 相同前綴 substring |
| --- | --------- | ------ | -------------- |
| 0   | ababcabab | (無)    | -              |
| 1   | babcabab  | 0      | ""             |
| 2   | abcabab   | 2      | "ab"           |
| 3   | bcabab    | 0      | ""             |
| 4   | cabab     | 0      | ""             |
| 5   | abab      | 4      | "abab"         |
| 6   | bab       | 0      | ""             |
| 7   | ab        | 2      | "ab"           |
| 8   | b         | 0      | ""             |

 * Step 4: 掃描所有位置 i（i > subRoot長度）看看是否有 z[i] == subRoot長度
 * → 有的話，表示 subRoot 完整地出現在 root 的序列化中，即為其子樹
 *
 * === 補充說明 ===
 * - 使用 "$" 作為節點標記符號是為了避免出現匹配錯誤（例如節點值12 和 2 重疊）
 * - 使用 "#" 表示 null，確保結構也一致
 * - "|" 是區隔符，避免 Z-Function 把前半段也拿去比對
 *
 * === 範例結果 ===
 * → return true，因為 subRoot 確實是 root 的子樹
 */