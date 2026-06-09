// 3. Bit Mask (Optimal)
public class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        // Brian Kernighan 演算法：
        // 每次執行 n &= (n - 1) 會把 n 的「最低位的 1」清掉
        // 因此迴圈次數 = n 的二進位中 1 的個數
        while (n != 0) {
            /*
            n - 1會把 n 最低位的 1 變成 0，並把其右側所有 0 變成 1 (i.e. n = 0100; n-1 = 0011)
            */
            n &= n - 1; // 清除最低位的 1
            res++;      // 累計已清掉的 1 的數量
        }
        return res;      // 即為 n 的位元 1 的總數
    }
}