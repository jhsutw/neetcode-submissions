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
    // 遞迴函式：將兩個 linked list 與進位值相加
    public ListNode add(ListNode l1, ListNode l2, int carry) {
        // 當 l1、l2 為 null 且沒有進位時，代表已完成加總
        if (l1 == null && l2 == null && carry == 0){
            return null;
        }

         // 初始化取值為 0（若 l1/l2 不為 null，再取其 val）
        int v1 = 0;
        int v2 = 0;
        if (l1 != null) {
            v1 = l1.val;
        }

        if (l2 != null) {
            v2 = l2.val;
        }

         // 計算總和與新的進位
        int sum = v1 + v2 + carry;
        int newCarry = sum / 10; // 取商（進位）
        int nodeValue = sum % 10; // 取餘數（本位數值）

        // 遞迴處理下一個節點
        ListNode nextNode = add(
            (l1 != null) ? l1.next : null,
            (l2 != null) ? l2.next : null,
            newCarry
        );

        // 建立新節點並串接
        return new ListNode(nodeValue, nextNode); // 建立一個新的 ListNode（節點），其數值為 nodeValue，下一個節點為 nextNode，並回傳這個節點。
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2){
        return add(l1, l2, 0);
    }
}

/*
輸入：
l1 = 2 → 4 → 3   (表示 342)
l2 = 5 → 6 → 4   (表示 465)

執行流程：
2+5+0 = 7，建立節點 7
4+6+0 = 10，建立節點 0，進位 1
3+4+1 = 8，建立節點 8

輸出：
7 → 0 → 8   (表示 807)
*/
