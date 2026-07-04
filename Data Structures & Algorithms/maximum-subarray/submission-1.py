class Solution:
    def maxSubArray(self, nums: List[int]) -> int:
        cur_sum = nums[0]
        total_sum = nums[0]

        for num in nums[1 :]:
            cur_sum = max(num, cur_sum + num)
            total_sum = max(total_sum, cur_sum)
        
        return total_sum