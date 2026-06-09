// Segment Tree class，用來支援區間最大值查詢
class SegmentTree {
    int n;              // 樹的葉節點數量，補成2的次方
    int[] tree;         // 儲存整棵Segment Tree的陣列

    // Constructor：初始化 Segment Tree
    SegmentTree(int N, int[] A) {
        this.n = N;

        // 將n補齊為最接近的2的次方（保證能用陣列完整建樹）
        while (Integer.bitCount(n) != 1) {
            n++;
        }

        build(N, A); // 建立 Segment Tree
    }

    // 功能：建立 Segment Tree
    void build(int N, int[] A) {
        tree = new int[2 * n]; // 建立大小為 2n 的陣列（index: n ~ 2n-1 為葉節點）

        // 初始化整棵樹為最小整數值（因為是取最大值）
        Arrays.fill(tree, Integer.MIN_VALUE);

        // 將原始陣列 A 填入葉節點（從 tree[n] 開始）
        for (int i = 0; i < N; i++) {
            tree[n + i] = A[i];
        }

        // 自底向上建立樹中間節點
        for (int i = n - 1; i > 0; --i) {
            // tree[i << 1] 是左子節點，tree[i << 1 | 1] 是右子節點
            tree[i] = Math.max(tree[i << 1], tree[i << 1 | 1]);
        }
    }

    // 功能：查詢區間 [l, r] 的最大值
    int query(int l, int r) {
        int res = Integer.MIN_VALUE; // 初始化答案為最小整數值

        // 轉換成樹中葉節點對應的位置（Segment Tree 的特性）
        // r += n + 1 是為了讓右界變成「開區間」，方便迴圈判斷
        for (l += n, r += n + 1; l < r; l >>= 1, r >>= 1) {

            // 如果 l 是右子節點（奇數），先計算它，然後右移一位
            if ((l & 1) == 1) res = Math.max(res, tree[l++]);

            // 如果 r 是右子節點（奇數），要先 --r 回到左子節點，然後計算
            if ((r & 1) == 1) res = Math.max(res, tree[--r]);

            // 接著將 l 和 r 回到上一層（父節點）：相當於除以2
            // 這是 l >>= 1 與 r >>= 1 的功能
        }
        return res; // 回傳最終查詢結果
    }
}

// Solution class：使用 Segment Tree 解 Sliding Window Maximum 問題
public class Solution {
    // 功能：回傳每個長度為 k 的滑動視窗的最大值
    public int[] maxSlidingWindow(int[] nums, int k) {
        int n = nums.length;

        // 建立 Segment Tree，用來支援區間最大值查詢
        SegmentTree segTree = new SegmentTree(n, nums);

        // 輸出結果的陣列大小為 n - k + 1（滑動視窗的總數）
        int[] output = new int[n - k + 1];

        // 對每個滑動視窗進行查詢最大值
        for (int i = 0; i <= n - k; i++) {
            output[i] = segTree.query(i, i + k - 1); // 查詢區間最大值
        }

        return output;
    }
}

/*
補充說明：

1. Integer.bitCount(n) != 1：檢查 n 的二進位中是否只有一個 1，代表是 2 的次方。
   - 若不是，就把 n 補到最近的 2 的次方，保證 Segment Tree 高度完整。
   - 例如：n = 5（二進位 101）→ bitCount = 2 → 不是 2^x → 補到 8（2^3）

2. tree[i << 1] vs. tree[i << 1 | 1]：
   - i << 1 就是 i * 2 → 左子節點
   - i << 1 | 1 等價於 (i * 2 + 1) → 右子節點
   - 這是 Segment Tree 陣列中節點編號的標準寫法

3. l >>= 1, r >>= 1：相當於 l / 2, r / 2 → 移動到父節點層級。
   - 每次 query 往上走一層，直到 l >= r 結束

4. 為什麼 r += n + 1？
   - 為了讓查詢區間變成左閉右開 [l, r)
   - 這樣在處理 tree[--r] 時不會漏掉右邊界
*/
