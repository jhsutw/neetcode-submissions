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
    public ListNode mergeKLists(ListNode[] lists) {
        // 若沒有任何 list，直接回傳 null
        if (lists.length == 0) return null;
        
        // 建立一個最小堆，按照 ListNode 的 val 排序
        PriorityQueue<ListNode> minHeap = new PriorityQueue<>((a, b) -> a.val - b.val);

        // 把每條鏈表的頭節點放進 heap（只放非空節點）
        for (ListNode list : lists){
            if (list != null){
                minHeap.offer(list);
            }
        }

        // 建立虛擬頭節點，cur 用來串接結果
        ListNode res = new ListNode(0);
        ListNode cur = res;

        // 每次從 heap 取出最小的節點接到 cur 後面
        while(!minHeap.isEmpty()){
            ListNode node = minHeap.poll();
            cur.next = node;
            cur = cur.next;

            // 如果該節點還有下一個節點，就丟回 heap
            node = node.next;
            if (node != null){
                minHeap.offer(node); // 因為上面只有把LinedList的頭節點放進去
            }
        }
        return res.next;
    }
}
