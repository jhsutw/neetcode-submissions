// Divide And Conquer (Segment Tree) 太複雜沒看
// 建立一棵 Segment Tree，支援查詢區間內的最小高度的 index
public class MinIdx_Segtree {
    int n;
    final int INF = (int) 1e9; // 用來表示無效索引的最大值
    int[] A, tree;             // A 為原始資料陣列，tree 為 Segment Tree 陣列

    // 建構子：初始化並建立 Segment Tree
    public MinIdx_Segtree(int N, int[] heights) {
        this.n = N;
        this.A = heights;

        // 將陣列長度補齊為 2 的次方，方便 Segment Tree 建構
        while (Integer.bitCount(n) != 1) {
            A = java.util.Arrays.copyOf(A, n + 1);
            A[n] = INF; // 多出來的位置補上無窮大，不影響最小值查詢
            n++;
        }

        tree = new int[2 * n]; // 樹的總大小為 2n（標準 Segment Tree 結構）
        build();               // 建立 Segment Tree
    }

    // 建立 Segment Tree，每個節點儲存的是對應區間內最小值的 index
    public void build() {
        // 初始化葉節點：直接對應到 A 中的每一個 index
        for (int i = 0; i < n; i++) {
            tree[n + i] = i;
        }

        // 自底向上建立非葉節點
        for (int j = n - 1; j >= 1; j--) {
            int a = tree[j << 1];         // 左子節點
            int b = tree[(j << 1) + 1];   // 右子節點
            // 比較 A[a] 和 A[b]，把值較小的 index 存入父節點
            if (A[a] <= A[b]) {
                tree[j] = a;
            } else {
                tree[j] = b;
            }
        }
    }

    // 更新某一個 index 的值，同步更新 tree 中所有相關節點
    public void update(int i, int val) {
        A[i] = val; // 更新原陣列
        // 往上更新對應的 Segment Tree 節點
        for (int j = (n + i) >> 1; j >= 1; j >>= 1) {
            int a = tree[j << 1];
            int b = tree[(j << 1) + 1];
            if (A[a] <= A[b]) {
                tree[j] = a;
            } else {
                tree[j] = b;
            }
        }
    }

    // 查詢介於 ql ~ qh 區間內的最小值 index（對外方法）
    public int query(int ql, int qh) {
        return query(1, 0, n - 1, ql, qh);
    }

    // 查詢介於 ql ~ qh 的區間最小值 index（遞迴核心）
    public int query(int node, int l, int h, int ql, int qh) {
        // 無交集：回傳無效值
        if (ql > h || qh < l) return INF;
        // 完全包含：直接回傳該節點儲存的 index
        if (l >= ql && h <= qh) return tree[node];

        // 拆分左右子節點遞迴查詢
        int a = query(node << 1, l, (l + h) >> 1, ql, qh);
        int b = query((node << 1) + 1, ((l + h) >> 1) + 1, h, ql, qh);

        // 處理越界情況：只要其中一邊是 INF 就回傳另一邊
        if (a == INF) return b;
        if (b == INF) return a;
        // 回傳 A[a] 與 A[b] 較小值的 index
        return A[a] <= A[b] ? a : b;
    }
}

// 使用 Divide and Conquer 演算法搭配 Segment Tree 求最大長方形面積
public class Solution {
    // 計算 heights[l..r] 區間內可以構成的最大矩形面積
    public int getMaxArea(int[] heights, int l, int r, MinIdx_Segtree st) {
        if (l > r) return 0;              // 無效區間
        if (l == r) return heights[l];    // 只有一根柱子，面積就是它的高度

        // 找出 [l, r] 區間內最小高度柱子的 index
        int minIdx = st.query(l, r);

        // 面積可能來自三種情況：
        // 1. 包含最小柱子的整段矩形
        // 2. 左半邊的最大矩形（不包含 minIdx）
        // 3. 右半邊的最大矩形（不包含 minIdx）
        return Math.max(
            Math.max(getMaxArea(heights, l, minIdx - 1, st),
                     getMaxArea(heights, minIdx + 1, r, st)),
            (r - l + 1) * heights[minIdx] // 以最矮柱子為高度、整段為寬度
        );
    }

    // 主函式：建立 Segment Tree 並啟動遞迴
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        MinIdx_Segtree st = new MinIdx_Segtree(n, heights); // 建構最小值查詢用的 Segment Tree
        return getMaxArea(heights, 0, n - 1, st);            // 從整個區間開始遞迴
    }
}