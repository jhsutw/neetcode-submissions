from math import inf

class Solution:
    def maxProfit(self, prices: List[int]) -> int:
        
        min_val = inf
        total_min = 0
        for price in prices:
            min_val = min(price, min_val)
            diff = price - min_val
            total_min = max(diff, total_min)
        return total_min
        