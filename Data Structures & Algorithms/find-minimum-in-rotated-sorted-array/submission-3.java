public class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;

        // 當左右邊界還沒重合時繼續搜尋
        while (l < r) {
            int m = l + (r - l) / 2; // 計算中間點（防止溢位）

            if (nums[m] < nums[r]) {
                // 右半邊是排序好的，最小值在左半邊（包含 m）(i.e. 5,6,0,"1",2,3,4)
                r = m;
            } else {
                // 最小值不可能在左半邊，移動左邊界 (i.e. 3,4,5,"6",0,1,2)
                l = m + 1;
            }
        }

        // 離開迴圈時，l == r，即最小值的位置
        return nums[l];
    }
}
