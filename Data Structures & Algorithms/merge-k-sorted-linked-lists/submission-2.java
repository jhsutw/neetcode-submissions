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
    // 主函式：合併 K 條排序好的 Linked List
    public ListNode mergeKLists(ListNode[] lists) {
        // 若沒有任何 list，直接回傳 null
        if (lists.length == 0) return null;

        // 從第 1 條 list 開始，依序與前一條合併
        for (int i = 1; i < lists.length; i++) {
            lists[i] = merge(lists[i], lists[i - 1]);
        }

        // 最後回傳最後一條 list（已包含所有合併結果）
        return lists[lists.length - 1];
    }

    // 輔助函式：合併兩條排序好的 Linked List
    private ListNode merge(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode(0); // dummy 節點方便處理邊界
        ListNode curr = dummy;            // 指向當前操作節點

        // 當兩條 list 都還有節點時，依據值的大小依序接上
        while (l1 != null && l2 != null) {
            if (l1.val <= l2.val) {
                curr.next = l1;           // l1 較小就接上它
                l1 = l1.next;             // l1 往後移
            } else {
                curr.next = l2;           // l2 較小就接上它
                l2 = l2.next;             // l2 往後移
            }
            curr = curr.next;             // 指標往後移
        }

        // 把剩下未走完的 list 接到尾端（只會有一條有剩）
        if (l1 != null) {
            curr.next = l1;
        } else {
            curr.next = l2;
        }

        // 回傳真正的合併頭節點（略過 dummy）
        return dummy.next;
    }
}