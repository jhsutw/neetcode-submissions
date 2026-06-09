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
    // 遞迴函數：從尾巴開始回傳，n 為倒數計數器
    public ListNode rec (ListNode head, int[] n) {
        if (head == null){
            return null; // 基底條件：走到底回傳 null 開始回溯
        }

        // 遞迴到底再回來，每一層都會處理自己的 head
        head.next = rec(head.next, n);
        n[0]--; // 回溯階段開始倒數
        if (n[0] == 0){
             // 如果是第 n 個，就跳過這個節點（刪掉）
            return head.next;
        }
        return head; // 其他節點保持連接
    }

    public ListNode removeNthFromEnd(ListNode head, int n){
        // 用陣列包住 n，使其在遞迴中可被修改（Java 是 pass-by-value）
        // Java 是「傳值呼叫」，基本型別像 int 傳遞時只會複製值。如果我們要在遞迴裡持續修改 n，必須用 陣列或物件包裝它，才能讓每層遞迴看到同一份資料。
        return rec(head, new int[]{n});
    }
}
