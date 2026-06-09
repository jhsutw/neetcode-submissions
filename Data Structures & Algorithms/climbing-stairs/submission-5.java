// 6. Math
public class Solution {
    public int climbStairs(int n) {
        // √5，用來計算黃金比例
        double sqrt5 = Math.sqrt(5);

        // 黃金比例 φ ≈ 1.618
        double phi = (1 + sqrt5) / 2;

        // φ 的共軛值 ψ ≈ -0.618
        double psi = (1 - sqrt5) / 2;

        // Fibonacci 閉式是從 F(0)=0 開始，而題目從 F(1)=1 開始 → 要用 n+1
        n++;

        // Binet’s formula：
        // F(n) = (φ^n - ψ^n) / √5
        return (int) Math.round((Math.pow(phi, n) -
                                 Math.pow(psi, n)) / sqrt5);
    }
}