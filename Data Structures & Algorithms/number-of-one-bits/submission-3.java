// 4. Built-In Function
public class Solution {
    public int hammingWeight(int n) {
        // 直接使用 JDK 內建的位元計數函式
        // Integer.bitCount(n) 會回傳 n 的二進位表示中「1」的個數（Hamming weight）
        // 注意：Java 的 int 為 32 位元，包含符號位；bitCount 會把 32 位元全部納入計算
        // 內部實作經過最佳化（JIT 可能映射為硬體 POPCNT 指令），效能佳
        return Integer.bitCount(n);
    }
}