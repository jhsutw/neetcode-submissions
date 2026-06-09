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
    public boolean hasCycle(ListNode head) {
        // 建立一個 HashSet 用來記錄已拜訪過的節點
        HashSet<ListNode> seen = new HashSet<>();
        // 使用 cur 指標遍歷鏈結串列
        ListNode cur = head;


        while (cur != null){
            // 如果這個節點已經看過，代表有環
            if (seen.contains(cur)){
                return true;
            }

            // 否則就記錄下來這個節點，繼續往下走
            seen.add(cur);
            cur = cur.next;
        }
        // 如果整串走完都沒重複出現的節點，代表無環
        return false;
    }
}
