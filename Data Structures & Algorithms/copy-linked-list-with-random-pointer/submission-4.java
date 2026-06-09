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

class Solution {
    public Node copyRandomList(Node head) {
        if (head == null){
            return null;
        }

        // 第一次遍歷：在每個節點後插入一個 copy 節點
        // 原始: A -> B -> C
        // 變成: A -> A' -> B -> B' -> C -> C'
        Node l1 = head;
        while (l1 != null){
            Node l2 = new Node(l1.val); // 建立 copy 節點
            l2.next = l1.next; // copy 節點指向原本的下一個
            l1.next = l2; // 原節點指向 copy 節點
            l1 = l2.next; // 移動到下一個原始節點
        }

         // 保存深拷貝 list 的開頭
        Node newHead = head.next;

        // 第二次遍歷：設定 copy 節點的 random
        l1 = head;
        while (l1 != null){
            if (l1.random != null){
                l1.next.random = l1.random.next; // 把原節點跟copy節點的random對應連接在一起
            }
            l1 = l1.next.next; // 移動到下一個原始節點
        }

         // 第三次遍歷：拆分兩條 list，恢復原始 list，並取得 copy list
        l1 = head;
        while(l1 != null){
            Node l2 = l1.next; // copy 節點
            l1.next = l2.next; // 恢復原始節點指向下一個原始節點
            if (l2.next != null){
                l2.next = l2.next.next; // copy 節點指向下一個 copy 節點
            }
            l1 = l1.next; // 前進到下一個原始節點
        }
        return newHead;
    }
}
