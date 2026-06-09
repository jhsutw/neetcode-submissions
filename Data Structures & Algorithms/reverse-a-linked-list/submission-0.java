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
        // 若head是空的，代表是空串列，return null
        if (head == null){
            return null;
        }

         // 初始化 newHead（最終要回傳的反轉後的新頭節點）
         // head為該遞迴局新加入的節點
        ListNode newHead = head;

         // 如果 head 後面還有節點，繼續遞迴反轉
        if (head.next != null){
            // 遞迴到最後一個節點，並將其設定為 newHead
            newHead = reverseList(head.next);
            // 將下一個節點的 next 指向自己，實現反轉
            head.next.next = head;
        }
        // 將原本的 next 斷掉，避免形成環（否則會變成死循環）
        // 目前的狀況是head到hesd.next是個雙向環
        head.next = null;

        return newHead;
    }
}
/*
原始鏈結：
head → 1 → 2 → 3 → null

【遞迴到最底】
reverseList(3)
return 3

【開始反轉】
↓ 回到 reverseList(2)
     2.next.next = 2
     2.next = null

3 → 2 → null

↓ 回到 reverseList(1)
     1.next.next = 1
     1.next = null

3 → 2 → 1 → null
*/