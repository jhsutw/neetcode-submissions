class Solution {
    public int[] productExceptSelf(int[] nums) {
        int n = nums.length;
        int[] prefix = new int[n];
        int[] profix = new int[n];
        int[] res = new int[n];

        prefix[0] = 1;
        int preSum = 1;
        for (int i = 1; i < n; i++) {
            preSum *= nums[i - 1];
            prefix[i] = preSum;
        }

        profix[n - 1] = 1;
        int proSum = 1;
        for (int i = n - 2; i >= 0; i--) {
            proSum *= nums[i + 1];
            profix[i] = proSum;
        }

        for (int i = 0; i < n; i++) {
            res[i] = prefix[i] * profix[i];
        }

        return res;
    }
}  
