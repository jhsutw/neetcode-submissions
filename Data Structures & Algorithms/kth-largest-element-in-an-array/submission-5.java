public class Solution {
    // partition 函數：對 nums[left...right] 做倒序 QuickSort partition，回傳 pivot 的最終位置
    private int partition(int[] nums, int left, int right) {
        int mid = (left + right) >> 1; // 中間點
        swap(nums, mid, left + 1); // 將中間點移到第二位 (left+1)

        // 三點中值法：將 left、mid、right 三個數做排序，使 pivot 更接近中位數
        if (nums[left] < nums[right]) 
            swap(nums, left, right);
        if (nums[left + 1] < nums[right]) 
            swap(nums, left + 1, right);
        if (nums[left] < nums[left + 1]) 
            swap(nums, left, left + 1);

        int pivot = nums[left + 1]; // pivot 就是調整後的中間值
        int i = left + 1;
        int j = right;

        // 雙指針法做 partition（目標：大的在左，小的在右）
        while (true) {
            while (nums[++i] > pivot); // 找第一個 <= pivot 的數
            while (nums[--j] < pivot); // 找第一個 >= pivot 的數
            if (i > j) break;          // 指針交錯表示結束
            swap(nums, i, j);          // 將兩邊錯放的數值交換
        }

        // 將 pivot 放回正確位置
        nums[left + 1] = nums[j];
        nums[j] = pivot;
        return j; // 回傳 pivot 的最終 index
    }

    // 交換陣列中兩個位置
    private void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }

    // quickSelect：尋找第 k 大元素（第 k-1 個 index，因為是 0-based）
    private int quickSelect(int[] nums, int k) {
        int left = 0;
        int right = nums.length - 1;

        while (true) {
            // 如果只剩下 1～2 個元素，就直接比較
            if (right <= left + 1) {
                if (right == left + 1 && nums[right] > nums[left])
                    swap(nums, left, right);
                return nums[k];
            }

            // 做 partition 拿到 pivot index
            int j = partition(nums, left, right);

            // 根據 pivot index 縮小搜尋範圍
            if (j >= k) right = j - 1;  // k 在左半邊
            if (j <= k) left = j + 1;   // k 在右半邊或正好是 j
        }
    }

    // 對外 API：找第 k 大元素
    public int findKthLargest(int[] nums, int k) {
        return quickSelect(nums, k - 1); // 對應第 k 大 → 第 (k-1) 個 index
    }
}

/*
| 比較項目             | 簡單版（上個方法）                            | 改良版（三點中值法版本）                                     |
| ---------------- | ------------------------------------ | ------------------------------------------------ |
| **Pivot 選擇**     | 總是選 `nums[right]`                    | 使用三點中值法：`left`, `mid`, `right` 三數排序後選中間值作為 pivot |
| **排序方向**         | 遞增排序（小 → 大）<br>所以找的是第 `length - k` 小 | 遞減排序（大 → 小）<br>直接找第 `k - 1` 個元素                  |
| **比較方式**         | `nums[i] <= pivot` 放到左邊              | `nums[i] > pivot` 放到左邊（因為大值在左）                   |
| **Pivot 放置位置**   | 將 pivot 放在正確位置後再做左右遞迴                | 一樣會放 pivot 到正確位置，但流程偏複雜（有額外調整）                   |
| **穩定性與效率**       | 簡單、實作快<br>但 pivot 選得不好會退化成 O(n²)     | 中位數 pivot 改良提升效率<br>平均更接近 O(n)                   |
| **Edge Case 處理** | 沒有特別處理 2 個元素的 case                   | 特別處理當只剩兩個元素時直接比較                                 |
| **程式碼長度**        | 精簡、直觀                                | 冗長但優化多（不容易 TLE）                                  |
| **適合場景**         | 數據量不大、快寫快改                           | 數據量大、對時間效率有要求時                                   |
*/
