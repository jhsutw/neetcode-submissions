// 4. Prefix & Suffix
public class Solution {
    public int maxProduct(int[] nums) {
        int n = nums.length;
        int res = nums[0]; // 紀錄目前找到的最大乘積
        int prefix = 0,    // 從左到右的累積乘積
            suffix = 0;    // 從右到左的累積乘積

        for (int i = 0; i < n; i++) {
            // 更新 prefix：如果之前的乘積是 0，就從當前元素重新開始 (等於用0把nums分成多段)
            prefix = nums[i] * (prefix == 0 ? 1 : prefix);

            // 更新 suffix：如果之前的乘積是 0，就從當前反向元素重新開始
            suffix = nums[n - 1 - i] * (suffix == 0 ? 1 : suffix);

            // 更新全域最大值，取 prefix 或 suffix 中較大的
            res = Math.max(res, Math.max(prefix, suffix));
        }
        return res;
    }
}