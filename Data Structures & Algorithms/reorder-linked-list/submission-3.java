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
        // 🐢🐇 使用快慢指針找中間節點，slow 最後會停在中點
        ListNode slow = head;
        ListNode fast = head.next;
        while (fast != null && fast.next != null){
            slow = slow.next;
            fast = fast.next.next;
        }

         // ✂️ 從中點斷開，把後半段拉出來反轉
        ListNode second = slow.next; // 第二段起點
        ListNode prev = slow.next = null; // 斷開前半段與後半段 (ListNode prev = null; slow.next = null;)
        while (second != null){
            ListNode tmp = second.next;
            second.next = prev;
            prev = second;
            second = tmp;
        }


        // 🔗 合併兩段：第一段是 head 開頭、第二段是剛剛反轉過的 prev
        ListNode first = head;
        second = prev;
        while (second != null){
            ListNode tmp1 = first.next;
            ListNode tmp2 = second.next;
            first.next = second; // 接上後半段節點
            second.next = tmp1; // 接回第一段的下一個節點
            first = tmp1;
            second = tmp2;
        }
    }
}
/*
📘 範例走法（輸入：1→2→3→4→5）：
Step 1: 找中點
    slow → 3（中點）
    
Step 2: 分為兩段：
    前半段: 1 → 2 → 3
    後半段: 4 → 5 → null

Step 3: 反轉後半段：
    5 → 4 → null

Step 4: 合併
    1 → 5 → 2 → 4 → 3
*/