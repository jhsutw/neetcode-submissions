# Definition for a binary tree node.
# class TreeNode:
#     def __init__(self, val=0, left=None, right=None):
#         self.val = val
#         self.left = left
#         self.right = right

class Solution:
    def isValidBST(self, root: Optional[TreeNode]) -> bool:
        def helper(node, lo, hi):
            if not node:
                return True
            
            if not (lo < node.val < hi):
                return False
            
            return helper(node.left, lo, node.val) and helper(node.right, node.val, hi)
        
        return helper(root, float('-inf'), float('inf'))
        