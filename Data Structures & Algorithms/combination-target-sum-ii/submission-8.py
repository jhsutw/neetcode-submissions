class Solution:
    def combinationSum2(self, candidates: List[int], target: int) -> List[List[int]]:
        res = []
        candidates.sort()

        def backtrack(start, cur, total):
            if total == target:
                res.append(cur[:])
                return
            
            if total > target:
                return

            for i in range(start, len(candidates)):
                if i > start and candidates[i - 1] == candidates[i]:
                    continue
                if total + candidates[i] > target:
                    break
            
                cur.append(candidates[i])
                backtrack(i + 1, cur, total + candidates[i])
                cur.pop()

        backtrack(0, [], 0)
        return res