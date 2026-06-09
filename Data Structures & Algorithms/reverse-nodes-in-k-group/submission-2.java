class Solution {
    
    public ListNode reverseKGroup(ListNode head, int k) {
        // 建立虛擬節點 dummy，指向 head，方便處理頭節點反轉
        ListNode dummy = new ListNode(0, head);
        ListNode groupPrev = dummy;

        // 重複處理每一組長度為 k 的區塊
        while (true) {
            // 以 groupPrev 為起點，往後找第 k 個節點
            ListNode kth = getKth(groupPrev, k);
            if (kth == null) break; // 如果不足 k 個，直接結束

            // 記錄下一組的起點（即第 k+1 個節點）
            ListNode groupNext = kth.next;

            // 開始反轉這一組節點（從 groupPrev.next 到 kth）
            ListNode prev = groupNext;             // 初始化 prev = groupNext（最終會接在尾巴）
            ListNode curr = groupPrev.next;        // curr 為目前要反轉的節點

            // 以下為標準單向鏈結串列反轉邏輯
            // 舉例第一次迴圈 curr 是節點 1，prev 是節點 4（groupNext）
            while (curr != groupNext) {
                ListNode tmp = curr.next;          // 暫存下一個節點
                curr.next = prev;                  // 當前節點指向 prev（反轉）
                prev = curr;                       // prev 向前推進
                curr = tmp;                        // curr 向前推進
            }

            // 舉例第一組處理完後為：
            // 原始：1 → 2 → 3 → 4
            // 反轉後：3 → 2 → 1 → 4
            // groupPrev 是 dummy，dummy.next 要指向 3
            // 原本 groupPrev.next 是 1，現在變成這一組的最後一個節點

            // 接回主鏈
            ListNode tmp = groupPrev.next;         // 暫存這組的起點（反轉後會變成尾巴）
            groupPrev.next = kth;                 // 將上一組的尾巴接到反轉後的頭
            groupPrev = tmp;                      // 更新 groupPrev 為這組的尾巴，準備處理下一組
        }

        // 最後返回 dummy.next，即為反轉後的鏈結串列頭
        return dummy.next;
    }

    // 輔助函式：從 curr 開始往後找第 k 個節點，若不足 k 個則回傳 null
    private ListNode getKth(ListNode curr, int k) {
        while (curr != null && k > 0) {
            curr = curr.next;
            k--;
        }
        return curr;
    }
}
/* 舉例說明：
第一次：
tmp = 2
curr.next = prev → 1 → 4
prev = curr = 1
curr = tmp = 2

第二次：
tmp = 3
curr.next = prev → 2 → 1
prev = curr = 2
curr = tmp = 3

第三次：
tmp = 4
curr.next = prev → 3 → 2
prev = 3
curr = 4
*/
