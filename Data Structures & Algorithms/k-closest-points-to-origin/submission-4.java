/**
 * 此程式碼使用 QuickSelect 演算法，在一個點陣列中找到前 k 個離原點 (0,0) 最近的點。
 * 它會根據點與原點的距離平方進行劃分，類似快速排序 (QuickSort)，但只針對找到第 k 小的元素為止，
 * 效率比完整排序更高，平均時間複雜度為 O(n)。
 */

public class Solution {
    public int[][] kClosest(int[][] points, int k) {
        int L = 0, R = points.length - 1;
        int pivot = points.length;

        // QuickSelect 主迴圈：不斷根據 pivot 劃分直到恰好左邊有 k 個點為止（要排序到有k個點小於等於pivot點的距離）
        while (pivot != k) {
            pivot = partition(points, L, R);
            if (pivot < k) {
                // pivot 太小，代表第 k 小點在右側
                L = pivot + 1;
            } else {
                // pivot 太大或剛好，繼續往左找
                R = pivot - 1;
            }
        }

        // 複製前 k 個最近的點到結果陣列
        int[][] res = new int[k][2];
        System.arraycopy(points, 0, res, 0, k);
        return res;
    }

    // QuickSelect 的 partition 函式
    private int partition(int[][] points, int l, int r) {
        int pivotIdx = r; // 選最右邊的點當作 pivot
        int pivotDist = euclidean(points[pivotIdx]); // 計算 pivot 的距離平方
        int i = l;

        // 將所有距離 <= pivot 的點移到左邊
        for (int j = l; j < r; j++) {
            if (euclidean(points[j]) <= pivotDist) {
                // 交換 points[i] 和 points[j]
                int[] temp = points[i];
                points[i] = points[j];
                points[j] = temp;
                i++;
            }
        }

        // 最後把 pivot 放到正確的位置（也就是索引 i）
        int[] temp = points[i];
        points[i] = points[r];
        points[r] = temp;

        return i; // 回傳 pivot 最後位置
    }

    // 計算點 (x, y) 到原點的距離平方
    private int euclidean(int[] point) {
        return point[0] * point[0] + point[1] * point[1];
    }
}