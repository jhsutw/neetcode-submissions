// Array
class Solution {
    public int findDuplicate(int[] nums) {
        int[] seen = new int[nums.length];
        for (int num : nums){
            if (seen[num - 1] == 1){
                return num;
            }
            seen[num - 1] = 1;
        }
        return -1;
    }
}
