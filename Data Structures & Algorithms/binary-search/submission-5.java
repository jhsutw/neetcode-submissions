class Solution {
    public int search(int[] nums, int target) {
        // 用內建的function
        int index = Arrays.binarySearch(nums, target);
        // 有找到則index >= 0 return index；否則回傳 -1
        return index >= 0 ? index : -1;
    }
}
