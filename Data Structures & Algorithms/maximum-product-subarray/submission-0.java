// 1. Brute Force
public class Solution {
    public int maxProduct(int[] nums) {
        int res = nums[0]; // 初始化最大乘積為第一個元素

        // 外層迴圈選擇子陣列的起點
        for (int i = 0; i < nums.length; i++) {
            int cur = nums[i]; // 初始化當前子陣列的乘積為起點元素
            res = Math.max(res, cur); // 檢查是否更新最大乘積

            // 內層迴圈選擇子陣列的終點
            for (int j = i + 1; j < nums.length; j++) {
                cur *= nums[j]; // 擴展子陣列並累積乘積
                res = Math.max(res, cur); // 檢查是否更新最大乘積
            }
        }

        return res; // 回傳最大乘積
    }
}