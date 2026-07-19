class Solution:
    def generateParenthesis(self, n: int) -> List[str]:
        res = []
        cur = []

        def backtrack(left, right):
            if len(cur) == n * 2:
                res.append(''.join(cur))
                return

            if left < n:
                cur.append('(')
                backtrack(left + 1, right)
                cur.pop()
                
            if right < left:
                cur.append(')')
                backtrack(left, right + 1)
                cur.pop()

        backtrack(0, 0)
        return res