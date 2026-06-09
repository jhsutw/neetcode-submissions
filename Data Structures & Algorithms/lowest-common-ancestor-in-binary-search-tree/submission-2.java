public class Solution {
    public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
        TreeNode cur = root;  // 從根節點開始搜尋
        
        while (cur != null) {
            // 如果 p 和 q 都比目前節點大，表示都在右子樹
            if (p.val > cur.val && q.val > cur.val) {
                cur = cur.right;
                
            // 如果 p 和 q 都比目前節點小，表示都在左子樹
            } else if (p.val < cur.val && q.val < cur.val) {
                cur = cur.left;
                
            // 否則，p 和 q 分別在當前節點的左右兩邊，cur 就是最近公共祖先
            } else {
                return cur;
            }
        }
        
        return null;  // 不會進到這邊，除非樹為空或輸入異常
    }
}