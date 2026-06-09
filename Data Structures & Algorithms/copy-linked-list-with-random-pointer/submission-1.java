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
    // 定義一個 HashMap 來儲存 原始節點 ➜ 複製節點 的對應關係
    HashMap<Node, Node> map = new HashMap<>();

    public Node copyRandomList(Node head){
        // 若節點為 null，直接回傳 null
        if (head == null) return null;
        // 如果這個節點之前已經被複製過了，直接從 map 拿來用（避免重複複製）
        if (map.containsKey(head)) return map.get(head);

        // 建立一個新的節點（只填值）
        Node copy = new Node(head.val);
            // 記錄這個新節點對應原始節點
        map.put(head, copy);

        // 遞迴複製 next 與 random 節點（這邊會利用 map 避免重複）
        copy.next = copyRandomList(head.next);
        copy.random = map.get(head.random);
        return copy;
    }
}

