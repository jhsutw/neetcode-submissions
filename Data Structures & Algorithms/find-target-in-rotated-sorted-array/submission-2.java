class Solution {
    public int search(int[] nums, int target) {
        int l = 0, r = nums.length - 1;

        // 找 pivot (i.e. 456"1"23)
        while (l < r) {
            int m = (l + r) / 2;
            if (nums[m] > nums[r]) { // 中間值 m 落在 左半段（大的那段）
                l = m + 1;
            } else { // 中間值 m 落在 右半段（含最小值那段）
                r = m;
            }
        }

        int pivot = l;
        // 找pivot往左有沒有target
        int result = binarySearch(nums, target, 0, pivot - 1);
        // 找到就return
        if (result != -1){
            return result;
        }
        // 找pivot往右有沒有target
        return binarySearch(nums, target, pivot, nums.length - 1);
    }

    // binary search main function
    public int binarySearch(int[] nums, int target, int left, int right){
        while (left <= right){
            int mid = (left + right) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target){
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return -1;
    }
}
