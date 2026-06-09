// Binary Search
public class Solution {
    public int findMin(int[] nums) {
        int l = 0;
        int r = nums.length - 1;
        int res = nums[0]; // 預設最小值為陣列第一個元素

        while (l <= r) {
            // 如果目前區間是排序好的，直接回傳最左邊的數字就是最小值（沒有rotation）
            if (nums[l] < nums[r]) {
                res = Math.min(res, nums[l]);
                break;
            }

            int m = l + (r - l) / 2; // 中間索引（避免 overflow）
            res = Math.min(res, nums[m]); // 更新目前發現的最小值

            // 若左半區間是排序好的（代表最小值一定不在這區）
            if (nums[m] >= nums[l]) {
                l = m + 1; // 往右邊繼續搜尋（i.e. 4,5,6,"7",0,1,2）
            } else {
                r = m - 1; // 否則往左邊繼續搜尋（i.e. 5,6,0,"1",2,3,4）
            }
        }

        return res; // 回傳最小值
    }
}