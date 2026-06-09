// Lower Bound
class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length;

        while (l < r){
            int m = l + ((r - l) / 2);
            // 把等於的case放到這！
            if (nums[m] >= target){
                r = m;
            } else{
                l = m + 1;
            }
        }
        // target會是l的值
        // l > 0 變成 l < nums.length (target可以包含index=0的值) 
        return (l < nums.length && nums[l] == target) ? l : -1;
    }
}
