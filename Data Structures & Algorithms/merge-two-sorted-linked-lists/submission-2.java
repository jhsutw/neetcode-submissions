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
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        // 建立虛擬頭節點 dummy（為了方便 return 和操作）
        ListNode dummy = new ListNode(0);
         // node 用來追蹤目前合併鏈結串列的最後一個節點
        ListNode node = dummy;

        // 當兩個串列都還沒走完時，持續比大小並接上較小者
        while (list1 != null && list2 != null){
            if (list1.val < list2.val){
                node.next = list1; // 接上 list1 節點
                list1 = list1.next; // list1 往下走
            } else {
                node.next = list2; // 接上 list2 節點
                list2 = list2.next; // list2 往下走
            }
            node = node.next; // 移動合併指標
        }

        // 其中一條鏈結還沒走完，直接接在尾巴（兩者只有一個不為 null）
        if (list1 != null){
            node.next = list1;
        } else{
            node.next = list2;
        }
         // dummy.next 才是真正合併好的頭節點 (dummy是空節點)
        return dummy.next;
    }
}