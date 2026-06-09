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
        // 建立一個陣列來儲存所有節點的參考（方便之後用 index 存取）
        List<ListNode> nodes = new ArrayList<>();
        ListNode cur = head;
        while (cur != null){
            nodes.add(cur);
            cur = cur.next;
        }

        // 計算從頭數過來要移除的 index
        int removeIndex = nodes.size() - n;

         // 若要刪除的是第一個節點，直接回傳第二個節點
        if (removeIndex == 0){
            return head.next;
        }

        // 否則讓前一個節點跳過被移除的節點 (跳過 nodes.get(removeIndex))
        nodes.get(removeIndex - 1).next = nodes.get(removeIndex).next;
        return head;
    }
}
