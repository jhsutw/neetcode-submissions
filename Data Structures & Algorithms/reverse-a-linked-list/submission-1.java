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
    public ListNode reverseList(ListNode head) {
        // prev 是反轉後的前一個節點，初始為 null
        ListNode prev = null;
        // curr 是目前遍歷到的節點，從 head 開始
        ListNode curr = head;

        // 當 curr 尚未走到底時，持續反轉
        while (curr != null){
            // 暫存下一個節點，避免反轉後指標遺失
            ListNode temp = curr.next;
            // 將當前節點指向前一個節點，反轉指標方向
            curr.next = prev;
            // 更新 prev 與 curr，繼續往後走
            prev = curr;
            curr = temp;
        }
        // prev 最終會是新的 head（因為 curr 最後變成 null）
        return prev;
    }
}
