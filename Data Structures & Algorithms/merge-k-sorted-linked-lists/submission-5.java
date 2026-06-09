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
    public ListNode mergeKLists(ListNode[] lists) {
        // 如果輸入陣列為 null 或空的，直接回傳 null
        if (lists == null || lists.length == 0) {
            return null;
        }

        // 當 lists 中還有超過一個鏈表時，就持續進行合併
        while (lists.length > 1) {
            List<ListNode> mergedLists = new ArrayList<>();

            // 每次合併兩個鏈表
            for (int i = 0; i < lists.length; i += 2) {
                ListNode l1 = lists[i];
                // 如果 i + 1 還在範圍內就抓出來，否則設為 null
                ListNode l2 = (i + 1) < lists.length ? lists[i + 1] : null;
                // 將合併後的結果放進暫存的 list
                mergedLists.add(mergeList(l1, l2));
            }

            // 更新 lists，進行下一輪合併
            lists = mergedLists.toArray(new ListNode[0]);
        }

        // 所有的 list 都合併成一個，回傳 (lists會是一個雙層list，但只會剩一個子list)
        return lists[0];
    }

    // 合併兩個排序的鏈表
    private ListNode mergeList(ListNode l1, ListNode l2) {
        // 使用 dummy node 簡化處理流程
        ListNode dummy = new ListNode();
        ListNode tail = dummy;

        // 當兩個鏈表都還有節點時，選擇較小的加入結果中
        while (l1 != null && l2 != null) {
            if (l1.val < l2.val) {
                tail.next = l1;  // 選 l1 節點
                l1 = l1.next;
            } else {
                tail.next = l2;  // 選 l2 節點
                l2 = l2.next;
            }
            tail = tail.next;  // 更新 tail 指向新加入的節點
        }

        // 若 l1 還有剩餘節點，直接接上
        if (l1 != null) {
            tail.next = l1;
        }

        // 若 l2 還有剩餘節點，也直接接上
        if (l2 != null) {
            tail.next = l2;
        }

        // 回傳合併後的鏈表（跳過 dummy 節點）
        return dummy.next;
    }
}

/*
範例流程圖（假設有 4 條排序鏈表）：
初始：lists = [L1, L2, L3, L4]

第一輪合併：
merge(L1, L2) → M1
merge(L3, L4) → M2
⇒ lists = [M1, M2]

第二輪合併：
merge(M1, M2) → Result
⇒ lists = [Result] → 長度為 1，停止迴圈
*/