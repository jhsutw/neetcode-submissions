public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int len1 = nums1.length, len2 = nums2.length;

        int i = 0, j = 0;                 // 分別為 nums1 和 nums2 的index指標
        int median1 = 0, median2 = 0;     // 用來記錄中間兩個數（針對"偶數"情況）；奇數情況return median1
        // 遍歷次數：總共只需要找到第 (len1 + len2) / 2 + 1 小的元素為止
        for (int count = 0; count < (len1 + len2) / 2 + 1; count++) {
            median2 = median1;  // median2 永遠記錄前一次的 median1（用於偶數情況）

            // 同時 i、j 都還沒走到陣列尾端
            if (i < len1 && j < len2) {
                if (nums1[i] > nums2[j]) {
                    median1 = nums2[j];  // 取較小的數（模擬合併排序）
                    j++;                 // 移動 nums2 的指標
                } else {
                    median1 = nums1[i];
                    i++;
                }
            }
            // 若 nums2 已經走完，只取 nums1 的值 (奇偶數數情況該值均為(n/2)+1的值)
            else if (i < len1) {
                median1 = nums1[i];
                i++;
            }
            // 若 nums1 已經走完，只取 nums2 的值
            else {
                median1 = nums2[j];
                j++;
            }
        }

        // 若總長度是奇數，中位數就是 median1（第 mid 個元素）
        if ((len1 + len2) % 2 == 1) {
            return (double) median1;
        }
        // 若總長度是偶數，中位數是中間兩數平均
        else {
            return (median1 + median2) / 2.0;
        }
    }
}
