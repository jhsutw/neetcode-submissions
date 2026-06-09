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
    public ListNode removeNthFromEnd(ListNode head, int n) {
        // 新增 dummy 節點，指向 head，避免刪除頭節點時處理特殊情況
        ListNode dummy = new ListNode(0, head);
        
        // 初始化兩個指標，left 指在 dummy，right 指在 head
        ListNode left = dummy;
        ListNode right = head;

         // right 先走 n 步，形成 right 與 left 相隔 n 節點的間距
        while (n > 0){
            right = right.next;
            n--;
        }

        // right 與 left 一起走，直到 right 到達 null (走到底)
        // 此時 left 正好停在要刪除節點的前一個節點
        while (right != null){
            left = left.next;
            right = right.next;
        }

         // 刪除倒數第 n 個節點（跳過left.next）
        left.next = left.next.next;
        return dummy.next;
    }
}
