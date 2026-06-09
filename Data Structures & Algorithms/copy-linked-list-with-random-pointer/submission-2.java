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
        // 建立一個 Map，記錄原始節點與對應複製節點的映射
        Map<Node, Node> oldToCopy = new HashMap<>();

        // 預先加入 null 映射，避免之後處理 random 或 next 為 null 時出錯
        oldToCopy.put(null, null);

        // 第一次遍歷：複製每個節點的 val，建立對應的新節點
        Node cur = head;
        while (cur != null){
            Node copy = new Node(cur.val); // 建立新節點（只有 val）；(next, random)都會被複製
            oldToCopy.put(cur, copy); // 原節點 ➜ 新節點 的對應關係存入 map
            cur = cur.next; // 前進到下一個節點
        }

        // 第二次遍歷：設定新節點的 next 和 random 指標
        cur = head;
        while (cur != null){
            Node copy = oldToCopy.get(cur); // 取得對應的新節點
            copy.next = oldToCopy.get(cur.next); // 設定新的 next 指向對應新節點
            copy.random = oldToCopy.get(cur.random); // 設定新的 random 指向對應新節點
            cur = cur.next; // 前進到下一個節點
        }
        return oldToCopy.get(head);
    }
}
