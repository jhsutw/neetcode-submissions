class Solution {
    public int reverseBits(int n) {
        int res = 0;
        for (int i = 0; i < 32; i++) {            // 一共 32 位 (int 是 32-bit)
            int bit = (n >> i) & 1;               // 取出 n 的第 i 位 (右移 i 位再取 &1)
            res += (bit << (31 - i));             // 把這個 bit 放到對應反轉的位置（左移 31-i 位）
        }
        return res;
    }
}
