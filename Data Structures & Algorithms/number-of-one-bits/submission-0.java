// 1. Bit Mask - I
public class Solution {
    public int hammingWeight(int n) {
        int res = 0;
        // 逐位檢查 32 個 bit（int 在 Java 為 32 位元，含符號位）
        for (int i = 0; i < 32; i++) {
            // (1 << i) 會產生只有第 i 位為 1 的遮罩
            // ((1 << i) & n) 若不為 0，代表 n 的第 i 位是 1
            if (((1 << i) & n) != 0) {
                res++;
            }
        }
        return res;
    }
}