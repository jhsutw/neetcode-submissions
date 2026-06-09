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
        // 建立一個 List 用來暫存所有節點的值
        List<Integer> nodes = new ArrayList<>();

        // 走訪每一個 linked list，把所有的節點值都加到 nodes 中
        for (ListNode lst : lists){
            while (lst != null){
                nodes.add(lst.val); // 將每個節點的 val 存進 list
                lst = lst.next;
            }
        }

        // 把所有節點值排序（從小到大）
        // Collections.sort() 是專門設計來對 List 進行排序的方法
        // Arrays.sort() 是用來排序 陣列（array）
        Collections.sort(nodes);

        // 建立一個 dummy head 來方便組裝新的 linked list
        // 可以保留初始的 dummy node res，然後最後 return res.next，就能正確回傳整個 list
        // 直接用 cur 作為頭，就無法再回到最開始的頭節點了，因為 cur 不會保留原始的頭位置，會一直往後移
        ListNode res = new ListNode(0);
        ListNode cur = res;

        // 將排序後的值一個一個變成新節點串起來
        for (int node : nodes){
            cur.next = new ListNode(node);
            cur = cur.next;
        }
        // 回傳真正的頭節點（略過 dummy head）
        return res.next;
    }
}
