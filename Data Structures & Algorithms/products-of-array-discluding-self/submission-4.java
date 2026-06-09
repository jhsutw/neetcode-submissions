class Solution {
    public int[] productExceptSelf(int[] nums) {
      int n = nums.length;
      int[] res = new int[n];

      // 創建一個list放所有元素的前綴值
      res[0] = 1;
      for (int i = 1; i < nums.length; i++){
        res[i] = nums[i - 1] * res[i - 1];
      }
      
      // 用前綴乘上每個元素的後綴
      // 前一個方法用了res、pref、suff三個list；這個方法只用res（list）以及postfix（變數）
      int postfix = 1;
      for (int i = n - 1; i >= 0; i--){
        res[i] *= postfix;
        postfix *= nums[i];
      }
      return res;
    }
}  
