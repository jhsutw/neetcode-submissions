/**
 * Definition for singly-linked list.
 * public class ListNode {
 *     int val;
 *     ListNode next;
 *     ListNode() {}
 *     ListNode(int val) { this.val = val; }
 *     ListNode(int val, ListNode next) { this.val = val; this.next = next; }
 * }
 */

class Solution {
    public void reorderList(ListNode head) {
        // 從 head 和 head.next 開始進入遞迴重排過程
        head = rec(head, head.next);
    }

    public ListNode rec(ListNode root, ListNode cur){
         // 遞迴到底部，返回從頭開始的 root 指針
        if (cur == null){
            return root;
        }

        // 繼續往下一層遞迴
        root = rec(root, cur.next); // 深入遞迴，把下一個節點配對完後回傳下一個尚未配對的節點

        // 若 root 為 null，表示已經完成重排，停止往回處理
        // 已經走到尾巴或中間
        if (root == null){
            return null;
        }

        ListNode tmp = null;

        // 判斷是否到了串列中央（奇數或偶數長度）
        if (root == cur || root.next == cur){
            cur.next = null; // 截斷串列，防止循環
        } else {
            // 插入 cur 到 root 後面，並將原本的 root.next 接到 cur 後面
            tmp = root.next;
            root.next = cur;
            cur.next = tmp;
        }
        return tmp;
    }
}
/*
1 → 2 → 3 → 4 → 5

rec(1, 2)
  rec(1, 3)
    rec(1, 4)
      rec(1, 5)
        rec(1, null) → return root = 1

接著遞迴往回傳時配對節點：

1️⃣ rec(1, 5)：
tmp = root.next = 2
root.next = 5
5.next = tmp (2)
→ 結果：1 → 5 → 2
return tmp = 2 (下一層的呼叫會變成rec(2, 4))

2️⃣ rec(2, 4)：
root = 2
cur = 4
tmp = root.next = 3
2.next = 4
4.next = 3
→ 結果：1 → 5 → 2 → 4 → 3
return tmp = 3

result:
1 → 5
5 → 2
2 → 4
4 → 3
3 → null
*/
