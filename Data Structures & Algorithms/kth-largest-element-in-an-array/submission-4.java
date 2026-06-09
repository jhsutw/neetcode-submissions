/**
 * 此程式碼使用 Quick Select 演算法，在未排序的陣列中找出第 k 大的元素。
 * Quick Select 是 Quick Sort 的變形，只遞迴到目標位置，平均時間複雜度為 O(n)。
 * 
 * 解法邏輯：
 * 1. 將第 k 大的問題轉換成第 (n - k) 小的問題（也就是排序後 index 為 n - k 的數）。
 * 2. 使用 Quick Select 將數組切分並定位目標。
 */

public class Solution {
    public int findKthLargest(int[] nums, int k) {
        // 因為 QuickSelect 是找「第 k 小」的數，所以轉換為第 (n - k) 小
        k = nums.length - k;

        return quickSelect(nums, 0, nums.length - 1, k);
    }

    // QuickSelect 主邏輯，類似 QuickSort 的 partition
    private int quickSelect(int[] nums, int left, int right, int k) {
        int pivot = nums[right]; // 選擇最右邊作為 pivot
        int p = left; // p 為小於等於 pivot 的區塊尾端

        // 將所有 <= pivot 的元素移到左邊
        for (int i = left; i < right; i++) {
            if (nums[i] <= pivot) {
                int temp = nums[p];
                nums[p] = nums[i];
                nums[i] = temp;
                p++;
            }
        }

        // 將 pivot 放到正確位置（如此一來index p以前的元素均小於pivot）
        int temp = nums[p];
        nums[p] = nums[right];
        nums[right] = temp;

        // 根據 pivot 的位置與目標 k 判斷接下來的範圍 (判斷pivot是否為第k小的元素，若不是則從新定義pivot)
        if (p > k) {
            return quickSelect(nums, left, p - 1, k); // k 在左半邊 (pivot左半邊的元素僅知道比pivot小並沒有排序，要再縮小範圍用quickselect找)
        } else if (p < k) {
            return quickSelect(nums, p + 1, right, k); // k 在右半邊(pivot右半邊的元素僅知道比pivot大並沒有排序，要再縮小範圍用quickselect找)
        } else {
            return nums[p]; // 找到第 k 小的數
        }
    }
}