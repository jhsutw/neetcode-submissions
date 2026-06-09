class Solution:
    def longestConsecutive(self, nums: List[int]) -> int:
        # find the starting point of consecutive num groups
        # count the length of the consecutive num groups
        # choose the max length to be the answer

        existing = set(nums)
        candidates = []
        for num in nums:
            if (num - 1) not in existing:
                candidates.append(num)
        
        max_length = 0
        for candidate in candidates:
            length = 1
            while (candidate + length) in existing:
                length += 1
            max_length = max(max_length, length)

        return max_length
            

        