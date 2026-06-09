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

public class Solution {
    public ListNode reverseKGroup(ListNode head, int k) {
        ListNode cur = head;
        int group = 0;

        // 檢查是否還有足夠的 k 個節點可以反轉
        while (cur != null && group < k) { 
            cur = cur.next;
            group++;
        }

        // 如果有足夠的 k 個節點，才進行反轉
        if (group == k) { 
            // 先遞迴反轉後面的節點，cur 此時指向第 k+1 個節點
            cur = reverseKGroup(cur, k); 

            // 反轉當前這 k 個節點
            while (group-- > 0) { 
                ListNode tmp = head.next;  // 暫存下一個節點
                head.next = cur;           // 將目前節點接到已反轉的後半部分前面
                cur = head;                // 移動 cur，使其成為新的前端
                head = tmp;                // 繼續處理下一個節點
            }

            // head 現在已經移到下一段 group 的起點，cur 為這段反轉後的新頭
            head = cur;
        }

        // 若不足 k 個節點，不反轉，直接回傳原本的 head
        return head;
    }
}

/*
任務是把 1 → 2 → 3 反轉後接到 5 → 4 前面

head = 1
cur = 5 → 4    ← 已遞迴處理好的部分

1st recursion:
tmp = head.next = 2
head.next = cur = 5 → 4       // 1 → 5 → 4
cur = head = 1
head = tmp = 2

2nd recursion:
tmp = 3
head.next = cur = 1 → 5 → 4   // 2 → 1 → 5 → 4
cur = head = 2
head = tmp = 3

3rd recursion:
tmp = null
head.next = cur = 2 → 1 → 5 → 4 // 3 → 2 → 1 → 5 → 4
cur = head = 3
head = tmp = null

result:
cur = 3 → 2 → 1 → 5 → 4
return head = cur
*/