public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 計算中位數的兩個位置：left 和 right
        // 若總長度為奇數，left == right
        // 若總長度為偶數，則取第 left 和第 right 小的平均
        int left = (nums1.length + nums2.length + 1) / 2;
        int right = (nums1.length + nums2.length + 2) / 2;

        // 呼叫 getKth 找出第 left 小與第 right 小的值，再取平均
        return (getKth(nums1, nums1.length, nums2, nums2.length, left, 0, 0) +
                getKth(nums1, nums1.length, nums2, nums2.length, right, 0, 0)) / 2.0;
    }

    // getKth: 從兩個排序好的陣列中，找出第 k 小的元素
    public int getKth(int[] a, int m, int[] b, int n, int k, int aStart, int bStart) {
        // 若 a 的長度 > b，交換陣列，讓 a 是較短的那一個（遞迴時可以避免越界）
        if (m > n) {
            return getKth(b, n, a, m, k, bStart, aStart);
        }

        // 若 a 已經沒元素了，直接回傳 b 中第 k 小的元素
        if (m == 0) {
            return b[bStart + k - 1];
        }

        // 若只剩第 1 小的元素，直接取 a[aStart] 與 b[bStart] 中較小的
        if (k == 1) {
            return Math.min(a[aStart], b[bStart]);
        }

        // 取 a 中最多 k/2 個元素，b 同理
        int i = Math.min(m, k / 2);
        int j = Math.min(n, k / 2);

        // 比較 a 和 b 當前的第 i, j 個元素大小
        if (a[aStart + i - 1] > b[bStart + j - 1]) {
            // a 的前 i 個不可能包含第 k 小，保留 a，縮減 b 範圍（不斷排除不可能為medium的元素）
            return getKth(a, m, b, n - j, k - j, aStart, bStart + j);
        } else {
            // b 的前 j 個不可能包含第 k 小，保留 b，縮減 a 範圍（不斷排除不可能為medium的元素）
            return getKth(a, m - i, b, n, k - i, aStart + i, bStart);
        }
    }
}