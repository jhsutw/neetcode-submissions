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
        l = 0;
        r = nums.length - 1;
        
        // 因為該int array已經升序排列，所以只要比較target跟pivot的大小，就知道target在pivot的哪一側
        if (target >= nums[pivot] && target <= nums[r]){
            l = pivot; // target在pivot右側
        } else {
            r = pivot - 1; // target在pivot左側
        }

        // binary search main function
        while (l <= r){
            int mid = (l + r) / 2;
            if (nums[mid] == target) {
                return mid;
            } else if (nums[mid] < target){
                l = mid + 1;
            } else {
                r = mid - 1;
            }
        }
        return -1;
    }
}
