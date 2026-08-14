class Solution:
    def mergeTriplets(self, triplets: List[List[int]], target: List[int]) -> bool:
        good = [False, False, False]

        for a, b, c in triplets:
            if a > target[0] or b > target[1] or c > target[2]:
                continue

            if a == target[0]:
                good[0] = True
            if b == target[1]:
                good[1] = True
            if c == target[2]:
                good[2] = True

        return all(good)
        