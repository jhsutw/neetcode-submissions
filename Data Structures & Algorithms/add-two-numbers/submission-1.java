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
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        // 建立虛擬頭節點 dummy，最後會回傳 dummy.next
        ListNode dummy = new ListNode();
        // cur 為目前處理的節點指標
        ListNode cur = dummy;

         // carry 為進位值，初始為 0
        int carry = 0;

        // 當 l1、l2 尚有節點 或 carry 尚未為 0 時，繼續加總
        while (l1 != null || l2 != null || carry != 0){
            // 若 l1 尚有節點，取其值，否則設為 0
            int v1 = (l1 != null) ? l1.val : 0;
            // 若 l2 尚有節點，取其值，否則設為 0
            int v2 = (l2 != null) ? l2.val : 0;

            // 加總 l1, l2 與進位值
            int val = v1 + v2 + carry;
            // 更新進位值
            carry = val / 10;
            // 取得當前位數的值（個位數）
            val = val % 10;
            // 建立新節點並接到當前節點之後 (一開始的cur是dummy)
            cur.next = new ListNode(val);

            // 移動 cur 指標到下一節點
            cur = cur.next;
            // 若 l1 有下一節點則前進
            l1 = (l1 != null) ? l1.next : null;
            // 若 l2 有下一節點則前進
            l2 = (l2 != null) ? l2.next : null;
        }

        // 回傳 dummy 的下一個節點，即為加總後的結果
        return dummy.next;
    }
}
