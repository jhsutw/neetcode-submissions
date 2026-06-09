public class Solution {
    public int missingNumber(int[] nums) {
        int n = nums.length;     // 陣列長度 n，數字範圍應該是 [0, n]
        int xorr = n;            // 初始化 xorr = n (因為 range 裡包含 n)

        for (int i = 0; i < n; i++) {
            // 把 index i 與 nums[i] 都 XOR 起來
            // 這樣 [0..n] 出現兩次的數字會互相抵銷掉
            xorr ^= i ^ nums[i];
        }

        return xorr;  // 最後剩下的就是缺少的那個數字
    }
}

