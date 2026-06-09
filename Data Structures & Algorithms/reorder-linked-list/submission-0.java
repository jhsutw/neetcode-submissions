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
        if (head == null){
            return;
        }

        // 步驟一：將所有節點存入 List 中
        // 為了能夠快速地從頭尾交替存取節點，這在原本的單向鏈結串列中是做不到的(只能一格一格走，不適合交錯)。

        List<ListNode> nodes = new ArrayList<>();
        ListNode cur = head;
        while (cur != null){
            nodes.add(cur);
            cur = cur.next;
        }

        int i = 0, j = nodes.size() - 1;
        while (i < j){
            nodes.get(i).next = nodes.get(j);
            i++;
            if (i >= j){
                break;
            }
            nodes.get(j).next = nodes.get(i);
            j--;
        }
        nodes.get(i).next = null;
    }
}
