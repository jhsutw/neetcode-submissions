// Upper Bound（上一題的變形：從閉區間 >> 開區間）
class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length;

        while (l < r){
            int m = l + ((r - l) / 2);
            if (nums[m] > target){
                r = m;
            } else{
                l = m + 1;
            }
        }
        // target會是比l少一個index的值
        return (l > 0 && nums[l - 1] == target) ? l - 1 : -1;
    }
}
