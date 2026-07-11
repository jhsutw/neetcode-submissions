class Solution:
    def subsets(self, nums: List[int]) -> List[List[int]]:
        res = []
        temp = []

        def backtrack(start):
            res.append(temp[:])

            for i in range(start, len(nums)):
                temp.append(nums[i])
                backtrack(i + 1)
                temp.pop()

        backtrack(0)
        return res