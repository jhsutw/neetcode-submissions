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


// 2. Iteration (Two Pass)
class Solution {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        // 第一次遍歷：計算整個鏈結串列的長度 N
        int N = 0;
        ListNode cur = head;
        while (cur != null){
            N++;
            cur = cur.next;
        }

        // 要移除的節點 index（從 0 開始算）
        int removeIndex = N - n;

        // 特殊情況：要移除的是第一個節點，則直接return第二個node
        if (removeIndex == 0){
            return head.next;
        }

        // 第二次遍歷：走到要刪除節點的前一個位置
        cur = head;
        for (int i = 0; i < N - 1; i++){
            // 若下一個節點就是要移除的節點
            if ((i + 1) == removeIndex){
                // 將當前節點的 next 指向要移除節點的下一個節點
                cur.next = cur.next.next;
                break;
            }
            cur = cur.next;
        }
        return head;
    }
}
