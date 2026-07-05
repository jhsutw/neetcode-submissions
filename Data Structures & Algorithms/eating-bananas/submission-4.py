class Solution:
    def minEatingSpeed(self, piles: List[int], h: int) -> int:
        left = 1
        right = max(piles)
        min_rate = right

        while left <= right:
            rate = left + (right - left) // 2
            time = 0

            for pile in piles:
                time += math.ceil(pile / rate)
            
            if time <= h:
                min_rate = min(min_rate, rate)
                right = rate - 1
            else:
                left = rate + 1

        return min_rate