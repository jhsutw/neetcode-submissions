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

// Fast And Slow Pointers
// 若有環：快慢指針終將在環中相遇（數學保證，類似跑道追逐問題）。
class Solution {
    public boolean hasCycle(ListNode head) {
        // 快指針與慢指針都從頭開始
        ListNode fast = head;
        ListNode slow = head;

        // 當 fast 和 fast.next 都不是 null，才能繼續前進
        while (fast != null && fast.next != null){
            // 快指針一次跳兩格
            fast = fast.next.next;
            // 慢指針一次跳一格
            slow = slow.next;
            // 如果兩個指針相遇了，代表有環
            if (fast == slow) return true;
        } // 如果 fast 先走到底，代表沒有環
        return false;
    }
}
