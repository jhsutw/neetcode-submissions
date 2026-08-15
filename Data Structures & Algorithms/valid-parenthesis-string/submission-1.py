class Solution:
    def checkValidString(self, s: str) -> bool:
        left_min = 0
        left_max = 0

        for char in s:
            if char == '(':
                left_min += 1
                left_max += 1
            
            if char == ')':
                left_min -= 1
                left_max -= 1
            
            if char == '*':
                left_min -= 1
                left_max += 1
            
            if left_max < 0:
                return False
            
            left_min = max(0, left_min)

        return left_min == 0