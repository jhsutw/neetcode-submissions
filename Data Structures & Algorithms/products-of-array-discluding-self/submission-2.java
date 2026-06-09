// division method 先把所有數字相乘，再除掉各項值
class Solution {
    public int[] productExceptSelf(int[] nums) {
       int prod = 1, zeroCount = 0;
       for (int num : nums){
            if (num != 0){
                prod *= num;
            } else {
                zeroCount++;
            }    
       }
       // 超過一個數字為0，output是全為0的list
       if (zeroCount > 1){
            return new int[nums.length];
       }

        // 注意除以0的問題！res[i] = (nums[i] == 0)? prod : 0; 若該項為零直接輸出prod；反之輸出0
       int[] res = new int[nums.length];
       for (int i = 0; i < nums.length; i++){
            if (zeroCount > 0){
                res[i] = (nums[i] == 0)? prod : 0;
            } else {
                res[i] = prod / nums[i];
            }
       }
       return res;
    }
}  
