class Solution:
    def productExceptSelf(self, nums: List[int]) -> List[int]:
        # use prefix list to store the product before ith elem
        prefix = 1
        prefix_list = [1] * len(nums)
        for i in range(len(nums) - 1):
            prefix *= nums[i]
            prefix_list[i + 1] = prefix

        # use suffix list to store the product after the ith elem
        suffix = 1
        suffix_list = [1] * len(nums)
        for i in range(len(nums) - 1, 0, -1):
            suffix *= nums[i]
            suffix_list[i - 1] = suffix 
        
        result = [0] * len(nums)
        for i in range(len(nums)):
            result[i] = prefix_list[i] * suffix_list[i]

        return result
        