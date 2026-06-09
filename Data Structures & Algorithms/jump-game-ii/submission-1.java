class Solution {
    public int jump(int[] nums) {
        // 設定結果、左界、右界
        int res = 0;
        int l = 0;
        int r = 0;

        // 掃描左右界中的值，找到可以跑最遠的點，紀錄到達位置（farest）
        while (r < nums.length - 1) {
            int farthest = 0;

            for (int i = l; i <= r; i++) {
                farthest = Math.max(farthest, i + nums[i]);
            }

            // 左界更新為下一個右界後一個值
            l = r + 1;

            // 右界更新為到達位置（繼續掃描l,r間下一個最遠的到達位置）
            r = farthest;
            res++;
        }
        return res;
    }
}
