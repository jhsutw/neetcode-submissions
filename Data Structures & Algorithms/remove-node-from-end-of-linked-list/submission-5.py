# Definition for singly-linked list.
# class ListNode:
#     def __init__(self, val=0, next=None):
#         self.val = val
#         self.next = next

class Solution:
    def removeNthFromEnd(self, head: Optional[ListNode], n: int) -> Optional[ListNode]:
        # dummy → 1 → 2 → 3 → 4
        dummy = ListNode(0, head)
        
        first = dummy
        second = dummy

        # 走 n+1 步 →  second 停在目標節點的前一個
        for i in range(n + 1):
            first = first.next

        while first:
            first = first.next
            second = second.next

        second.next = second.next.next

        # 不回傳 head，因為 head 可能已經被刪掉
        return dummy.next



        