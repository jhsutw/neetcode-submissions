class Solution:
    def combinationSum(self, nums: List[int], target: int) -> List[List[int]]:
        res = []

        def backtrack(start, cur, total):
            if total == target:
                res.append(cur[:])
                return

            if total > target:
                return

            for i in range(start, len(nums)):
                cur.append(nums[i])
                backtrack(i, cur, total + nums[i])
                cur.pop()

        backtrack(0, [], 0)
        return res