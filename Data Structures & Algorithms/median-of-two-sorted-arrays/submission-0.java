class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;

         // 建立一個新的int array來存合併後的兩個陣列
        int[] merged = new int[len1 + len2];
        // 將 nums1 的所有元素（從 index 0 開始）複製到 merged 的開頭位置（index 0 開始），共複製 len1 個元素
        System.arraycopy(nums1, 0, merged, 0, len1);
        // 將 nums2 的所有元素（從 index 0 開始）複製到 merged index len1的位置，共複製 len2 個元素
        System.arraycopy(nums2, 0, merged, len1, len2);
        Arrays.sort(merged);

        int totalLen = merged.length;
        // 如果總長度是偶數：取中間兩數的平均
        if (totalLen % 2 == 0){
            return (merged[totalLen / 2 - 1] + merged[totalLen / 2]) / 2.0;
        } else {
            // 如果是奇數：取正中間那個數
            return merged[totalLen / 2];
        }
    }
}
