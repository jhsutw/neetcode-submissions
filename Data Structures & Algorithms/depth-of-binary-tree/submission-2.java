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

/*
import javafx.util.Pair; // 注意：要使用 javafx.util.Pair，或自訂 Pair class

class Pair<K, V> {
    public K key;
    public V value;
    public Pair(K key, V value) {
        this.key = key;
        this.value = value;
    }
    public K getKey() { return key; }
    public V getValue() { return value; }
}

為什麼不用HashMap?
| 場景                             | 建議用法            |
| --------------------------------| -------------------|
| 遍歷時每個節點要記錄附加資訊（如深度）| ✅ 用 Pair 更合適    |
| 需要隨時根據 key 查找 value       | ✅ 用 HashMap 更合適 |
| 有 key 重複、需要紀錄多組資料       | ❌ 不建議用 HashMap  |

*/

public class Solution {
    public int maxDepth(TreeNode root) {
        // 使用 stack 模擬遞迴，儲存 (節點, 當前深度)
        Stack<Pair<TreeNode, Integer>> stack = new Stack<>();
        stack.push(new Pair<>(root, 1)); // 將根節點與初始深度 1 放入堆疊

        int res = 0; // 紀錄最大深度

        while (!stack.isEmpty()) {
            Pair<TreeNode, Integer> current = stack.pop(); // 取出一個節點與其深度
            TreeNode node = current.getKey();
            int depth = current.getValue();

            if (node != null) {
                // 更新目前為止的最大深度
                res = Math.max(res, depth);

                // 將左右子節點與對應深度放入堆疊
                stack.push(new Pair<>(node.left, depth + 1));
                stack.push(new Pair<>(node.right, depth + 1));
            }
        }

        // 回傳最大深度
        return res;
    }
}

/*
範例輸入：root = [1, 2, 3, 4, null, null, 5]

        1
       / \
      2   3
     /     \
    4       5

執行過程（非遞迴 DFS）：
處理順序為13524，因為stack 是後進先出（LIFO）！

Step 1: stack = [(1, 1)]
- pop (1,1) → res = 1
- push (2,2), (3,2)

Step 2: pop (3,2) → res = 2
- push (null,3), (5,3)

Step 3: pop (5,3) → res = 3
- push (null,4), (null,4)

Step 4~5: pop null → 無動作

Step 6: pop (2,2) → res 保持 3
- push (4,3), (null,3)

Step 7: pop (4,3) → res 保持 3
- push (null,4), (null,4)

後續全為 null，不處理

✅ 最終最大深度：3

補充說明：
- 此方法是 DFS 的非遞迴寫法
- 使用 Stack 模擬遞迴過程，並手動追蹤每個節點對應的深度
- 每次遇到非空節點時更新最大深度，直到遍歷整棵樹
- 適用於不方便用遞迴的情況（例如樹過深）

注意事項：
- 需匯入 javafx.util.Pair（或自定義 Pair class）
*/