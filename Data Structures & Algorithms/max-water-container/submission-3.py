class Solution:
    def maxArea(self, heights: List[int]) -> int:
        max_amount = 0
        l = 0
        r = len(heights) - 1

        while (l < r):
            amount = min(heights[l], heights[r]) * (r - l)
            max_amount = max(amount, max_amount)

            if (heights[l] < heights[r]):
                l += 1
            else:
                r -= 1
    
        return max_amount