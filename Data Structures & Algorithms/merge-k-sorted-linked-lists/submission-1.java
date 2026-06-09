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

public class Solution {    
    public ListNode mergeKLists(ListNode[] lists) {
        // 建立一個 dummy 頭節點，方便操作
        ListNode res = new ListNode(0);
        ListNode cur = res; // cur 是目前串接的位置

        while (true) {
            int minNode = -1; // 紀錄目前最小節點的 index
            for (int i = 0; i < lists.length; i++) {
                // 如果 lists[i] 為 null，表示該 list 已合併完
                if (lists[i] == null) continue;

                // 找出目前節點值最小的那一條 list
                if (minNode == -1 || lists[minNode].val > lists[i].val) {
                    minNode = i;
                }
            }

            // 如果 minNode 還是 -1，表示所有 lists 都為 null，合併完成
            if (minNode == -1) {
                break;
            }

            // 把最小值節點接到結果串列中
            cur.next = lists[minNode];
            // 串接後，那條 list 向後移一個節點（等於刪掉已經放入cur的節點）
            lists[minNode] = lists[minNode].next;
            // cur 移動到剛剛接上的節點
            cur = cur.next;
        }

        // 返回合併後串列的頭節點（略過 dummy 頭）
        return res.next;
    }
}