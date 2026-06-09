// 6. Segment Tree（看不懂！放棄！）
/*
1. 座標壓縮
- 先對 nums 去重並排序成 sortedArr。
- 用二分搜尋把每個 nums[i] 映射成其在 sortedArr 的索引 order[i]（值域從原本的可能很大壓到 [0..n-1]）。
2. 建立線段樹
- 以壓縮後的長度 n 建一棵最大值線段樹 SegmentTree(n)，底層大小補到最接近的 2 的冪。
- 線段樹的意義：tree[idx] 存的是「索引範圍內，各值作為結尾的 LIS 長度的最大值」。也就是：位置 k 存「以值 sortedArr[k] 結尾的 LIS 最長是多少」。
3. 逐一處理每個數（由左到右）
對於 order 中每個 num：
- 查詢：在區間 [0, num-1] 的最大值 best = seg.query(0, num-1)，
代表「所有比當前值小的值作為結尾的 LIS 的最大值」。
若 num == 0，查詢區間會是空，程式會回傳 0（表示從頭起一個新序列）。
- 計算：當前值作為結尾的 LIS 長度 cur = best + 1。
- 更新：把位置 num 的值更新為 cur：seg.update(num, cur)。
- 維護全域答案 LIS = max(LIS, cur)。
4. 回傳答案
- 全部處理完後，LIS 即為最長遞增子序列長度。
*/
// 定義線段樹（Segment Tree）資料結構
class SegmentTree {
    int n;          // 樹葉節點數量（經過補齊成 2 的冪次方）
    int[] tree;     // 儲存線段樹節點的陣列

    public SegmentTree(int N) {
        n = N;
        // 將 n 補齊成最接近且大於等於 N 的 2 的冪次方 (只有當n是2的n次方時，(n & (n - 1)) == 0)
        // 這樣才能保證線段樹是完全二元樹
        while ((n & (n - 1)) != 0) {
            n++;
        }
        tree = new int[2 * n]; // 配置樹的儲存空間（索引 1 為根節點）
    }

    // 更新線段樹中第 i 個位置的值為 val
    void update(int i, int val) {
        tree[n + i] = val; // 更新葉節點
        int j = (n + i) >> 1; // 找到父節點位置
        while (j >= 1) {
            // 每次回溯更新父節點為左右子節點的最大值
            tree[j] = Math.max(tree[j << 1], tree[j << 1 | 1]);
            j >>= 1; // 移動到上層父節點
        }
    }

    // 查詢區間 [l, r] 內的最大值
    int query(int l, int r) {
        if (l > r) {
            return 0; // 無效區間，回傳 0
        }
        int res = Integer.MIN_VALUE; // 初始值設為最小整數
        l += n; // 對應到葉節點的索引
        r += n + 1; // 因為區間是閉區間，右邊要 +1
        while (l < r) {
            // 如果 l 是右子節點，將它的值納入結果並右移
            if ((l & 1) != 0) {
                res = Math.max(res, tree[l]);
                l++;
            }
            // 如果 r 是左子節點，先左移再將它的值納入結果
            if ((r & 1) != 0) {
                r--;
                res = Math.max(res, tree[r]);
            }
            // 移動到父節點
            l >>= 1;
            r >>= 1;
        }
        return res;
    }
}

public class Solution {
    public int lengthOfLIS(int[] nums) {
        // 將 nums 進行去重（distinct）並排序
        // sortedArr 用於壓縮座標
        int[] sortedArr = Arrays.stream(nums).distinct().sorted().toArray();

        // 將原本的 nums 映射成 sortedArr 中的索引位置
        // 方便用 Segment Tree 處理
        int[] order = new int[nums.length];
        for (int i = 0; i < nums.length; i++) {
            order[i] = Arrays.binarySearch(sortedArr, nums[i]);
        }

        int n = sortedArr.length;       // 壓縮後的元素數量
        SegmentTree segTree = new SegmentTree(n); // 建立線段樹

        int LIS = 0; // 最長遞增子序列長度
        for (int num : order) {
            // 查詢比當前數字小的所有數字的 LIS 最大值，然後 +1
            int curLIS = segTree.query(0, num - 1) + 1;

            // 更新當前數字對應位置的 LIS 值
            segTree.update(num, curLIS);

            // 更新全域最大 LIS
            LIS = Math.max(LIS, curLIS);
        }
        return LIS;
    }
}