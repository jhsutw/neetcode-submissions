public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // 將兩陣列命名為 A 和 B，稍後會保證 A 是較短的那個
        int[] A = nums1;
        int[] B = nums2;
        int total = A.length + B.length;         // 總長度
        int half = (total + 1) / 2;              // 中位數切點（左半邊總共應該有幾個元素）

        // 保證 A 是較短的陣列（加速並避免越界）
        if (B.length < A.length) {
            int[] temp = A;
            A = B;
            B = temp;
        }

        int l = 0;
        int r = A.length;  // 在 A 上做 binary search
        while (l <= r) {
            int i = (l + r) / 2;     // A 分割線左側元素個數
            int j = half - i;        // B 分割線左側元素個數（使 A+B 左半邊共有 half 個元素）

            // 取得分割線左右兩側的元素（若越界則用極小或極大值）
            int Aleft  = i > 0 ? A[i - 1] : Integer.MIN_VALUE;
            int Aright = i < A.length ? A[i] : Integer.MAX_VALUE;
            int Bleft  = j > 0 ? B[j - 1] : Integer.MIN_VALUE;
            int Bright = j < B.length ? B[j] : Integer.MAX_VALUE;

            // 核心條件：左側最大值 <= 右側最小值 → 分割正確
            if (Aleft <= Bright && Bleft <= Aright) {
                // 若總長度為奇數 → 中位數是左側最大值
                if (total % 2 != 0) {
                    return Math.max(Aleft, Bleft);
                }
                // 若總長度為偶數 → 中位數是中間兩數平均
                return (Math.max(Aleft, Bleft) + Math.min(Aright, Bright)) / 2.0;
            }
            // 若 Aleft 太大，代表 i 分得太右邊了，往左移
            else if (Aleft > Bright) {
                r = i - 1;
            }
            // 若 Bleft 太大，代表 i 分得太左邊了，往右移
            else {
                l = i + 1;
            }
        }

        // 不應該到這裡，保險用
        return -1;
    }
}