// sorting
class Solution {
    public int[][] kClosest(int[][] points, int k) {
        // 根據距離平方來排序：不需要開根號，節省效能
        // 在java中平方為a[0]*a[0] or Math.pow(a, 2)；a ^ 2 表示：a 和 2 做 XOR 運算
        Arrays.sort(points, (a, b) -> 
            (a[0]*a[0] + a[1]*a[1]) - (b[0]*b[0] + b[1]*b[1])
        );

        // 回傳排序後的前 k 個點
        return Arrays.copyOfRange(points, 0, k);
    }
}

