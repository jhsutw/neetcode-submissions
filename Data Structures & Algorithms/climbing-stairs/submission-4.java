// 5. Matrix Exponentiation 太難放棄
public class Solution {

    // 主函式：計算爬上 n 階樓梯的方法數
    public int climbStairs(int n) {
        // 特例處理：只有一階樓梯時，只有一種走法
        if (n == 1) return 1;

        // 定義斐波那契數列的轉移矩陣 M：
        // M^n 可用來計算第 n 項的 Fibonacci 數
        int[][] M = {
            {1, 1},
            {1, 0}
        };

        // 使用快速冪計算 M 的 n 次方（得到的是 F(n+1)）
        int[][] result = matrixPow(M, n);

        // 回傳 F(n+1)，即為爬 n 階樓梯的方法數
        return result[0][0];
    }

    // 矩陣乘法：計算 A × B 的結果
    private int[][] matrixMult(int[][] A, int[][] B) {
        return new int[][] {
            {
                A[0][0] * B[0][0] + A[0][1] * B[1][0],
                A[0][0] * B[0][1] + A[0][1] * B[1][1]
            },
            {
                A[1][0] * B[0][0] + A[1][1] * B[1][0],
                A[1][0] * B[0][1] + A[1][1] * B[1][1]
            }
        };
    }

    // 快速冪：計算矩陣 M 的 p 次方（使用 binary exponentiation）
    private int[][] matrixPow(int[][] M, int p) {
        // 單位矩陣（等同於數值上的 1）
        int[][] result = {
            {1, 0},
            {0, 1}
        };

        // base 初始為 M（持續平方）
        int[][] base = M;

        // 不斷將 p 二分，使用 binary exponentiation 技巧
        while (p > 0) {
            // 如果 p 是奇數，將 base 乘進 result
            if (p % 2 == 1) {
                result = matrixMult(result, base);
            }

            // base 自乘（平方），p 減半
            base = matrixMult(base, base);
            p /= 2;
        }

        return result;
    }
}