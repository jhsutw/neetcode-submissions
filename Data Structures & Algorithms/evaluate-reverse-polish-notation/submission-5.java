// 定義雙向鏈結串列的節點
public class DoublyLinkedList {
    String val;
    DoublyLinkedList next;
    DoublyLinkedList prev;

    DoublyLinkedList(String val, DoublyLinkedList next, DoublyLinkedList prev){
        this.val = val;
        this.next = next;
        this.prev = prev;
    }
}

class Solution {
    public int evalRPN(String[] tokens) {
        // 將 tokens 轉換成一個雙向鏈結串列
        DoublyLinkedList head = new DoublyLinkedList(tokens[0], null, null);
        DoublyLinkedList curr = head;

        for (int i = 1; i < tokens.length; i++){
            // 將每個 token 加到串列後面
            curr.next = new DoublyLinkedList(tokens[i], null, curr);
            curr = curr.next;
        }

        int ans = 0;
        // 還沒遍歷完所有的nodes就會進入迴圈
        while (head != null){
            if (("+-*/").contains(head.val)){
                // 定義被運算的值：運算符號前的兩個元素
                int l = Integer.parseInt(head.prev.prev.val);
                int r = Integer.parseInt(head.prev.val);
                int res = 0;
                if (head.val.equals("+")){
                    res = l + r;
                } else if (head.val.equals("-")){
                    res = l - r;
                } else if (head.val.equals("*")){
                    res = l * r;
                } else if (head.val.equals("/")){
                    res = l / r;
                }

                // 重新定義head.val為運算結果
                // String.valueof(int) 把數字轉為字串
                head.val = String.valueOf(res);
                // 刪除前兩個nodes（l & r）
                head.prev = head.prev.prev.prev;
                /* （發生情況：除了第一次運算之後的運算都會發生）
                   原本： X <-> A <-> B <-> head
                   你把 A 和 B 處理掉，讓：head.prev = X;
                   但此時 X.next 還是指向 A，所以鏈結是錯的！
                   所以要讓head.prev.next重新指向head
                */
                if (head.prev != null){
                    head.prev.next = head;
                }
            }
            ans  = Integer.parseInt(head.val);
            // 遍歷下一個node
            head = head.next;
        }
        return ans;
    }
}
