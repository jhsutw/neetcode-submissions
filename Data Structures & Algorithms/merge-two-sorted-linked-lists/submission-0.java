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
        // 如果 list1 為空，直接回傳 list2（合併結束）
        if (list1 == null){
            return list2;
        }
        // 如果 list2 為空，直接回傳 list1（合併結束）
        if (list2 == null){
            return list1;
        }
        // 若 list1 當前節點的值較小（等於也包含），將其設為當前合併結果的節點
        if (list1.val <= list2.val){
            // 接著遞迴合併 list1.next 和 list2，接在 list1 後面
            list1.next = mergeTwoLists(list1.next, list2);
            return list1;
        } else {
            // 否則 list2 的值較小，設為當前合併結果節點
            list2.next = mergeTwoLists(list1, list2.next);
            return list2;
        }
    }
}
/*
list1: 1 → 3 → 5 → null  
list2: 2 → 4 → 6 → null

| 比較            | 下一步               | 合併中間結果            |
| ------------- | -------------------  | --------------------- |
| 1 vs 2        | 1 接上 merge(3, 2)    | 1 → ?                 |
| 3 vs 2        | 2 接上 merge(3, 4)    | 1 → 2 → ?             |
| 3 vs 4        | 3 接上 merge(5, 4)    | 1 → 2 → 3 → ?         |
| 5 vs 4        | 4 接上 merge(5, 6)    | 1 → 2 → 3 → 4 → ?     |
| 5 vs 6        | 5 接上 merge(null, 6) | 1 → 2 → 3 → 4 → 5 → ? |
| list1 == null | return list2         | 1 → 2 → 3 → 4 → 5 → 6 |
*/