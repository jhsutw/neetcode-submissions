class Solution {
    public int[] productExceptSelf(int[] nums) {
        // 建立三個int[]: res放結果、pref放前綴（nums[i]前的值乘積）、suff放後綴（nums[i]後的值乘積）
        int n = nums.length;
        int[] res = new int[n];
        int[] pref = new int[n];
        int[] suff = new int[n];

        // nums[0]（首值）前綴為1；num[i - 1]（尾值）後綴為1
        pref[0] = 1;
        suff[n - 1] = 1;
        // 遞迴概念計算每個元素的前後綴
        for (int i = 1; i < n; i++){
            pref[i] = pref[i - 1] * nums[i - 1];
        }
        for (int i = n - 2; i >= 0; i--){
            suff[i] = suff[i + 1] * nums[i + 1];
        }
        // 每個元素的前後綴相乘得res
        for (int i = 0; i < n; i++){
            res[i] = pref[i] * suff[i];
        }
        return res;
    }
}  
