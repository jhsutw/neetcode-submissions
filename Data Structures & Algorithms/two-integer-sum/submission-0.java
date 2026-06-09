class Solution {
    public int[] twoSum(int[] nums, int target) {
        for (int i = 0; i < nums.length; i++){
            // j = i + 1 （不需要重新比對i前面的數字，且小的數字會在output array的前面）
            for (int j = i + 1; j < nums.length; j++){
                if (nums[i] + nums[j] == target){
                    // int[]{i, j} 的 Output 為 [0,1] (new 不要忘記)
                    return new int[]{i, j};
                }
            }
        }
        // 建立一個長度為 0 的整數陣列
        return new int[0];
    }
}
