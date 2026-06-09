class Solution:
    def threeSum(self, nums: List[int]) -> List[List[int]]:
        # create a result list to put the result
        # iterate each elem as the first elem of triples sum to be 0
        # use two pointer to find the first and the second elem of triples
        result = []
        nums.sort()

        for i in range(0, len(nums) - 2):
            if (i > 0 and nums[i] == nums[i - 1]):
                continue

            left = i + 1
            right = len(nums) - 1

            while (left < right):
                triple_sum = nums[i] + nums[left] + nums[right]
                if triple_sum == 0:
                    result.append([nums[i], nums[left], nums[right]])

                    left += 1
                    right -= 1

                    while (left < right and nums[left] == nums[left - 1]):
                        left += 1

                    while (left < right and nums[right] == nums[right + 1]):
                        right -= 1
                elif (triple_sum < 0):
                        left += 1
                else:
                        right -= 1
        return result             
                


                