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

// 5. Divide And Conquer (Recursion)
// 從頭尾兩個lists中的list往中間夾擊，每次排序l & r的node.val 並持續擴充curr取得最終排序結果（因為遞迴curr值會存起來）
class Solution {
    public ListNode mergeKLists(ListNode[] lists) {
        if (lists == null || lists.length == 0){
            return null;
        }
        return divide(lists, 0, lists.length - 1);
    }

    // 將 lists 的 l 到 r 區間合併
    private ListNode divide(ListNode[] lists, int l, int r){
        if (l > r){
            return null;
        }
        if (l == r){
            return lists[l];
        }

        int mid = l + (r - l) / 2;  // 正確計算中間值
        ListNode left = divide(lists, l, mid);
        ListNode right = divide(lists, mid + 1, r);  // 修正變數名

        return conquer(left, right);  // 合併左右
    }

    // 合併兩個排序好的鏈表
    private ListNode conquer(ListNode l1, ListNode l2){
        ListNode dummy = new ListNode(0);  // 虛擬頭節點
        ListNode curr = dummy;

        while (l1 != null && l2 != null){
            if (l1.val <= l2.val){
                curr.next = l1;
                l1 = l1.next;
            } else {
                curr.next = l2;
                l2 = l2.next;
            }
            curr = curr.next;
        }

        // 連接剩餘節點
        if (l1 != null){
            curr.next = l1;
        } else {
            curr.next = l2;
        }

        return dummy.next;
    }
}
