/*
// Definition for a Node.
class Node {
    int val;
    Node next;
    Node random;

    public Node(int val) {
        this.val = val;
        this.next = null;
        this.random = null;
    }
}
*/

/*
5. Space Optimized - II
這段程式碼的巧妙之處在於：
第一輪利用原本 random 欄位暫存 copy 節點，節省空間。
第二輪使用已建立的 copy node 來正確設定它們的 random 指向。
第三輪拆分原始節點與新節點，並恢復原本節點的 random 欄位。
*/

public class Solution {
    public Node copyRandomList(Node head) {
        if (head == null) {
            return null;
        }

        // 第一次遍歷：插入 copy 節點，暫存在原節點的 random 欄位中
        Node l1 = head;
        while (l1 != null) {
            Node l2 = new Node(l1.val);  // 建立 copy 節點
            l2.next = l1.random;         // 暫時把 l2 的 next 指向原節點的 random
            l1.random = l2;              // 原節點的 random 暫存 copy 節點
            l1 = l1.next;                // 前進至下一個原節點
        }

        // 取得新的 list 開頭（原 head 的 random 就是 copy head）
        Node newHead = head.random;

        // 第二次遍歷：設定 copy 節點的 random 指標
        l1 = head;
        while (l1 != null) {
            Node l2 = l1.random;         // copy 節點
            // l2.next 是原本節點的 random，所以 l2.random = l2.next.random 是指向對應的 copy 節點
            l2.random = (l2.next != null) ? l2.next.random : null;
            l1 = l1.next;
        }

        // 第三次遍歷：恢復原 list 並建構新 list 的 next 指標
        l1 = head;
        while (l1 != null) {
            Node l2 = l1.random;              // copy 節點
            l1.random = l2.next;              // 原節點的 random 指回原本的 random 節點
            l2.next = (l1.next != null) ? l1.next.random : null; // copy 的 next 指向下一個 copy
            l1 = l1.next;
        }

        return newHead;
    }
}