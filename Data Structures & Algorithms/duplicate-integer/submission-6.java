public class Solution {
    public boolean hasDuplicate(int[] nums) {
        Arrays.sort(nums);

        // noted that i should start from 1, instead of 0
        for (int i = 1; i < nums.length; i++){
            if (nums[i] == nums[i - 1]){
                return true;
            }
        }
        return false;    
    }
}