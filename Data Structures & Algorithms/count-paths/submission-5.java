// 6. Math (看不懂)
public class Solution {
    public int uniquePaths(int m, int n) {
        // 邊界：任一維度為 1，只有一條路（一路向右或一路向下）
        if (m == 1 || n == 1) {
            return 1;
        }

        // 選較小的 k 來計算組合數，以降低溢位風險與迭代次數
        // C(m+n-2, m-1) 與 C(m+n-2, n-1) 等價，取 k = min(m-1, n-1)
        if (m < n) {             // 確保 m >= n，則 k = n-1 會是較小者
            int temp = m;
            m = n;
            n = temp;
        }

        // 此時 k = n-1
        // 使用連乘/連除計算： res = ∏_{i=m}^{m+n-2} i / (n-1)!
        long res = 1;
        int j = 1;
        for (int i = m; i < m + n - 1; i++) { // i 走 m, m+1, ..., m+n-2（共 n-1 項）
            res *= i;   // 乘上分子的一項
            res /= j;   // 立刻除以分母對應的一項（1..n-1），保持 res 為整數
            j++;
        }

        // 注意：理論上結果可能超過 int（尤其 m,n 很大時），這裡依題目型別轉回 int
        return (int) res;
    }
}