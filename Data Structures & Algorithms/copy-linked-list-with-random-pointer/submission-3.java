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

// 3. Hash Map (One Pass)
// 「先建立所有節點，再綁定指標」，確保 random 和 next 都能正確對應。
class Solution {
    public Node copyRandomList(Node head) {
        // 建立一個 HashMap，將舊節點對應到新節點（deep copy）
        HashMap<Node, Node> oldToCopy = new HashMap<>();

        // 預先將 null 映射到 null，避免後面處理 random 或 next 為 null 時出錯
        oldToCopy.put(null, null);

        Node cur = head;
        // 遍歷原始鏈結串列，逐一建立對應的新節點
        // 這段程式碼 不會直接複製 next 和 random，它只是在處理 目前節點 cur 本身的值 val，而不是它的指標關係。
        while(cur != null){
            // 若 map 尚未有對應的節點，先創一個預設 val=0 的新節點
            if (!oldToCopy.containsKey(cur)){
                oldToCopy.put(cur, new Node(0));
            }
            // 將新節點的 val 設為與原節點相同
            oldToCopy.get(cur).val = cur.val;

            // 建立對應的 next 節點（如果還沒建立）
            if (!oldToCopy.containsKey(cur.next)){
                oldToCopy.put(cur.next, new Node(0));
            }

            oldToCopy.get(cur).next = oldToCopy.get(cur.next); // 設定新節點的 next 指標

            // 建立對應的 random 節點（如果還沒建立）
            if (!oldToCopy.containsKey(cur.random)){
                oldToCopy.put(cur.random, new Node(0));
            }
            oldToCopy.get(cur).random = oldToCopy.get(cur.random); // 設定新節點的 random 指標
            
            // 移動到下一個節點
            cur = cur.next;
        }
        return oldToCopy.get(head);
    }
}
